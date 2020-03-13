package com.example.projectkfudemo;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;



import static com.example.projectkfudemo.R.*;


public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private BaseActivity baseActivity = new BaseActivity();

    public void showProgressDialog() {
        baseActivity.showProgressDialog();
    }

    public void hideProgressDialog() {
        baseActivity.hideProgressDialog();
    }

    public Button signInButton;


    @Override
    public void onStart() {
           super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signInButton = findViewById(R.id.email_sign_in_button);
        login = findViewById(id.field_login);
        password = findViewById(id.field_password);
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
       /* if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt, user.getEmail()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
            findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }*/
    }


    private void mSignOnClick() {
        String varLogin;
        String varPassword;

        if (login.getText().toString().equals("")) {
            //код если поле пусто
        } else {
            //код если текст есть
            varLogin = login.getText().toString();
        }
        if (password.getText().toString().equals("")) {
            //код если поле пусто
        } else {
            //код если текст есть
            varPassword = password.getText().toString();
        }
        //нужно отправить запрос на сервер
        //получить idшник и работать с ним
//        NetworkService.getInstance().getJSONUserApi().getUser().equals(new Callback<ArrayList<User>>() {
//            @Override
//            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
//                if (response.isSuccessful()) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
//                System.out.print("Error occurred while getting request!");
//                t.printStackTrace();
//            }
//        });

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case id.email_sign_in_button:
                mSignOnClick();
                break;
        }
    }

}

