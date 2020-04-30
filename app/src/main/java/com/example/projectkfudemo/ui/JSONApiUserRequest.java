package com.example.projectkfudemo.ui;

import com.example.projectkfudemo.RequestList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONApiUserRequest {
    @GET("e-ksu/service_desk_mobile.get_user_request_list?p_user_id=user_id&p2=p1")
    public Observable<RequestList> getRequestWithLoginPassword(@Query("user_id") int user_id, @Query("p1") String p2);
}
