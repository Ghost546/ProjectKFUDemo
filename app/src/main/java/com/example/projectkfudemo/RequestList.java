package com.example.projectkfudemo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RequestList {

    @SerializedName("requests")
    @Expose
    private List<Request> requests;

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}

