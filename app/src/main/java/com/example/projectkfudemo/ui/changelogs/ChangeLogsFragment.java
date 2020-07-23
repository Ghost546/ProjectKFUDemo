package com.example.projectkfudemo.ui.changelogs;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.Utility;
import com.example.projectkfudemo.ui.changelogs.cardlists.ApplicationAdapter;
import com.example.projectkfudemo.ui.changelogs.cardlists.PerformersAdapter;
import com.example.projectkfudemo.ui.changelogs.cardlists.ResponsibleAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeLogsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeLogsFragment extends Fragment {

//    private static Bundle arg;

    private Request request;
    private ListView responsibleForTheExecutionOfTheApplicationList;
    private ListView applicationLifeCycleList;
    private ListView performersList;


    public static ChangeLogsFragment newInstance(Request request) {
        ChangeLogsFragment fragment = new ChangeLogsFragment();
        fragment.request = request;
        return fragment;
    }

    private void setIds(View root) {
        responsibleForTheExecutionOfTheApplicationList = root.findViewById(R.id.responsible_for_the_execution_of_the_application_list);
        performersList = root.findViewById(R.id.performers_list);
        applicationLifeCycleList = root.findViewById(R.id.application_life_cycle_list);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_change_logs, container, false);
        if(request == null) {
            System.out.println("Заявки нет");
        } else {
            System.out.println("Заявка есть, размер worksList: " + request.getWorksList().size());
        }
        setIds(root);

        ResponsibleAdapter responsibleAdapter = new ResponsibleAdapter(inflater.getContext(), R.layout.logs_list_item, request.getWorksList());
        PerformersAdapter performersAdapter = new PerformersAdapter(inflater.getContext(), R.layout.performers_list_item, request.getWorkersList());
        ApplicationAdapter applicationAdapter = new ApplicationAdapter(inflater.getContext(), R.layout.application_list_item, request.getActionsOverRequest());
        responsibleForTheExecutionOfTheApplicationList.setAdapter(responsibleAdapter);
        performersList.setAdapter(performersAdapter);
        applicationLifeCycleList.setAdapter(applicationAdapter);
        Utility.setListViewHeightBasedOnChildren(responsibleForTheExecutionOfTheApplicationList);
        Utility.setListViewHeightBasedOnChildren(performersList);
        Utility.setListViewHeightBasedOnChildren(applicationLifeCycleList);
        setTable();

        return root;
    }

}