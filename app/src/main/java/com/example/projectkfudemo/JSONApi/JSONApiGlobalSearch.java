package com.example.projectkfudemo.JSONApi;

import com.example.projectkfudemo.requests.RequestList;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONApiGlobalSearch {
//    Запрос на сервер для глобального поиска
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.get_search_request_list?p_user_id=82801&p2=12490708491553622138440874532401&p_declarant_fio=лукоянова&p_status_id=6&p_date1=2019-01-01&p_date2=2019-12-25")
    public Observable<RequestList> getRequestListForSearch(@Field("p_user_id") int user_id, @Field("p2") String p2, @Field("p_declarant_fio") String name,
                                                           @Field("p_cod") int cod, @Field("p_date1") String dateStart, @Field("p_date2") String dateFinish,
                                                           @Field("p_regtype") int reg_type, @Field("p_status_id") int status, @Field("p_reg_user_id") int reg_user_id,
                                                           @Field("p_worker_id") int worker_id, @Field("p_page") int page, @Field("p_page_size") int page_size);
}
