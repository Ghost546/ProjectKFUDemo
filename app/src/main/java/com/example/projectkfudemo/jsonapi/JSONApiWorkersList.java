package com.example.projectkfudemo.jsonapi;


import com.example.projectkfudemo.parametrclasses.forjson.SearchWorkersList;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface JSONApiWorkersList {
    //ссылка для фио исполнителя
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.workers_list?")
    Observable<SearchWorkersList> getSearchWorkersList(@Field("p_user_id") int user_id);
}
