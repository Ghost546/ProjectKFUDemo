package com.example.projectkfudemo.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.User;

public class MenuFragment extends Fragment {

    static Bundle args;

    public static MenuFragment newInstance(Bundle arg) {
        MenuFragment fragment = new MenuFragment();
        args = arg;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        User user = (User) args.getSerializable("user");
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        return rootView;
    }
}
