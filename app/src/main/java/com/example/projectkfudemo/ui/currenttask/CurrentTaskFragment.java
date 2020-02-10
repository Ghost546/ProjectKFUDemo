package com.example.projectkfudemo.ui.currenttask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectkfudemo.CurrentRequestStateAdapter;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class CurrentTaskFragment extends Fragment {


    private List<Request> states = new ArrayList<>();

    CurrentTaskViewModel currentTaskViewModel;

    ListView requestList;

    public static CurrentTaskFragment newInstance() {
        CurrentTaskFragment fragment = new CurrentTaskFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currentTaskViewModel = ViewModelProviders.of(this).get(CurrentTaskViewModel.class);
        View root = inflater.inflate(R.layout.fragment_current_task, container, false);

        Request request1 = new Request();
        request1.setId(12345);
        request1.setTextOfRequest("It is text. Nut");
        request1.setStatusOfRequest("I'm ready");
        request1.setPeriodOfExecution(14);

        states.add(request1); //добавляем элемент в массив

        // начальная инициализация списка
        // создаем адаптер
        CurrentRequestStateAdapter stateAdapter = new CurrentRequestStateAdapter(inflater.getContext(), R.layout.task, states); // getActivity?

        // получаем элемент ListView
        requestList = root.findViewById(R.id.currentTasksList);
        // устанавливаем адаптер
        requestList.setAdapter(stateAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // получаем выбранный пункт
                Request selectedRequest = (Request) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedRequest.getId(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        requestList.setOnItemClickListener(itemListener);

        return root;

    }


}