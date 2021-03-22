package com.example.projectkfudemo.architecturalcomponents.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.architecturalcomponents.ui.addcommenttorequest.AddCommentToRequestFragment;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.addcommenttorequestfragment.ViewModelAddCommentToRequest;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment.ViewModelGlobalSearch;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment.ViewModelGlobalSearchResult;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.mytaskfragment.ViewModelMyTask;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.currenttaskfragment.ViewModelCurrentTask;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.requestgeneralviewfragment.ViewModelRequestGeneralView;
import com.example.projectkfudemo.parametrclasses.User;
import com.example.projectkfudemo.architecturalcomponents.ui.changelogs.ChangeLogsFragment;
import com.example.projectkfudemo.architecturalcomponents.ui.currenttask.CurrentTaskFragment;
import com.example.projectkfudemo.architecturalcomponents.ui.globalsearch.GlobalSearchFragment;
import com.example.projectkfudemo.architecturalcomponents.ui.globalsearch.GlobalSearchResultFragment;
import com.example.projectkfudemo.architecturalcomponents.ui.menu.MenuFragment;
import com.example.projectkfudemo.architecturalcomponents.ui.mytask.MyTaskFragment;
import com.example.projectkfudemo.architecturalcomponents.ui.requestgeneralview.RequestGeneralViewFragment;
import com.example.projectkfudemo.architecturalcomponents.ui.map.MapFragment;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.mainactivity.ViewModelMainActivity;
import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.requests.RequestList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    public static final String APP_PREFERENCES = "User";

    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_PASSWORD = "Password";

    ViewModelMainActivity viewModelMainActivity;
    ViewModelCurrentTask viewModelCurrentTask;
    ViewModelMyTask viewModelMyTask;
    ViewModelGlobalSearch viewModelGlobalSearch;
    ViewModelGlobalSearchResult viewModelGlobalSearchResult;
    ViewModelAddCommentToRequest viewModelAddCommentToRequest;
    ViewModelRequestGeneralView viewModelRequestGeneralView;

    Fragment selectedFragment;
    public FragmentTransaction fragmentTransaction;
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseUser mFirebaseUser;
//    FirebaseUser user;
    public Bundle args;
    User userMain = null;
    SharedPreferences userPreferences;
    BottomNavigationView navView;


    public void switchSelectedItemCurrentTask() {
        if(navView.getSelectedItemId()!= R.id.navigation_current_task) {
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
//                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.addToBackStack(null);
//                    if(viewModelGlobalSearchResult.getLiveDataSearchResultListFromServer().getValue()==null) {
//                        Log.i(TAG, "!операция в BottomNavigationView. viewModelGlobalSearchResult.liveDataSearchResultListFromServer.requestList==null");
//                        selectedFragment = GlobalSearchFragment.newInstance(args);
//                        fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
//                        fragmentTransaction.commit();
//                    } else {
//                        startFragmentGlobalSearchResult(viewModelGlobalSearchResult.getLiveDataSearchResultListFromServer().getValue());
//                    }
                    startFragmentGlobalSearch();
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "!создал "+ TAG+"!");
        navView = findViewById(R.id.nav_view);
        args = getIntent().getExtras();
        userMain = (User) args.getSerializable("user");
        viewModelMainActivity = new ViewModelProvider(this).get(ViewModelMainActivity.class);
        viewModelMainActivity.setUser(userMain);
        if(viewModelCurrentTask==null) {
            viewModelCurrentTask = new ViewModelProvider(this).get(ViewModelCurrentTask.class);
            viewModelCurrentTask.setObject(userMain);
        }
        if(viewModelMyTask==null) {
            viewModelMyTask = new ViewModelProvider(this).get(ViewModelMyTask.class);
            viewModelMyTask.setObject(userMain);
        }
        if(viewModelGlobalSearch==null) {
            viewModelGlobalSearch = new ViewModelProvider(this).get(ViewModelGlobalSearch.class);
        }
        if(viewModelGlobalSearchResult==null) {
            viewModelGlobalSearchResult = new ViewModelProvider(this).get(ViewModelGlobalSearchResult.class);
            if(viewModelGlobalSearchResult!=null) {
                Log.i(TAG, "!viewModelGlobalSearchResult!=null");
            }

        }
        if(viewModelRequestGeneralView==null) {
            viewModelRequestGeneralView = new ViewModelProvider(this).get(ViewModelRequestGeneralView.class);
            if(viewModelRequestGeneralView!=null) {
                Log.i(TAG, "!viewModelRequestGeneralView!=null");
            }
        }
        if(viewModelAddCommentToRequest==null) {
            viewModelAddCommentToRequest = new ViewModelProvider(this).get(ViewModelAddCommentToRequest.class);
            if(viewModelAddCommentToRequest!=null) {
                Log.i(TAG, "!viewModelAddCommentToRequest!=null");
            }
        }


        Log.i(TAG, "!из " + TAG + " отправил userMain!");

        viewModelMainActivity.setObjectForRequests();
        viewModelMainActivity.requestOnSetDataAboutSpinners();

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
        Fragment selectedFragment = RequestGeneralViewFragment.newInstance(request, args);
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
    public void startFragmentGlobalSearch() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        if(viewModelGlobalSearchResult.getLiveDataSearchResultListFromServer().getValue()==null) {
            Log.i(TAG, "!операция в BottomNavigationView. viewModelGlobalSearchResult.liveDataSearchResultListFromServer.requestList==null");
            selectedFragment = GlobalSearchFragment.newInstance(args);
            fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
            fragmentTransaction.commit();
        } else {
            startFragmentGlobalSearchResult(viewModelGlobalSearchResult.getLiveDataSearchResultListFromServer().getValue());
        }
    }

    @NotNull
    public void startFragmentGlobalSearchResult(RequestList requestList) {
        Log.i(TAG, "!Выполнение startFragmentGlobalSearchResult");
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        if(viewModelGlobalSearchResult==null) {
            Log.i(TAG, "!viewModelGlobalSearchResult == null");
        } else {
            Log.i(TAG, "!Хэш-код viewModelGlobalSearchResult: " + viewModelGlobalSearchResult.hashCode());
        }
        Fragment selectedFragment = GlobalSearchResultFragment.newInstance(requestList);
        fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
        fragmentTransaction.commit();
    }

    @NotNull
    public void startFragmentAddCommentToRequest(Request request) {
        Log.i(TAG, "!Выполнение startFragmentAddCommentToRequest");
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        if(viewModelAddCommentToRequest==null) {
            Log.i(TAG, "!viewModelAddCommentToRequest == null");
        } else {
            Log.i(TAG, "!Хэш-код viewModelAddCommentToRequest" + viewModelAddCommentToRequest.hashCode());
        }
        Fragment selectedFragment = AddCommentToRequestFragment.Companion.newInstance(request);
        fragmentTransaction.replace(R.id.fragment_container, selectedFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;
        for (Fragment fragment: fm.getFragments()) {
            if (fragment instanceof  OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                if(getFragmentManager().getBackStackEntryCount() == 1) {
                    moveTaskToBack(false);
                } else {
                    super.onBackPressed();
                }
                break;
            }
        }

        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }


    }

    public FragmentTransaction getFragmentTransaction() {
        return fragmentTransaction;
    }

    public ViewModelMainActivity getViewModelMainActivity() {
        return viewModelMainActivity;
    }

    public ViewModelCurrentTask getViewModelCurrentTask() {
        return viewModelCurrentTask;
    }

    public ViewModelMyTask getViewModelMyTask() {
        return viewModelMyTask;
    }

    public ViewModelGlobalSearch getViewModelGlobalSearch() {
        return viewModelGlobalSearch;
    }

    public ViewModelGlobalSearchResult getViewModelGlobalSearchResult() {
        return viewModelGlobalSearchResult;
    }

    public ViewModelAddCommentToRequest getViewModelAddCommentToRequest() {
        return viewModelAddCommentToRequest;
    }

    public ViewModelRequestGeneralView getViewModelRequestGeneralView() {
        return viewModelRequestGeneralView;
    }
}


