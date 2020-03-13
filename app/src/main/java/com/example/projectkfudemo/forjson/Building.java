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
}
