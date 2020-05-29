package com.example.projectkfudemo.ui;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;

public interface RetrofitGetUserImage {
    @GET("")
    void downloadFileWithFixedUrl();
}
