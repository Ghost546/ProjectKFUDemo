package com.example.projectkfudemo.JSONApi;


import com.example.projectkfudemo.forjson.SearchWorkersList;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface JSONApiWorkersList {
    //ссылка для фио исполнителя
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.workers_list?")
    public Observable<SearchWorkersList> getSearchWorkersList(@Field("p_user_id") int user_id);
}
