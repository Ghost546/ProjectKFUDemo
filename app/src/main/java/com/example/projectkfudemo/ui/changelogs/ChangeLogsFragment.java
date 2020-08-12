package com.example.projectkfudemo.ui.changelogs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.forjson.Workers;
import com.example.projectkfudemo.ui.changelogs.cardlists.ApplicationAdapter;
import com.example.projectkfudemo.ui.changelogs.cardlists.PerformersAdapter;
import com.example.projectkfudemo.ui.changelogs.cardlists.ResponsibleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeLogsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeLogsFragment extends Fragment {

//    private static Bundle arg;

    private Request request;
    private ListView listView;
    private Spinner spinner;


    public static ChangeLogsFragment newInstance(Request request) {
        ChangeLogsFragment fragment = new ChangeLogsFragment();
        fragment.request = request;
        return fragment;
    }

    private void setIds(View root) {
        spinner = root.findViewById(R.id.change_logs_spinner);
        listView = root.findViewById(R.id.change_logs_list_view);
    }

    private void setView(Request request) {
        String text = "";
        for(int i = 0; i < request.getActionsOverRequest().size(); i++) {
            text += request.getActionsOverRequest().get(i).getComment() + "\n";
            text += "\n";
        }

    }

    public void setTable() {

    }



    private void setList(LayoutInflater inflater, int position) {
        if(position == 0) {
            ResponsibleAdapter responsibleAdapter = new ResponsibleAdapter(inflater.getContext(), R.layout.responsible_list_item, request.getWorksList());
            listView.setAdapter(responsibleAdapter);
        }
        if(position == 1) {
            PerformersAdapter performersAdapter = new PerformersAdapter(inflater.getContext(), R.layout.performers_list_item, request.getWorkersList());
            listView.setAdapter(performersAdapter);
        }
        if(position == 2) {
            ApplicationAdapter applicationAdapter = new ApplicationAdapter(inflater.getContext(), R.layout.application_list_item, request.getActionsOverRequest());
            listView.setAdapter(applicationAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_change_logs, container, false);
        if(request == null) {
            System.out.println("Заявки нет");
        } else {
            System.out.println("Заявка есть, размер worksList: " + request.getWorksList().size());
        }
        setIds(root);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(inflater.getContext(), R.array.change_logs_string_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                setList(inflater, selectedItemPosition);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        setTable();

        return root;
    }

}