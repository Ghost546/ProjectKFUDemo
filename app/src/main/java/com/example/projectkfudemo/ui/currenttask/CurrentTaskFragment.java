package com.example.projectkfudemo.ui.currenttask;

import android.app.job.JobService;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.projectkfudemo.CurrentRequestStateAdapter;
import com.example.projectkfudemo.MainActivity;
import com.example.projectkfudemo.NetworkService;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.RequestList;
import com.example.projectkfudemo.User;
import com.example.projectkfudemo.ui.requestgeneralview.RequestGeneralViewFragment;
import com.google.firebase.database.annotations.NotNull;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;


public class CurrentTaskFragment extends Fragment {
    static private Bundle args;
    private List<Request> states = new ArrayList<>();

    private volatile CurrentRequestStateAdapter requestAdapter = null;

    private ListView requestListView = null;

    public static CurrentTaskFragment newInstance(Bundle arg) {
        CurrentTaskFragment fragment = new CurrentTaskFragment();
        args = arg;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        currentTaskViewModel = ViewModelProviders.of(this).get(CurrentTaskViewModel.class);
        View root = inflater.inflate(R.layout.fragment_current_task_list, container, false);

//        if(getArguments() != null) {
//            if(getArguments().containsKey("list")){
//                getArguments().getString("list");
//            }else{
//                //server
//            }
//        }

        User user = (User) args.getSerializable("user");

        int user_id = user.getUserId();
        String p2= user.getP2();
        NetworkService.getInstance().getJSONRequestApi().getRequestWithLoginPassword(user_id, p2)
                .subscribeOn(Schedulers.io()) //Schedulers.io()
                .observeOn(AndroidSchedulers.mainThread()) //AndroidSchedulers.mainThread()
                .subscribe(new Observer<RequestList>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(RequestList requestList) {
                            states = requestList.getRequests();
                            requestAdapter = new CurrentRequestStateAdapter(inflater.getContext(), R.layout.task, states);
                            requestListView.setAdapter(requestAdapter);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                });

//        if (getRequests!=null)
//            getRequests.dispose();
//
//        NetworkService.getInstance().getJSONApi().getRequestWithLoginPassword().equals(new Callback<ArrayList<RequestList>>() {
//            @Override
//            public void onResponse(Call<ArrayList<RequestList>> call, Response<ArrayList<RequestList>> response) {
//                if (response.isSuccessful()) {
//                    states = (List<Request>) requestList;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<RequestList>> call, Throwable t) {
//                System.out.print("Error occurred while getting request!");
//                t.printStackTrace();
//            }
//        });
//
//
//         начальная инициализация списка
//         создаем адаптер
//          getActivity?
//
//         получаем элемент ListView
        requestListView = root.findViewById(R.id.currentTasksList);
        // устанавливаем адаптер
//        while (requestAdapter == null) {
//            //по идее ожидание дропа данных с другого потока
//        }


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
        return root;
    }
//    private AdapterView.OnItemClickListener setMyAdapter(ListView requestList) {
//        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                // получаем выбранный пункт
//                Request selectedRequest = (Request) parent.getItemAtPosition(position);
//                selectedRequest.setThatIsCurrentRequest();
//                //настраиваем и отправляем будущий фрагмент
//                MainActivity mainActivity = (MainActivity) getActivity();
//                //запускаем фрагмент
//                if (selectedRequest != null) {
////                    Gson gson = new Gson();
////                    getArguments().putString("list",gson.toJson(states));
//                    mainActivity.startFragmentGeneralView(selectedRequest);
//                } else {
//                    System.out.println("selectedRequest is null");
//                }
//                //((MainActivity)getActivity())
//            }
//        };
//        return itemListener;
//    }


}