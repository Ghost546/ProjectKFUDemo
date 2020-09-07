package com.example.projectkfudemo.ui.currenttask;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.MainActivity;
import com.example.projectkfudemo.NetworkServiceRequests;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.RequestList;
import com.example.projectkfudemo.RequestStateAdapter;
import com.example.projectkfudemo.Search;
import com.example.projectkfudemo.User;
import com.example.projectkfudemo.ui.JSONApiRequest;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CurrentTaskFragment extends Fragment {
    static private Bundle args;
    private List<Request> states = new ArrayList<>();

    private EditText searchEditText;
    
    private volatile RequestStateAdapter requestAdapter = null;

    private ListView requestListView = null;

    public static CurrentTaskFragment newInstance(Bundle arg) {
        CurrentTaskFragment fragment = new CurrentTaskFragment();
        args = arg;
        return fragment;
    }

    public ListView getRequestListView(LayoutInflater inflater, int position) {
        User user = (User) args.getSerializable("user");
        NetworkServiceRequests.getInstance().getJSONRequestApi().getRequestWithLoginPassword(user.getUserId(), user.getP2(), position)
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<RequestList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestList requestList) {
                        states = requestList.getRequests();
                        if (states.size() == 0) {
                            FirebaseCrashlytics.getInstance().log("Пришел пустой массив на вывод! В текущих заявках. Class CurrentTaskFragment метод getRequestListView");
//                            throw new RuntimeException("Test Crash");
                        }
                        System.out.println(states.size());
                        requestAdapter = new RequestStateAdapter(inflater.getContext(), R.layout.task, states);
                        requestListView.setAdapter(requestAdapter);
                        System.out.println("Операция пройдена");
                    }

                    @Override
                    public void onError(Throwable e) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return requestListView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        currentTaskViewModel = ViewModelProviders.of(this).get(CurrentTaskViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_current_task_list, container, false);



        User user = (User) args.getSerializable("user");
        if(user.getUserId() == 0) {
            FirebaseCrashlytics.getInstance().log("userId = 0 в полученных данных о пользователе");
            throw new RuntimeException("Test Crash");
        } else {
            FirebaseCrashlytics.getInstance().log(String.valueOf(user.getUserId()));
        }
        if(user.getP2() == null) {
            FirebaseCrashlytics.getInstance().log("p2 прилетел null");
            throw new RuntimeException("Test Crash");
        } else {
            FirebaseCrashlytics.getInstance().log(String.valueOf(user.getP2()));
        }
        if(user.getP2().equals("")) {
            FirebaseCrashlytics.getInstance().log("p2 прилетел без данных");
            throw new RuntimeException("Test Crash");
        } else {
            FirebaseCrashlytics.getInstance().log(String.valueOf(user.getP2()));
        }

        requestListView = rootView.findViewById(R.id.currentTasksList);

        Spinner categorySpinner = rootView.findViewById(R.id.status);


        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(inflater.getContext(), R.array.statuses_current_tasks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        searchEditText = rootView.findViewById(R.id.search_edit_text);
        searchEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(!String.valueOf(s).equals("")) {
                    Search search = new Search(String.valueOf(s), states);
                    requestAdapter = new RequestStateAdapter(inflater.getContext(), R.layout.task, search.getResultList());
                    requestListView.setAdapter(requestAdapter);
                } else {
                    requestAdapter = new RequestStateAdapter(inflater.getContext(), R.layout.task, states);
                    requestListView.setAdapter(requestAdapter);
                }
            }
        });

        // Вызываем адаптер
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                requestListView = getRequestListView(inflater, selectedItemPosition);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        //получаем элемент ListView

        // устанавливаем адаптер


        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // получаем выбранный пункт
                Request selectedRequest = (Request) parent.getItemAtPosition(position);
                selectedRequest.setThatIsCurrentRequest();
                //настраиваем и отправляем будущий фрагмент
                MainActivity mainActivity = (MainActivity) getActivity();
                //запускаем фрагмент
                if (selectedRequest != null) {
//                    Gson gson = new Gson();
//                    getArguments().putString("list",gson.toJson(states));
                    mainActivity.startFragmentGeneralView(selectedRequest);
                } else {
                    System.out.println("selectedRequest is null");
                }
                //((MainActivity)getActivity())
            }
        };

        requestListView.setOnItemClickListener(itemListener);
        return rootView;
    }



}