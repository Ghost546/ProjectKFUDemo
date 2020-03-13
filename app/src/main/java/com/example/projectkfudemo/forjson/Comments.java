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
}
