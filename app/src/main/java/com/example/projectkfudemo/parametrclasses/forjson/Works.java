package com.example.projectkfudemo.parametrclasses.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Works {
    @SerializedName("date_of")
    @Expose
    private String date;

    @SerializedName("service_name")
    @Expose
    private String serviceName;

    @SerializedName("type_name")
    @Expose
    private String typeName;

    @SerializedName("description")
    @Expose
    private String description; //описание заявки

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
