package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LogsList {
    @SerializedName("logs")
    @Expose
    private List<Log> logs;
}
