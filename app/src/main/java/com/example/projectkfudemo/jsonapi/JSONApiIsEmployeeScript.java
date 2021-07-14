package com.example.projectkfudemo.jsonapi;

import com.example.projectkfudemo.parametrclasses.requests.RequestList;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONApiIsEmployeeScript {
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.is_employee_script?")
    Single setIsCommentScript(@Field("p_user_id") String userId,
                                @Field("p2") String p2, @Field("p_id") String requestId,
                                @Field("p_employee") String employee,
                                @Field("p_status_sort") String statusSort,
                                @Field("p_h") String hashedValueOfThePassedParameters,
                                @Field("p_url") String url);
//    PROCEDURE is_employee_script (p_user_id NUMBER,   -- id пользователя в таблице ias$db.users
//
//    p2 varchar2,   -- id текущей пользовательской сессии
//
//    p_id varchar2 default null,   -- id заявки  в tech_center$db.request
//
//    p_employee typ_tbl_char default tbl_char,   -- массив id назначенных исполнителей в ias$db.users
//s
//    p_status_sort varchar2 default '1',   -- код выбранного статуса заявок в списке (для возврата именно к этому списку после закрытия карточки заявки)
//
//    p_h raw, -- хэшированное значение передаваемых параметров (p1, p2)
//
//    p_url varchar2 default null);  -- имя процедуры, отображающей список заявок (для возврата именно к этому списку после закрытия карточки заявки);
}
