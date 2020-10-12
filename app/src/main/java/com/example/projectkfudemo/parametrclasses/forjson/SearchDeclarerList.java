package com.example.projectkfudemo.parametrclasses.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchDeclarerList {
    @SerializedName("registrators")
    @Expose
    private List<SearchDeclarer> declarersList;

    public List<SearchDeclarer> getDeclarersList() {
        return declarersList;
    }

    public void setDeclarersList(List<SearchDeclarer> declarersList) {
        this.declarersList = declarersList;
    }
}
