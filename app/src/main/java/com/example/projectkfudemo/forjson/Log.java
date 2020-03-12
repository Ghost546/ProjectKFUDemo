package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

public class Log {
    @SerializedName("status_name")
    @Expose
    private String statusName;
    @SerializedName("status_id")
    @Expose
    private int statusId;
    @SerializedName("change_date")
    @Expose
    private String changeDateString;
    private LocalDate changeDate;
    @SerializedName("change_user_id")
    @Expose
    private int changeUserId;
    @SerializedName("change_user_fullname")
    @Expose
    private String changeUserFullName;
    @SerializedName("comments")
    @Expose
    private String comment;
}
