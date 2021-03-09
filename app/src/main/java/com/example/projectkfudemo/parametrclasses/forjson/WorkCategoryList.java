package com.example.projectkfudemo.parametrclasses.forjson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkCategoryList {
    @SerializedName("work_category")
    private List<WorkCategory> workCategoryList;

    public List<WorkCategory> getWorkCategoryList() {
        return workCategoryList;
    }

    public void setWorkCategoryList(List<WorkCategory> workCategoryList) {
        this.workCategoryList = workCategoryList;
    }
}
