package com.example.projectkfudemo.JSONApi;

import com.example.projectkfudemo.forjson.SearchDeclarerList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONApiDeclarerList {
    //ссылка для Заявку зарегистрировал
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.registrators_list?")
    public Observable<SearchDeclarerList> getSearchDeclarerList(@Field("p_user_id") int user_id);
}
