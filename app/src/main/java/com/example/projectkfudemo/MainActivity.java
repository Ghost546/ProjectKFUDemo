package com.example.projectkfudemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.projectkfudemo.ui.currenttask.CurrentTaskFragment;
import com.example.projectkfudemo.ui.menu.MenuFragment;
import com.example.projectkfudemo.ui.mytask.MyTaskFragment;
import com.example.projectkfudemo.ui.requestgeneralview.RequestGeneralViewFragment;
import com.example.projectkfudemo.ui.search.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "User";

    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_PASSWORD = "Password";

    public static final String EXTRA_AССESS = "com.example.";
    Fragment selectedFragment;
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseUser mFirebaseUser;
//    FirebaseUser user;
    Bundle args;
    User userMain = null;
    SharedPreferences userPreferences;

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
                    selectedFragment = MapFragment.newInstance(args);
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
        System.out.println("Здесь твои переменные: " + userMain.getUserId() + ", " + userMain.getP2());



        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        System.out.println("Здесь твои переменные: " + userMain.getUserId() + ", " + userMain.getP2());

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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        
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


