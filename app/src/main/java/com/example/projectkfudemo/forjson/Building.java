package com.example.projectkfudemo.forjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//For JSON
//эти классы нужны для того чтобы  заполнить класс Request
public class Building {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("name")
    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
