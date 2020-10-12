package com.example.projectkfudemo.parametrclasses.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchWorkersList {
    @SerializedName("workers")
    @Expose
    private List<SearchWorkers> workersList;

    public List<SearchWorkers> getWorkersList() {
        return workersList;
    }

    public void setWorkersList(List<SearchWorkers> workersList) {
        this.workersList = workersList;
    }
}
