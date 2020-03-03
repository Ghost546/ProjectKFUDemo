package com.example.projectkfudemo.ui;

import com.example.projectkfudemo.Request;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONPlaceHolderApiRequest {
    @GET("/service_desk_mobile.get_request_list?p_user_id=230229&p2=1")
    public Call<ArrayList<Request>> getRequestWithId(@Path("id") int id);


//    @GET("/posts/{id}")
//    public Call<ResponseRequest> getRequests(@Path("id") int id);
}