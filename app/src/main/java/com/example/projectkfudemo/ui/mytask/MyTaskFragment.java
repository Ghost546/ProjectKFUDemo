package com.example.projectkfudemo.ui.mytask;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class MyTaskFragment extends Fragment {

    private List<Request> states = new ArrayList();

    MyTaskViewModel myTaskViewModel;

    ListView requestList;

    public static MyTaskFragment newInstance() {
        MyTaskFragment fragment = new MyTaskFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        myTaskViewModel = ViewModelProviders.of(this).get(MyTaskViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_my_task_list, container, false);
        NetworkService.getInstance().getJSONApi().equals(new Callback<ArrayList<Request>>() {
            @Override
            public void onResponse(Call<ArrayList<Request>> call, Response<ArrayList<Request>> response) {
                if(response.isSuccessful()) {
                    states = response.body();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Request>> call, Throwable t) {
                System.out.print("Error occurred while getting request!");
                t.printStackTrace();
            }
        });

//        Request request1 = new Request();
//        request1.setRequestId(54321);
//        request1.setTextOfRequest("It is text. Nut");
//        request1.setStatusOfRequest("I'm your request");
//        request1.setPeriodOfExecution("2001-01-01");

//        states.add(request1); //добавляем элемент в массив

        // начальная инициализация списка
        // создаем адаптер
        CurrentRequestStateAdapter stateAdapter = new CurrentRequestStateAdapter(inflater.getContext(), R.layout.task, states); // getActivity?

        // получаем элемент ListView
        requestList = rootView.findViewById(R.id.myTasksList);
        // устанавливаем адаптер
        requestList.setAdapter(stateAdapter);
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
        requestList.setOnItemClickListener(itemListener);

        return rootView;
    }
}