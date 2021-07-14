package com.example.projectkfudemo.jsonapi;

import com.example.projectkfudemo.parametrclasses.requests.RequestList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JSONApiIsCommentScript {
    @FormUrlEncoded
    @POST("e-ksu/service_desk_mobile.is_comment_script?")
    Observable<RequestList> setIsCommentScript(@Field("p_user_id") String userId, @Field("p2") String p2,
                                               @Field("p_request_id") String requestId,
                                               @Field("p_begin_date") String beginDate,
                                               @Field("p_end_date") String endDate, @Field("p_comment") String comment,
                                               @Field("p_classifier") String classifier,
                                               @Field("p_type") String typeOfActionWithComment,
                                               @Field("p_result") String result, @Field("p_summary") String summary,
                                               @Field("p_recommend") String recommend, @Field("p_url") String url,
                                               @Field("p_status") String status, @Field("p_executor") String executor,
                                               @Field("p_wid") String wid, @Field("p_check") String check,
                                               @Field("p_inventory_card") String inventoryCard, @Field("p_files") String files);
}
//    PROCEDURE is_comment_script(p_user_id VARCHAR2, -- id пользователя в таблице ias$db.users.................................................................+
//
//    p2 VARCHAR2, -- id текущей пользовательской сессии........................................................................................................+
//
//    p_request_id VARCHAR2 DEFAULT NULL, -- id заявки  в tech_center$db.request................................................................................+
//
//    p_begin_date VARCHAR2 DEFAULT NULL, -- дата начала работ по заявке (в сроке выполнения)...................................................................+
//
//    p_end_date VARCHAR2 DEFAULT NULL, -- дата окончания работ по заявке (в сроке выполнения)..................................................................+
//
//    p_comment VARCHAR2 DEFAULT NULL, -- описание выполненных работ............................................................................................+
//
//    p_classifier typ_tbl_char DEFAULT tbl_char, -- массив кодов выполненных по заявке работ...................................................................+
//
//    p_type VARCHAR2 DEFAULT NULL, -- тип действий с  комментарием (отчетом) исполнителя  (добавить / редактировать / удалить).................................+
//
//    p_result VARCHAR2 DEFAULT NULL,...........................................................................................................................+
//
//    p_summary VARCHAR2 DEFAULT NULL,..........................................................................................................................+
//
//    p_recommend VARCHAR2 DEFAULT NULL,........................................................................................................................+
//
//    p_url VARCHAR2 DEFAULT NULL, -- имя процедуры, отображающей список заявок (для возврата именно к этому списку после закрытия карточки заявки).............+
//
//    p_status VARCHAR2 DEFAULT NULL, -- код статуса заявки.....................................................................................................+
//
//    p_executor typ_tbl_char DEFAULT tbl_char, -- id исполнителя, оставившего комментарий (отчет) о проделанной работе, в таблице ias$db.users.................+
//
//    p_wid VARCHAR2 DEFAULT NULL, -- id комментария (отчета) исполнителя о проделанных по заявке работах  в таблице tech_center$db.works_for_request...........+
//
//    p_check VARCHAR2 DEFAULT NULL, -- индикатор, насколько выполнены работы: 1 - частично, 2 - полностью......................................................+
//
//    p_inventory_card typ_tbl_char DEFAULT tbl_char,...........................................................................................................+
//
//    p_files gtyp_tbl_varchar2 DEFAULT gtbl_varchar2);.........................................................................................................+