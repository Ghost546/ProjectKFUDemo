package com.example.projectkfudemo.JSONApi;

import com.example.projectkfudemo.requests.RequestList;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONApiGlobalSearch {
//    Запрос на сервер для глобального поиска
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.get_search_request_list?")
    Observable<RequestList> getRequestListForSearch(@Field("p_user_id") int user_id, @Field("p2") String p2, @Field("p_declarant_fio") String name,
                                                    @Field("p_cod") Integer cod, @Field("p_date1") String dateStart, @Field("p_date2") String dateFinish,
                                                    @Field("p_regtype") Integer reg_type, @Field("p_status_id") Integer status, @Field("p_reg_user_id") Integer reg_user_id,
                                                    @Field("p_worker_id") Integer worker_id, @Field("p_page") Integer page, @Field("p_page_size") Integer page_size);
//    p_user_id NUMBER,
//
//    p2 NUMBER,
//
//    p_declarant_fio varchar2 default null, -- фио заявителя
//
//    p_cod NUMBER default null, -- номер заявки
//
//    p_date1 varchar2 default null, -- дата подачи заявки с
//
//    p_date2 varchar2 default null, -- дата подачи заявки по
//
//    p_regtype NUMBER default null, -- тип заявки
//
//    p_status_id NUMBER DEFAULT NULL, -- статус заявки
//
//    p_reg_user_id number default null, --id зарегистрировавшего заявку
//
//    p_worker_id number default null, --id исполнителя
//
//    p_page NUMBER DEFAULT NULL,
//
//    p_page_size NUMBER DEFAULT NULL)

    @POST("e-ksu/service_desk_mobile.get_search_request_list?p_user_id=82801&p2=12490708491553622138440874532401&p_declarant_fio=лукоянова&p_status_id=6&p_date1=2019-01-01&p_date2=2019-12-25")
    Observable<RequestList> getRequestListForSearchWithoutParams();
}
