package com.example.projectkfudemo.ui.requestgeneralview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.CurrentRequest;
import com.example.projectkfudemo.MyRequest;
import com.example.projectkfudemo.R;

public class RequestGeneralViewFragment extends Fragment {

    public static RequestGeneralViewFragment newInstance(Request req) {
        RequestGeneralViewFragment fragment = new RequestGeneralViewFragment();

        Bundle bundle = new Bundle();


        fragment.request = req;


        return fragment;
    }


    private void VisibleSetting(CurrentRequest req) {

    }

    private void VisibleSetting(MyRequest req) {

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_general_view_request, container, false);

        if (req instanceof MyRequest)
            VisibleSetting(req);
        else VisibleSetting(req);

        return root;
    }
}
