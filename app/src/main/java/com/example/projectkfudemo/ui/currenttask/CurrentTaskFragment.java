package com.example.projectkfudemo.ui.currenttask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.CurrentRequestStateAdapter;
import com.example.projectkfudemo.MainActivity;
import com.example.projectkfudemo.NetworkServiceRequests;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.RequestList;
import com.example.projectkfudemo.User;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


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
        NetworkServiceRequests.getInstance().getJSONRequestApi().getRequestWithLoginPassword(user.getUserId(), user.getP2())
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
                            System.out.println("Операция пройдена");
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
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