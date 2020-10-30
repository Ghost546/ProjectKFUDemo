package com.example.projectkfudemo.architecturalcomponents.ui.mytask;

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

public class MyTaskFragment extends Fragment {

    static Bundle args;
    MainActivity mainActivity;
    private boolean mAlreadyLoaded = false;
    private boolean firstLoad = true;

    private final String REQUEST_LIST_SAVING_KEY = "requestListSavingKey";

    private List<Request> states = new ArrayList();

    private EditText searchEditText;

    private volatile RequestStateAdapter requestAdapter = null;

    private ListView requestListView = null;

    public static MyTaskFragment newInstance(Bundle arg) {
        MyTaskFragment fragment = new MyTaskFragment();
        args = arg;
        return fragment;
    }

    public ListView getRequestListView(LayoutInflater inflater, int position) {
        User user = (User) args.getSerializable("user");
        NetworkServiceRequests.getInstance().getJSONUserRequestApi().getRequestWithLoginPassword(user.getUserId(), user.getP2(), position)
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<RequestList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RequestList requestList) {
                        states = requestList.getRequests();
                        FirebaseCrashlytics.getInstance().log("Пришел пустой массив на вывод! В текущих заявках. Class MyTaskFragment метод getRequestListView");
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        myTaskViewModel = ViewModelProviders.of(this).get(MyTaskViewModel.class);

        View rootView = inflater.inflate(R.layout.fragment_my_task_list, container, false);
        mainActivity = (MainActivity) getActivity();

        User user = (User) args.getSerializable("user");

        System.out.println("Здесь твои переменные: " + user.getUserId() + ", " + user.getP2());
        Spinner categorySpinner = (Spinner) rootView.findViewById(R.id.status);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(inflater.getContext(), R.array.statuses_my_tasks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        searchEditText = rootView.findViewById(R.id.search_my_task_edit_text);
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

        // Вызываем адаптер
        categorySpinner.setAdapter(adapter);
        categorySpinner.setSelection(4);
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

        // получаем элемент ListView
        requestListView = rootView.findViewById(R.id.myTasksList);

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // получаем выбранный пункт
                Request selectedRequest = (Request) parent.getItemAtPosition(position);
                selectedRequest.setThatIsMyRequest();
                //настраиваем и отправляем будущий фрагмент
                //запускаем фрагмент
                if (selectedRequest != null) {
                    mainActivity.startFragmentGeneralView(selectedRequest);
                } else {
                    System.out.println("selectedRequest is null");
                }
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
            mainActivity.switchSelectedItemMyTask();
        }
    }

}