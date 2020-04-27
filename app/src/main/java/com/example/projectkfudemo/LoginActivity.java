package com.example.projectkfudemo;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.projectkfudemo.R.*;


public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private BaseActivity baseActivity = new BaseActivity();
    private String vLogin;
    private int vPassword;
    private String varLogin = "vdvoenosov";
    private int varPassword = 1;
    private volatile User userMain;


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
        setContentView(R.layout.activity_login);
        signInButton = findViewById(R.id.email_sign_in_button);
        login = findViewById(id.field_login);
        password = findViewById(id.field_password);

        NetworkService.getInstance().getJSONUserApi().getUser("vdvoenos", 1)
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        userMain = user;

                        System.out.println("Здесь твои переменные: " + user.getName() + ", " + user.getPassword());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    private void updateUI(FirebaseUser user) {
//        hideProgressDialog();
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
//    }


    private void mSignOnClick() {
        if (login.getText().toString().equals("")) {
            //код если поле пусто
        } else {
            //код если текст есть
            if (password.getText().toString().equals("")) {
                //код если поле пусто
            } else {
                //код если текст есть
                varLogin = login.getText().toString();
                //varPassword = password.getText().toString();
                NetworkService.getInstance().getJSONUserApi().getUser(varLogin, 1).equals(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        vLogin = varLogin;
                        vPassword = varPassword;
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.print("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });

            }
        }

        //нужно отправить запрос на сервер
        //получить idшник и работать с ним

        if(vLogin != null && vPassword != 0) {
            LogIn(vLogin, vPassword);
        }
    }

    public void LogIn(String vLogin, int vPassword) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("login", vLogin);
        intent.putExtra("password", vPassword);
        startActivity(intent);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case id.email_sign_in_button:
                mSignOnClick();
                break;
        }
    }

}

