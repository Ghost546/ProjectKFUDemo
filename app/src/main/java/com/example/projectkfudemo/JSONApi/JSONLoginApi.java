package com.example.projectkfudemo.JSONApi;

import com.example.projectkfudemo.User;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JSONLoginApi {

    //Запрос на регистрацию
    @FormUrlEncoded
    @POST("e-ksu/portal_pg_mobile.authentication?")
    Observable<User> getUser(@Field("p_login") String login, @Field("p_pass") String pass);
}
