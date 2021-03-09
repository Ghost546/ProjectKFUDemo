package com.example.projectkfudemo.JSONApi;

import com.example.projectkfudemo.parametrclasses.forjson.WorkCategoryList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONApiWorkCategoryList {
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.work_category_list?")
    public Observable<WorkCategoryList> getSearchWorkCategory(@Field("p_user_id") int user_id);
}
