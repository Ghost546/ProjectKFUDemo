package com.example.projectkfudemo.ui.mytask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectkfudemo.CurrentRequest;
import com.example.projectkfudemo.MainActivity;
import com.example.projectkfudemo.NetworkServiceRequests;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.RequestList;
import com.example.projectkfudemo.RequestStateAdapter;
import com.example.projectkfudemo.User;
import com.example.projectkfudemo.forjson.Works;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class MyTaskFragment extends Fragment {

    static Bundle args;

    private List<Request> states = new ArrayList();

    private volatile RequestStateAdapter requestAdapter = null;

    MyTaskViewModel myTaskViewModel;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        myTaskViewModel = ViewModelProviders.of(this).get(MyTaskViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_my_task_list, container, false);
        User user = (User) args.getSerializable("user");
        System.out.println("Здесь твои переменные: " + user.getUserId() + ", " + user.getP2());
        Spinner categorySpinner = (Spinner) rootView.findViewById(R.id.status);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(inflater.getContext(), R.array.statuses_current_tasks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Вызываем адаптер
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                requestListView = getRequestListView(inflater, selectedItemPosition-1);
                requestListView = rootView.findViewById(R.id.myTasksList);
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
                MainActivity mainActivity = (MainActivity)getActivity();
                //запускаем фрагмент
                if (selectedRequest != null) {
                    mainActivity.startFragmentGeneralView(selectedRequest);
                } else {
                    System.out.println("selectedRequest is null");
                }
            }
        };
        requestListView.setOnItemClickListener(itemListener);

        return rootView;
    }
}