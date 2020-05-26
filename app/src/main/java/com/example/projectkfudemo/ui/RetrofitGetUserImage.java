package com.example.projectkfudemo.ui;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;

public interface RetrofitGetUserImage {
    @GET("e-ksu/docs/F383898805/19545.jpg")
    public Observable<ResponseBody> downloadFileWithFixedUrl();
}
