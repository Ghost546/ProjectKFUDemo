package com.example.projectkfudemo.architecturalcomponents.ui.globalsearch;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.requests.RequestList;
import com.example.projectkfudemo.architecturalcomponents.models.RequestStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class GlobalSearchResultFragment extends Fragment {

    private TextView resultEmpty;
    private volatile RequestStateAdapter requestAdapter = null;
    private RequestList requestList;
    List<Request> list = new ArrayList<>();
    private ListView listView;

    public static GlobalSearchResultFragment newInstance(RequestList requestList) {
        GlobalSearchResultFragment fragment = new GlobalSearchResultFragment();
        fragment.requestList = requestList;
        return fragment;
    }

    public void setId(View rootView) {
        resultEmpty = rootView.findViewById(R.id.result_empty);
        listView = rootView.findViewById(R.id.result_list_view);
    }

    public void setListView(RequestList requestList, LayoutInflater inflater) {
        if(requestList!=null) { //проверка на наличие заявок
            list = requestList.getRequests();   //выполняется отображение зявок
            requestAdapter = new RequestStateAdapter(inflater.getContext(), R.layout.task, list);
            listView.setAdapter(requestAdapter);
        } else {
            listView.setVisibility(View.GONE);  //показывается текст что заявок по поиску нет
            resultEmpty.setVisibility(View.VISIBLE);
            resultEmpty.setText("По результатам поиска заявки отсутсвуют");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_global_search_result, container, false);
        setId(rootView);
        setListView(requestList, inflater);
        if(list.size()>0) {
            // слушатель выбора в списке
            AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    // получаем выбранный пункт
                    Request selectedRequest = (Request) parent.getItemAtPosition(position);
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

            listView.setOnItemClickListener(itemListener);
        }


        return rootView;
    }

}