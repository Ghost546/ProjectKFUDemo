package com.example.projectkfudemo.architecturalcomponents.ui.globalsearch;

import android.view.LayoutInflater;

import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.requests.RequestList;

import java.util.List;

public interface GlobalSearchInterface {
    void showResultFragment(RequestList requestList);
    void setSpinners();
}
