package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Works {
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("type_name")
    @Expose
    private String typeName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("description")
    @Expose
    private String description;
}
