package com.example.projectkfudemo.ui.menu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.CurrentRequestStateAdapter;
import com.example.projectkfudemo.LoginActivity;
import com.example.projectkfudemo.NetworkServiceRequests;
import com.example.projectkfudemo.NetworkServiceUserImage;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.RequestList;
import com.example.projectkfudemo.User;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static io.fabric.sdk.android.Fabric.TAG;

public class MenuFragment extends Fragment implements View.OnClickListener {

    static Bundle args;

    private Button mLogOutButton;
    private ImageView userPicture;

    public static MenuFragment newInstance(Bundle arg) {
        MenuFragment fragment = new MenuFragment();
        args = arg;
        return fragment;
    }
//https://shelly.kpfu.ru/e-ksu/docs/F383898805/19545.jpg
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        User user = (User) args.getSerializable("user");
        userPicture = rootView.findViewById(R.id.fragment_menu_user_icon);
        mLogOutButton = rootView.findViewById(R.id.fragment_menu_log_out_button);
        mLogOutButton.setOnClickListener(this);

        NetworkServiceUserImage.subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<RequestList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestList requestList) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


        NetworkServiceUserImage image = new NetworkServiceUserImage("https://shelly.kpfu.ru/e-ksu/docs/F383898805/19545.jpg");
        image.start();
        userPicture.setImageBitmap(image.getBmWithPicture());

        return rootView;
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
