package com.example.projectkfudemo.architecturalcomponents.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.User;

public class MapFragment extends Fragment {
    static Bundle args;
    MainActivity mainActivity;

    public static MapFragment newInstance(Bundle arg) {
        MapFragment fragment = new MapFragment();
        args = arg;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        User user = (User) args.getSerializable("user");

        mainActivity = (MainActivity) getActivity();

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (mainActivity != null) {
            mainActivity.switchSelectedItemMap();
        }
    }
}
