package com.example.projectkfudemo.architecturalcomponents.ui;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.parametrclasses.User;
import com.example.projectkfudemo.architecturalcomponents.models.NetworkServiceRequests;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.projectkfudemo.R.*;


public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private String vLogin;
    private int vPassword;
    private String varLogin;
    private String varPassword;
    private volatile User userMain;
    SharedPreferences mUser;

    private ImageView saver;

    public static final String APP_PREFERENCES = "User";

    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_PASSWORD = "Password";

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
        saver = findViewById(R.id.saver);                                                           //заглушка экрана на время проверки авторизации
        mUser = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);                        //содержит данные об авторизованном пользователе
        if(mUser.contains(APP_PREFERENCES_LOGIN) & mUser.contains(APP_PREFERENCES_PASSWORD)) {      //проверяет авторизован ли пользователь
            if(!mUser.getString(APP_PREFERENCES_LOGIN, "").equals("") && !mUser.getString(APP_PREFERENCES_PASSWORD, "").equals("")) {   //проверка не пыстые ли поля
                NetworkServiceRequests.getInstance().getJSONUserApi().getUser(mUser.getString(APP_PREFERENCES_LOGIN, ""), mUser.getString(APP_PREFERENCES_PASSWORD, "")) //отправляет запрос на авторизацию в случае если пользователь авторизован
                        .subscribeOn(Schedulers.io()) //Schedulers.io()
                        .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(User user) {
                                userMain = user;
                                System.out.println();
                                System.out.println("Здесь твои переменные: " + user.getUserId() + ", " + user.getP2());
                                if(userMain != null) {  //проверяет получен ли пользователь
                                    LogIn(userMain);    //вход в приложение
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                saver.setVisibility(View.GONE);                                     //подразумевается спад заглушки при уже авторизованном пользователе но при этом ошибки повторного входа
                                System.out.println("Произошла ошибка входа");//TODO: обязательно прописать подобное на экране
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        } else {
            saver.setVisibility(View.GONE);                                                         //заглушка спадает в случае неудачной проверки
        }
        signInButton = findViewById(R.id.email_sign_in_button);
        login = findViewById(id.field_login);
        password = findViewById(id.field_password);

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
            if (password.getText().toString().equals("")) {                                         //проверки полей на наличие текста
                //код если поле пусто
            } else {
                //код если текст есть
                varLogin = login.getText().toString();
                varPassword = password.getText().toString();                                        //занес в переменные для удобства
                NetworkServiceRequests.getInstance().getJSONUserApi().getUser(varLogin, varPassword)//отправка запроса по введенным данным
                        .subscribeOn(Schedulers.io()) //Schedulers.io()
                        .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(User user) {
                                userMain = user;
                                System.out.println();
                                System.out.println("Здесь твои переменные: " + user.getUserId() + ", " + user.getP2());

                                if(userMain != null & userMain.isSuccessful()) {                    //при успешной авторизации данные заносятся в SharedPreferences
                                    SharedPreferences.Editor editor = mUser.edit();
                                    editor.putString(APP_PREFERENCES_LOGIN, varLogin);
                                    editor.putString(APP_PREFERENCES_PASSWORD, varPassword);
                                    editor.apply();
                                    LogIn(userMain);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }

        //нужно отправить запрос на сервер
        //получить idшник и работать с ним
    }

    public void LogIn(User user) {//запуск главной активности
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
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

