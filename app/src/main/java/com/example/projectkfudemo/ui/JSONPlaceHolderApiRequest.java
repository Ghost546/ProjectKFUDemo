package com.example.projectkfudemo.ui;

import com.example.projectkfudemo.Request;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApiRequest {
    @GET("/posts/{id}")
    public Call<ArrayList<Request>> getData(@Path("id") int id);
}