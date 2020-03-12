package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Work {
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("type_name")
    @Expose
    private String typeName;
    @SerializedName("description")
    @Expose
    private String description;
}
