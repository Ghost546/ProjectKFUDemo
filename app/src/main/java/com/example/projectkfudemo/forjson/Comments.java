package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.joda.time.LocalDate;

import java.util.List;

public class Comments {
    @SerializedName("worker_id")
    @Expose
    private int workerId;

    @SerializedName("begin_date")
    @Expose
    private String beginDateString;

    @SerializedName("end_date")
    @Expose
    private String endDateString;

    private LocalDate beginDate;
    private LocalDate endDate;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("works")
    @Expose
    private List<WorksInComment> worksInCommentsList;

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<WorksInComment> getWorksInCommentsList() {
        return worksInCommentsList;
    }

    public void setWorksInCommentsList(List<WorksInComment> worksInCommentsList) {
        this.worksInCommentsList = worksInCommentsList;
    }
}
