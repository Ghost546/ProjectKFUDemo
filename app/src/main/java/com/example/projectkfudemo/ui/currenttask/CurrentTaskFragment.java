package com.example.projectkfudemo.ui.currenttask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectkfudemo.CurrentRequest;
import com.example.projectkfudemo.CurrentRequestStateAdapter;
import com.example.projectkfudemo.MainActivity;
import com.example.projectkfudemo.NetworkService;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.ResponseRequest;
import com.example.projectkfudemo.getCurrentRequests;
import com.example.projectkfudemo.ui.requestgeneralview.RequestGeneralViewFragment;
import com.google.firebase.database.annotations.NotNull;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

class Test {
    int i = 0;

    int getI() {
        return this.i;
    }

    void setI(int i) {
        this.i = i;
    }
}
public class CurrentTaskFragment extends Fragment {


    private List<Request> states = new ArrayList<>();


    ListView requestList;

    public static CurrentTaskFragment newInstance() {
        CurrentTaskFragment fragment = new CurrentTaskFragment();
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

        int n = 1; //временно



        NetworkService.getInstance().getJSONApi().equals(new Callback<ArrayList<Request>>() {
            @Override
            public void onResponse(Call<ArrayList<Request>> call, Response<ArrayList<Request>> response) {
                if(response.isSuccessful()) {
                    states = response.body().getRequests;
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Request>> call, Throwable t) {
                System.out.print("Error occurred while getting request!");
                t.printStackTrace();
            }
        });

        NetworkService.getInstance().getJSONApi().getRequests().enqueue(new Callback<ResponseRequest>() {
            @Override
            public void onResponse(Call<ResponseRequest> call, Response<ResponseRequest> response) {

                var requests = call.ResponseRequest.requests
            }

            @Override
            public void onFailure(Call<ResponseRequest> call, Throwable t) {
                System.out.print("Error occurred while getting request!");
                t.printStackTrace();
            }
        });

        Request request1 = new Request();
        request1.setRequestId(12345);
        request1.setTextOfRequest("It is text. Nut");
        request1.setStatusOfRequest("I'm current request");
        request1.setPeriodOfExecution("2012-12-12");

        states.add(request1); //добавляем элемент в массив

        // начальная инициализация списка
        // создаем адаптер
        CurrentRequestStateAdapter requestAdapter = new CurrentRequestStateAdapter(inflater.getContext(), R.layout.task, states); // getActivity?

        // получаем элемент ListView
        requestList = root.findViewById(R.id.currentTasksList);
        // устанавливаем адаптер
        requestList.setAdapter(requestAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // получаем выбранный пункт
                Request selectedRequest = (Request) parent.getItemAtPosition(position);
                selectedRequest.setThatIsCurrentRequest();
                //настраиваем и отправляем будущий фрагмент
                MainActivity mainActivity = (MainActivity)getActivity();
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
        requestList.setOnItemClickListener(itemListener);



        return root;

    }
}