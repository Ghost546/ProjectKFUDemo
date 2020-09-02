package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchWorkersList {
    @SerializedName("workers")
    @Expose
    private List<String> workersList;

    public List<String> getWorkersList() {
        return workersList;
    }

    public void setWorkersList(List<String> workersList) {
        this.workersList = workersList;
    }
}
