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
}
