package com.example.projectkfudemo.jsonapi;

import com.example.projectkfudemo.parametrclasses.requests.RequestList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONApiRequest {

    //Запрос текущих заявок
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.get_request_list?")
    Observable<RequestList> getRequestWithLoginPassword(@Field("p_user_id") int user_id, @Field("p2") String p1, @Field("p_status_id") int status);
}