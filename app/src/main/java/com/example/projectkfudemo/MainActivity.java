package com.example.projectkfudemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.example.projectkfudemo.forjson.SearchDeclarerList;
import com.example.projectkfudemo.forjson.SearchWorkersList;
import com.example.projectkfudemo.ui.changelogs.ChangeLogsFragment;
import com.example.projectkfudemo.ui.currenttask.CurrentTaskFragment;
import com.example.projectkfudemo.ui.globalsearch.GlobalSearchFragment;
import com.example.projectkfudemo.ui.globalsearch.GlobalSearchResultFragment;
import com.example.projectkfudemo.ui.menu.MenuFragment;
import com.example.projectkfudemo.ui.mytask.MyTaskFragment;
import com.example.projectkfudemo.ui.requestgeneralview.RequestGeneralViewFragment;
import com.example.projectkfudemo.ui.map.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "User";

    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_PASSWORD = "Password";

    //массив для фио исполнителя
    List<String> searchWorkersStrings;
    //массив для Заявку зарегистрировал
    List<String> searchDeclarerStrings;

    Fragment selectedFragment;
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseUser mFirebaseUser;
//    FirebaseUser user;
    Bundle args;
    User userMain = null;
    SharedPreferences userPreferences;
    BottomNavigationView navView;


    public void switchSelectedItemCurrentTask() {
        if(navView.getSelectedItemId()!=R.id.navigation_current_task) {
            navView.setSelectedItemId(R.id.navigation_current_task);
        }
    }


    public void switchSelectedItemMyTask() {
        if(navView.getSelectedItemId()!=R.id.navigation_my_task) {
            navView.setSelectedItemId(R.id.navigation_my_task);
        }
    }


    public void switchSelectedItemSearch() {
        if(navView.getSelectedItemId()!=R.id.navigation_global_search) {
            navView.setSelectedItemId(R.id.navigation_global_search);
        }
    }


    public void switchSelectedItemMap() {
        if(navView.getSelectedItemId()!=R.id.navigation_map) {
            navView.setSelectedItemId(R.id.navigation_map);
        }
    }


    public void switchSelectedItemMenu() {
        if(navView.getSelectedItemId()!=R.id.navigation_menu) {
            navView.setSelectedItemId(R.id.navigation_menu);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            FragmentTransaction fragmentTransaction;

            switch (item.getItemId()) {
                case R.id.navigation_current_task:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    selectedFragment = CurrentTaskFragment.newInstance(args);
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_my_task:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    selectedFragment = MyTaskFragment.newInstance(args);
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_global_search:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    selectedFragment = GlobalSearchFragment.newInstance(args);
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_map:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    selectedFragment = MapFragment.newInstance(args);
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_menu:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    selectedFragment = MenuFragment.newInstance(args);
                    fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    public void setWorkerArraysForSpinner(LayoutInflater inflater, User user) {
        NetworkServiceRequests.getInstance().getJSONWorkersListApi().getSearchWorkersList(user.getUserId())
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<SearchWorkersList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchWorkersList searchWorkersList) {
                        searchWorkersStrings = new ArrayList<>();
                        if(searchWorkersList.getWorkersList().size()>0) {
                            searchWorkersStrings = searchWorkersList.getWorkersList();
                        } else {
                            searchWorkersStrings.add("Cписок пуст");
                        }
//                        adapterRequestRegistration = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_spinner_item, searchDeclarerStrings);
//                        adapterRequestRegistration.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        spinnerRequestRegistration.setAdapter(adapterRequestRegistration);
                    }

                    @Override
                    public void onError(Throwable e) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setDeclarerArraysForSpinner(LayoutInflater inflater, User user) {
        NetworkServiceRequests.getInstance().getJSONDeclarerListApi().getSearchDeclarerList(user.getUserId())
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<SearchDeclarerList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchDeclarerList searchDeclarerList) {
                        searchDeclarerStrings = new ArrayList<>();
                        if(searchDeclarerList.getDeclarersList().size()>0) {
                            searchDeclarerStrings = searchDeclarerList.getDeclarersList();
                        } else {
                            //сообщение что массив пустой
                        }
//
                    }

                    @Override
                    public void onError(Throwable e) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public List<String> getSearchWorkersStrings() {
        return searchWorkersStrings;
    }

    public List<String> getSearchDeclarerStrings() {
        return searchDeclarerStrings;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        args = getIntent().getExtras();
        userMain = (User) args.getSerializable("user");
        System.out.println("Здесь твои переменные: " + userMain.getUserId() + ", " + userMain.getP2());



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

        switchSelectedItemCurrentTask();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishAffinity();
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

    @NotNull
    public void startFragmentChangeLogsView(Request request) {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        Fragment selectedFragment = ChangeLogsFragment.newInstance(request);
        fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
        fragmentTransaction.commit();
    }

    @NotNull
    public void startFragmentGlobalSearchResult(RequestList requestList) {
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        Fragment selectedFragment = GlobalSearchResultFragment.newInstance(requestList);
        fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        
    }


}


