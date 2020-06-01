package com.example.projectkfudemo.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.User;

public class MapFragment extends Fragment {
    static Bundle args;

    public static MapFragment newInstance(Bundle arg) {
        MapFragment fragment = new MapFragment();
        args = arg;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        User user = (User) args.getSerializable("user");

        return rootView;
    }
}
