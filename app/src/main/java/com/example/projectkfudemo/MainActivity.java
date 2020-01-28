package com.example.projectkfudemo;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.projectkfudemo.ui.currenttask.CurrentTaskFragment;
import com.example.projectkfudemo.ui.menu.MenuFragment;
import com.example.projectkfudemo.ui.mytask.MyTaskFragment;
import com.example.projectkfudemo.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_AССESS = "com.example.";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    FirebaseUser user;
    private List<Request> states = new ArrayList();

    ListView requestList;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_current_task:
                    selectedFragment = CurrentTaskFragment.newInstance();
                    return true;
                case R.id.navigation_my_task:
                    selectedFragment = MyTaskFragment.newInstance();
                    return true;
                case R.id.navigation_search:
                    selectedFragment = SearchFragment.newInstance();
                    return true;
                case R.id.navigation_menu:
                    selectedFragment = MenuFragment.newInstance();
                    return true;
            }
            return false;
        }
    };

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            BottomNavigationView navView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_current_task, R.id.navigation_my_task, R.id.navigation_search, R.id.navigation_menu)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navView, navController);

            // начальная инициализация списка
            // получаем элемент ListView
            requestList = findViewById(R.id.tasksList);
            // создаем адаптер
            CurrentRequestStateAdapter stateAdapter = new CurrentRequestStateAdapter(this, R.layout.task, states);
            // устанавливаем адаптер
            requestList.setAdapter(stateAdapter);
            // слушатель выбора в списке
            AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    // получаем выбранный пункт
                    Request selectedRequest = (Request) parent.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedRequest.getId(),
                            Toast.LENGTH_SHORT).show();
                }
            };
            requestList.setOnItemClickListener(itemListener);
        }


//            mFirebaseAuth = FirebaseAuth.getInstance();
//            mFirebaseUser = mFirebaseAuth.getCurrentUser();
//            if (mFirebaseUser == null) {
//                // Not signed in, launch the Sign In activity
//                startActivity(new Intent(this, LoginActivity.class));
//                finish();
//                return;
//            } else {
//                user = mFirebaseUser.getDisplayName();                                             // что блять такое UserName
//                if (mFirebaseUser.getPhotoUrl() != null) {
//                    mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
//                }
            }


