package com.example.projectkfudemo.ui;

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
//    @GET("e-ksu/service_desk_mobile.get_request_list?p_user_id=230229&p2=1")
//    public Call<User> getUser(/*@Query("p_user_id") int id, @Query("p2") String password*/);
    @FormUrlEncoded
    @POST("e-ksu/portal_pg_mobile.authentication?")
    Observable<User> getUser(@Field("p_login") String login, @Field("p_pass") String pass);
}
