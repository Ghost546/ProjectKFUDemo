package com.example.projectkfudemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectkfudemo.ui.currenttask.CurrentTaskFragment;
import com.example.projectkfudemo.ui.menu.MenuFragment;
import com.example.projectkfudemo.ui.mytask.MyTaskFragment;
import com.example.projectkfudemo.ui.requestgeneralview.RequestGeneralViewFragment;
import com.example.projectkfudemo.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_AССESS = "com.example.";
    Fragment selectedFragment;
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseUser mFirebaseUser;
//    FirebaseUser user;
    Bundle args;
    User userMain = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            FragmentTransaction fragmentTransaction;

            switch (item.getItemId()) {
                case R.id.navigation_current_task:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    selectedFragment = CurrentTaskFragment.newInstance(args);
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_my_task:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    selectedFragment = MyTaskFragment.newInstance(args);
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_search:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    selectedFragment = SearchFragment.newInstance(args);
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_menu:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    selectedFragment = MenuFragment.newInstance(args);
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        args = getIntent().getExtras();
        userMain = (User) args.getSerializable("user");

        if (userMain == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        if (savedInstanceState == null) {
            // при первом запуске программы
            selectedFragment = CurrentTaskFragment.newInstance(args);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
           // добавляем в контейнер при помощи метода add()
            fragmentTransaction.add(R.id.fragment_container, selectedFragment);
            fragmentTransaction.commit();
        }
    }


    @NotNull
    public void startFragmentGeneralView(@NotNull Request request) {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        Fragment selectedFragment = RequestGeneralViewFragment.newInstance(request);
        fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
        fragmentTransaction.commit();
    }

//            mFirebaseAuth = FirebaseAuth.getInstance();
//            mFirebaseUser = mFirebaseAuth.getCurrentUser();
//            if (mFirebaseUser == null) {
//                // Not signed in, launch the Sign In activity
//                startActivity(new Intent(this, LoginActivity.class));
//                finish();
//                return;
//            } else {
//                user = mFirebaseUser.getDisplayName();
//                if (mFirebaseUser.getPhotoUrl() != null) {
//                    mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
//                }
}


