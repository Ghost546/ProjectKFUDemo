package com.example.projectkfudemo.forjson;


import com.google.gson.annotations.SerializedName;

import java.util.List;

// For JSON
public class WorksList {
    @SerializedName("works")
    private List<Work> works;
}
