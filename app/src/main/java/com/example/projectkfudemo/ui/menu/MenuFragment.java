package com.example.projectkfudemo.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.CurrentRequestStateAdapter;
import com.example.projectkfudemo.LoginActivity;
import com.example.projectkfudemo.NetworkServiceRequests;
import com.example.projectkfudemo.NetworkServiceUserImage;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.RequestList;
import com.example.projectkfudemo.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class MenuFragment extends Fragment implements View.OnClickListener {

    static Bundle args;

    Button mLogOutButton;

    public static MenuFragment newInstance(Bundle arg) {
        MenuFragment fragment = new MenuFragment();
        args = arg;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        User user = (User) args.getSerializable("user");
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        mLogOutButton = rootView.findViewById(R.id.fragment_menu_log_out_button);
        mLogOutButton.setOnClickListener(this);

        NetworkServiceUserImage.getInstance().getRetrofitGetUserImage().downloadFileWithFixedUrl()
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        boolean writtenToDisk = writeResponseBodyToDisk(responseBody);
                        System.out.println("File download was a success? " + String.valueOf(writtenToDisk));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return rootView;
    }




    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(File.separator + "Future Studio Icon.png");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[40000000];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    System.out.print("File Download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    public void clickOnLogOutButton() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fragment_menu_log_out_button:
                clickOnLogOutButton();
                break;
        }
    }

}
