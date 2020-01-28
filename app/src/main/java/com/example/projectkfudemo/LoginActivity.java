package com.example.projectkfudemo;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import static com.example.projectkfudemo.R.*;


import static com.example.projectkfudemo.MainActivity.EXTRA_AССESS;

public class LoginActivity extends AppCompatActivity {


    private BaseActivity baseActivity = new BaseActivity();

    public void showProgressDialog() {
        baseActivity.showProgressDialog();
    }

    public void hideProgressDialog() {
        baseActivity.hideProgressDialog();
    }

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mEmailSetText;
    private EditText mPasswordSetText;
    private Button mSignIn;
    public FirebaseUser user;

    private static final String USER_KEY = "user key";
    public final String Auth_TAG = "Log by FireBase: ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSignIn = findViewById(id.email_sign_in_button);
        mEmailSetText = findViewById(id.field_email);
        mPasswordSetText = findViewById(id.field_password);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    Log.d(Auth_TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                }
                else {
                    Log.d(Auth_TAG, "onAuthStateChanged:signed_out");
                }
                updateUI(user);
            }
        };
    }

    @Override
    public void onStart() {
           super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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


    private boolean valdateForm() {                                                                 //прописано
        boolean valid = true;

        String email = mEmailSetText.getText().toString();
        if(TextUtils.isEmpty(email)) {
            mEmailSetText.setError("Required.");
            valid = false;
        }
        else {
            mEmailSetText.setError(null);
        }

        String password = mPasswordSetText.getText().toString();
        if(TextUtils.isEmpty(password)) {
            valid = false;
        }
        else {
            mPasswordSetText.setError(null);
        }

        return valid;
    }

    private void mSignOnClick() {
        String email = mEmailSetText.getText().toString();
        String password = mPasswordSetText.getText().toString();

        Log.d(Auth_TAG, "signIn: " + email);
        if(!valdateForm()) {                                                                        //прописан
            return;
        }

        showProgressDialog();                                                                       //прописан

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(Auth_TAG, "signInWithEmail:onComplete: " + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.w(Auth_TAG, "signInWithEmail:failed", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }

                /*if(!task.isSuccessful()) {
                    mStatusTextView.setText("Authentication failed");
                }*/
                hideProgressDialog();
            }
        });

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //intent.putExtra(USER_KEY, );

    }

    public void createAcount() {

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case id.email_sign_in_button:
                mSignOnClick();
                break;
        }
    }

}

