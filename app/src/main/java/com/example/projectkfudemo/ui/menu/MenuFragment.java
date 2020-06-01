package com.example.projectkfudemo.ui.menu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.LoginActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.User;
import com.squareup.picasso.Picasso;

//https://shelly.kpfu.ru/e-ksu/docs/F383898805/19545.jpg
public class MenuFragment extends Fragment implements View.OnClickListener {

    static Bundle args;

    private Button mLogOutButton;
    private ImageView userPicture;
    private Bitmap imageInMemory;
    User user;

    public static MenuFragment newInstance(Bundle arg) {
        MenuFragment fragment = new MenuFragment();
        args = arg;
        return fragment;
    }

    public String getStringForURL() {
        return user.getEmployeeInfo().getPhoto();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        user = (User) args.getSerializable("user");
        userPicture = rootView.findViewById(R.id.fragment_menu_user_icon);
        mLogOutButton = rootView.findViewById(R.id.fragment_menu_log_out_button);
        mLogOutButton.setOnClickListener(this);

        if(!getStringForURL().equals("")) {
            Picasso.get().load("https://shelly.kpfu.ru/e-ksu/docs/" + getStringForURL()).into(userPicture); //Итоговый код
        }

//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("https://shelly.kpfu.ru/e-ksu/docs/F383898805/19545.jpg")
//                .build();
//
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
//                if (response.body() != null) {
//                    response.body().byteStream(); // Read the data from the stream
//                    Bitmap imageFromServer = BitmapFactory.decodeStream(response.body().byteStream());
//                    if (imageFromServer == null)System.out.println("Изображение получено");
//                    else System.out.println("Изображения нет");
//                    setImageFromServer(imageFromServer);
//
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                System.out.println("request failed: " + e.getMessage());
//            }
//        });





//        NetworkServiceUserImage.subscribeOn(Schedulers.io()) //Schedulers.io()
//                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
//                .subscribe(new Observer<RequestList>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(RequestList requestList) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

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
