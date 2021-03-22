package com.example.projectkfudemo.JSONApi;

import com.example.projectkfudemo.parametrclasses.requests.RequestList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONApiUserRequest {

    //ссылка заявок текущего пользователя
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.get_user_request_list?")
    public Observable<RequestList> getRequestWithLoginPassword(@Field("p_user_id") int user_id, @Field("p2") String p2, @Field("p_status_id") int status);
}