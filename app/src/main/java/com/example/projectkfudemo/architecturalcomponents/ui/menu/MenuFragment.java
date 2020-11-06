package com.example.projectkfudemo.architecturalcomponents.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.architecturalcomponents.ui.LoginActivity;
import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.parametrclasses.User;
import com.squareup.picasso.Picasso;

import static com.example.projectkfudemo.architecturalcomponents.ui.MainActivity.APP_PREFERENCES;
import static com.example.projectkfudemo.architecturalcomponents.ui.MainActivity.APP_PREFERENCES_LOGIN;
import static com.example.projectkfudemo.architecturalcomponents.ui.MainActivity.APP_PREFERENCES_PASSWORD;

//https://shelly.kpfu.ru/e-ksu/docs/F383898805/19545.jpg
public class MenuFragment extends Fragment implements View.OnClickListener {
    static Bundle args;
    SharedPreferences userPreferences;
    User user;

    MainActivity mainActivity;
    LayoutInflater myInflater;
    View rootView;

    private Button mLogOutButton;
    private ImageView userPicture;
    private Bitmap imageInMemory;
    private TextView mFirstNameText;
    private TextView mSecondNameText;
    private TextView mThirdNameText;

    public static MenuFragment newInstance(Bundle arg) {
        MenuFragment fragment = new MenuFragment();
        args = arg;
        return fragment;
    }

    private void setIds() {
        userPicture = rootView.findViewById(R.id.fragment_menu_user_icon);
        mLogOutButton = rootView.findViewById(R.id.fragment_menu_log_out_button);
        mFirstNameText = rootView.findViewById(R.id.fragment_first_name_text);
        mSecondNameText = rootView.findViewById(R.id.fragment_second_name_text);
        mThirdNameText = rootView.findViewById(R.id.fragment_third_name_text);
    }

    private void setUserNameOnView() {
        mFirstNameText.setText(user.getLastname());
        if(mFirstNameText.getText().toString().length()>0) {
            mFirstNameText.setVisibility(View.VISIBLE);
        }
        mSecondNameText.setText(user.getFirstname());
        if(mSecondNameText.getText().toString().length()>0) {
            mSecondNameText.setVisibility(View.VISIBLE);
        }
        mThirdNameText.setText(user.getMiddlename());
        if(mThirdNameText.getText().toString().length()>0) {
            mThirdNameText.setVisibility(View.VISIBLE);
        }
    }

    public String getStringForURL() {
        return user.getEmployeeInfo().getPhoto();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        userPreferences = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        mainActivity = (MainActivity) getActivity();
        myInflater = inflater;
        rootView = view;

        user = (User) args.getSerializable("user");

        setIds();

        setUserNameOnView();

        mLogOutButton.setOnClickListener(this);

        if(!getStringForURL().equals("")) {
            Picasso.get().load("https://shelly.kpfu.ru/e-ksu/docs/" + getStringForURL()).into(userPicture); //Итоговый код
        }
        //TODO: придумать что-нибудь с кнопкой выхода и отображением изображения польователя,
        // в идеале сделать динамическим по размеру изображения. Возможно переписать с Picasso на Glide

        return view;
    }



    public void clickOnLogOutButton() {
        SharedPreferences.Editor editor = userPreferences.edit();
        editor.putString(APP_PREFERENCES_LOGIN, null);
        editor.putString(APP_PREFERENCES_PASSWORD, null);
        editor.apply();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mainActivity != null) {
            mainActivity.switchSelectedItemMenu();
        }
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fragment_menu_log_out_button:
                clickOnLogOutButton();
                break;
        }
    }

}
