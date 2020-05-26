package com.example.projectkfudemo;


import com.example.projectkfudemo.ui.RetrofitGetUserImage;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceUserImage {
    private static NetworkServiceUserImage mInstance;
    private static final String BASE_URL = "https://shelly.kpfu.ru/"; //основной адрес
    private Retrofit mRetrofit;

    private NetworkServiceUserImage() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    public static NetworkServiceUserImage getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkServiceUserImage();
        }
        return mInstance;
    }

    public RetrofitGetUserImage getRetrofitGetUserImage() {
        return mRetrofit.create(RetrofitGetUserImage.class);
    }
}
