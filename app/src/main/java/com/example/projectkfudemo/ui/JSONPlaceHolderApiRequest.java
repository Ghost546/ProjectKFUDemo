package com.example.projectkfudemo.ui;

import com.example.projectkfudemo.CurrentRequest;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.getCurrentRequests;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApiRequest {
    @GET("/posts/{id}")
    public Call<ArrayList<Request>> getRequestWithId(@Path("id") int id);


//    @GET("/posts/{id}")
//    public Call<ResponseRequest> getRequests(@Path("id") int id);
}