package com.example.projectkfudemo;

import com.example.projectkfudemo.ui.JSONPlaceHolderApiRequest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = ""; //основной адрес
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public JSONPlaceHolderApiRequest getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApiRequest.class);
    }

}
