package com.example.projectkfudemo.parametrclasses.forjson;

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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getChangeDateString() {
        return changeDateString;
    }

    public void setChangeDateString(String changeDateString) {
        this.changeDateString = changeDateString;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public int getChangeUserId() {
        return changeUserId;
    }

    public void setChangeUserId(int changeUserId) {
        this.changeUserId = changeUserId;
    }

    public String getChangeUserFullName() {
        return changeUserFullName;
    }

    public void setChangeUserFullName(String changeUserFullName) {
        this.changeUserFullName = changeUserFullName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
