package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Workers {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("fullname")
    @Expose
    private String fullname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
