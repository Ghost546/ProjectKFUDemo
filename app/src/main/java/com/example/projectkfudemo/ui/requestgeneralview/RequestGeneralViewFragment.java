package com.example.projectkfudemo.ui.requestgeneralview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.CurrentRequest;
import com.example.projectkfudemo.MyRequest;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;

public class RequestGeneralViewFragment extends Fragment {

    private TextView requestIdBlock;
    private TextView requestId;
    private TextView requestRegistrationDateBlock;
    private TextView requestRegistrationDate;
    private TextView periodOfExecutionBlock;
    private TextView periodOfExecution;
    private TextView statusOfRequestBlock;
    private TextView statusOfRequest;
    private TextView acceptedTheRequestBlock;
    private TextView acceptedTheRequest;
    private TextView declarerBlock;
    private TextView declarer;
    private TextView subdivisionBlock;
    private TextView subdivision;
    private TextView dataAboutDeclarerBlock;
    private TextView dataAboutDeclarer;
    private TextView textOfRequestBlock;
    private TextView textOfRequest;
    private TextView responsibleForTheExecutionOfTheRequestBlock;
    private TextView responsibleForTheExecutionOfTheRequest;
    private TextView actionsOverRequestBlock;
    private TextView actionsOverRequest;


    public static RequestGeneralViewFragment newInstance(Request request) {
        RequestGeneralViewFragment fragment = new RequestGeneralViewFragment();
        RequestGeneralViewFragment requestGeneralViewFragment = new RequestGeneralViewFragment();
        requestGeneralViewFragment.SendRequestSetting(request);
        return fragment;
    }

    private void SendRequestSetting(Request request) {
        if(request.getThatIsCurrentRequest()) {
            CurrentRequest request1 = new CurrentRequest(request);
            VisibleSetting(request1);
        }
        if(request.getThatIsMyRequest()) {
            MyRequest request1 = new MyRequest(request);
            VisibleSetting(request1);
        }
    }

    private void VisibleSetting(CurrentRequest request) {                                           //настраивает фрагмент для отображения в виде текущей заявки
        if(request.getRequestId()!=0) {
            requestIdBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getAcceptedTheRequest().equals("")) {
            requestRegistrationDateBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getRequestRegistrationDate().equals("")) {
            requestRegistrationDateBlock.setVisibility(View.VISIBLE);
        }
        if(request.getPeriodOfExecutionInDateFormat()!=null) {
            periodOfExecutionBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getDeclarer().equals("")) {
            declarerBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getSubdivision().equals("")) {
            subdivisionBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getDataAboutDeclarer().equals("")) {
            dataAboutDeclarerBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getTextOfRequest().equals("")) {
            textOfRequestBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getResponsibleForTheExecutionOfTheRequest().equals("")) {
            responsibleForTheExecutionOfTheRequestBlock.setVisibility(View.VISIBLE);
        }
    }

    private void VisibleSetting(MyRequest request) {                                                //настраивает фрагмент для отображения в виде текущей заявки
        if(request.getRequestId()!=0) {
            requestIdBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getRequestRegistrationDate().equals("")) {
            requestRegistrationDateBlock.setVisibility(View.VISIBLE);
        }
        if(request.getPeriodOfExecutionInDateFormat()!=null) {
            periodOfExecutionBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getDeclarer().equals("")) {
            declarerBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getSubdivision().equals("")) {
            subdivisionBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getDataAboutDeclarer().equals("")) {
            dataAboutDeclarerBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getTextOfRequest().equals("")) {
            textOfRequestBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getResponsibleForTheExecutionOfTheRequest().equals("")) {
            responsibleForTheExecutionOfTheRequestBlock.setVisibility(View.VISIBLE);
        }
        if(!request.getActionsOverRequest().equals("")) {
            actionsOverRequestBlock.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setIds(View root) {
        requestIdBlock=root.findViewById(R.id.request_id_block);
        requestId=root.findViewById(R.id.request_id);
        requestRegistrationDateBlock=root.findViewById(R.id.request_registration_date_block);
        requestRegistrationDate=root.findViewById(R.id.request_registration_date);
        periodOfExecutionBlock=root.findViewById(R.id.period_of_execution_block);
        periodOfExecution=root.findViewById(R.id.period_of_execution);
        statusOfRequestBlock=root.findViewById(R.id.status_of_request_block);
        statusOfRequest=root.findViewById(R.id.status_of_request);
        acceptedTheRequestBlock=root.findViewById(R.id.accepted_the_request_block);
        acceptedTheRequest=root.findViewById(R.id.accepted_the_request);
        declarerBlock=root.findViewById(R.id.declarer_block);
        declarer=root.findViewById(R.id.declarer);
        subdivisionBlock=root.findViewById(R.id.subdivision_block);
        subdivision=root.findViewById(R.id.subdivision);
        dataAboutDeclarerBlock=root.findViewById(R.id.data_about_declarer_block);
        dataAboutDeclarer=root.findViewById(R.id.data_about_declarer);
        textOfRequestBlock=root.findViewById(R.id.text_of_request_block);
        textOfRequest=root.findViewById(R.id.text_of_request);
        responsibleForTheExecutionOfTheRequestBlock=root.findViewById(R.id.responsible_for_the_execution_of_the_request_block);
        responsibleForTheExecutionOfTheRequest=root.findViewById(R.id.responsible_for_the_execution_of_the_request);
        actionsOverRequestBlock=root.findViewById(R.id.actions_over_request_block);
        actionsOverRequest=root.findViewById(R.id.actions_over_request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_general_view_request, container, false);
        setIds(root);

        return root;
    }
}
