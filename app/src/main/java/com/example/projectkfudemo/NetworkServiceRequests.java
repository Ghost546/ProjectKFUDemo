package com.example.projectkfudemo;

import com.example.projectkfudemo.ui.JSONApiRequest;
import com.example.projectkfudemo.ui.JSONApiUserRequest;
import com.example.projectkfudemo.ui.JSONLoginApi;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkServiceRequests {
    private static NetworkServiceRequests mInstance;
    private static final String BASE_URL = "https://portal-dis.kpfu.ru/"; //основной адрес
    private Retrofit mRetrofit;

    private NetworkServiceRequests() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    public static NetworkServiceRequests getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkServiceRequests();
        }
        return mInstance;
    }

    public JSONApiUserRequest getJSONUserRequestApi() {
        return mRetrofit.create(JSONApiUserRequest.class);
    }

    public JSONApiRequest getJSONRequestApi() {
        return mRetrofit.create(JSONApiRequest.class);
    }

    public JSONLoginApi getJSONUserApi() {
        return  mRetrofit.create(JSONLoginApi.class);
    }

}
