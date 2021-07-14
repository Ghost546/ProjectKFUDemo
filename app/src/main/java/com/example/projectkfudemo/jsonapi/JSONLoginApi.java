package com.example.projectkfudemo.jsonapi;

import com.example.projectkfudemo.parametrclasses.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONLoginApi {

    //Запрос на регистрацию
    @FormUrlEncoded
    @POST("e-ksu/portal_pg_mobile.authentication?")
    Observable<User> getUser(@Field("p_login") String login, @Field("p_pass") String pass);
}
