package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.Observable;

public class SearchDeclarerList {
    @SerializedName("registrators")
    @Expose
    private List<String> declarersList;

    public List<String> getDeclarersList() {
        return declarersList;
    }

    public void setDeclarersList(List<String> declarersList) {
        this.declarersList = declarersList;
    }
}
