package com.example.projectkfudemo.architecturalcomponents.ui.globalsearch;

import android.view.LayoutInflater;

import com.example.projectkfudemo.requests.Request;

import java.util.List;

public interface GlobalSearchInterface {
    void showResultFragment(List<Request> requestList);
    void setSpinners();
}
