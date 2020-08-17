package com.example.projectkfudemo.ui.globalsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.R;


public class GlobalSearchFragment extends Fragment {
    static Bundle args;

    public static GlobalSearchFragment newInstance(Bundle arg) {
        GlobalSearchFragment fragment = new GlobalSearchFragment();
        args = arg;
        return fragment;
    }

    public void setIds() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_global_search, container, false);



        return rootView;
    }


}
