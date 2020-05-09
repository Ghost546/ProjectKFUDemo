package com.example.projectkfudemo.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.LoginActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.User;

public class MenuFragment extends Fragment implements View.OnClickListener {

    static Bundle args;

    Button mLogOutButton;

    public static MenuFragment newInstance(Bundle arg) {
        MenuFragment fragment = new MenuFragment();
        args = arg;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        User user = (User) args.getSerializable("user");
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        mLogOutButton = rootView.findViewById(R.id.fragment_menu_log_out_button);
        mLogOutButton.setOnClickListener(this);

        return rootView;
    }

    public void clickOnLogOutButton() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fragment_menu_log_out_button:
                clickOnLogOutButton();
                break;
        }
    }

}
