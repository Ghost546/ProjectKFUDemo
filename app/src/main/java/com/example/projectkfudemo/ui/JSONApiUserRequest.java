package com.example.projectkfudemo.ui;

import com.example.projectkfudemo.RequestList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONApiUserRequest {

    //Запрос заявок текущего пользователя
    @GET("e-ksu/service_desk_mobile.get_user_request_list?")
    public Observable<RequestList> getRequestWithLoginPassword(@Query("p_user_id") int user_id, @Query("p2") String p2, @Query("p_status_id") int status);
}