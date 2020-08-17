package com.example.projectkfudemo.ui;

import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.RequestList;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONApiRequest {

    //Запрос текущих заявок
    @GET("e-ksu/service_desk_mobile.get_request_list?")
    public Observable<RequestList> getRequestWithLoginPassword(@Query("p_user_id") int user_id, @Query("p2") String p1, @Query("p_status_id") int status);
}