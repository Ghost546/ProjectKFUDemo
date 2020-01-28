package com.example.projectkfudemo;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int id;
    private String name;
    private String secondname;
    private int funtion;
    private int phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public int getFuntion() {
        return funtion;
    }

    public void setFuntion(int funtion) {
        this.funtion = funtion;
    }

}
