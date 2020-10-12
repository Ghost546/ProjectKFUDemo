package com.example.projectkfudemo.architecturalcomponents.ui.currenttask;

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

import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity;
import com.example.projectkfudemo.architecturalcomponents.models.NetworkServiceRequests;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.requests.RequestList;
import com.example.projectkfudemo.architecturalcomponents.models.RequestStateAdapter;
import com.example.projectkfudemo.architecturalcomponents.models.Search;
import com.example.projectkfudemo.parametrclasses.User;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CurrentTaskFragment extends Fragment implements Serializable {

    static private Bundle args;
    private Bundle saveRequests;
    MainActivity mainActivity;
    private boolean mAlreadyLoaded = false;
    private boolean firstLoad = true;

    private final String REQUEST_LIST_SAVING_KEY = "requestListSavingKey";

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
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(REQUEST_LIST_SAVING_KEY, (Serializable) states);
        super.onSaveInstanceState(outState);
    }

    private boolean getAlreadyLoaded() {
        return mAlreadyLoaded;
    }

    private void setAlreadyLoaded(boolean mAlreadyLoaded) {
        this.mAlreadyLoaded = mAlreadyLoaded;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        currentTaskViewModel = ViewModelProviders.of(this).get(CurrentTaskViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_current_task_list, container, false);



        mainActivity = (MainActivity) getActivity();


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

        // Вызываем адаптер
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                if (savedInstanceState != null) {
                    states = (List<Request>) savedInstanceState.getSerializable(REQUEST_LIST_SAVING_KEY);
                    requestAdapter = new RequestStateAdapter(inflater.getContext(), R.layout.task, states);
                    requestListView.setAdapter(requestAdapter);
                } else {
                    if(getAlreadyLoaded()) {
                        setAlreadyLoaded(false);
                    }
                    else {
                        requestListView = getRequestListView(inflater, selectedItemPosition);
                    }
                }

                if(firstLoad) {
                    requestListView = getRequestListView(inflater, selectedItemPosition);
                    firstLoad = false;
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        searchEditText = rootView.findViewById(R.id.search_current_task_edit_text);
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
                } else {
                    requestAdapter = new RequestStateAdapter(inflater.getContext(), R.layout.task, states);
                }
                requestListView.setAdapter(requestAdapter);
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

        if (savedInstanceState == null && !getAlreadyLoaded()) {
            setAlreadyLoaded(true);
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mainActivity != null) {
            mainActivity.switchSelectedItemCurrentTask();
        }
    }
}