package com.example.projectkfudemo.ui.requestgeneralview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.CurrentRequest;
import com.example.projectkfudemo.MyRequest;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;

public class RequestGeneralViewFragment extends Fragment {

    private LinearLayout requestIdBlock;
    private TextView requestId;
    private LinearLayout requestRegistrationDateBlock;
    private TextView requestRegistrationDate;
    private LinearLayout periodOfExecutionBlock;
    private TextView periodOfExecution;
    private LinearLayout statusOfRequestBlock;
    private TextView statusOfRequest;
    private LinearLayout acceptedTheRequestBlock;
    private TextView acceptedTheRequest;
    private LinearLayout declarerBlock;
    private TextView declarer;
    private LinearLayout subdivisionBlock;
    private TextView subdivision;
    private LinearLayout dataAboutDeclarerBlock;
    private TextView dataAboutDeclarer;
    private LinearLayout textOfRequestBlock;
    private TextView textOfRequest;
    private LinearLayout responsibleForTheExecutionOfTheRequestBlock;
    private TextView responsibleForTheExecutionOfTheRequest;
    private LinearLayout actionsOverRequestBlock;
    private TextView actionsOverRequest;

    private Request request;

    public static RequestGeneralViewFragment newInstance(Request request) {
        RequestGeneralViewFragment fragment = new RequestGeneralViewFragment();
        fragment.request = request;
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
            requestId.setText(Integer.toString(request.getRequestId()));
        }
        if(!request.getAcceptedTheRequest().equals("")) {
            acceptedTheRequestBlock.setVisibility(View.VISIBLE);
            acceptedTheRequest.setText(request.getAcceptedTheRequest());
        }
        if(request.getRequestRegistrationDate()!=null) {
            requestRegistrationDateBlock.setVisibility(View.VISIBLE);
            requestRegistrationDate.setText(String.valueOf(request.getRequestRegistrationDate()));
        }
        if(request.getPeriodOfExecutionInDateFormat()!=null) {
            periodOfExecutionBlock.setVisibility(View.VISIBLE);
            periodOfExecution.setText(String.valueOf(request.getPeriodOfExecutionInDateFormat()));
        }
        if(!request.getDeclarer().equals("")) {
            declarerBlock.setVisibility(View.VISIBLE);
            declarer.setText(request.getDeclarer());
        }
        if(!request.getSubdivision().equals("")) {
            subdivisionBlock.setVisibility(View.VISIBLE);
            subdivision.setText(request.getSubdivision());
        }
        if(!request.getDataAboutDeclarer().equals("")) {
            dataAboutDeclarerBlock.setVisibility(View.VISIBLE);
            dataAboutDeclarer.setText(request.getDataAboutDeclarer());
        }
        if(!request.getTextOfRequest().equals("")) {
            textOfRequestBlock.setVisibility(View.VISIBLE);
            textOfRequest.setText(request.getTextOfRequest());
        }
        if(!request.getResponsibleForTheExecutionOfTheRequest().equals("")) {
            responsibleForTheExecutionOfTheRequestBlock.setVisibility(View.VISIBLE);
            responsibleForTheExecutionOfTheRequest.setText(request.getResponsibleForTheExecutionOfTheRequest());
        }
    }

    private void VisibleSetting(MyRequest request) {                                                //настраивает фрагмент для отображения в виде текущей заявки
        if(request.getRequestId()!=0) {
            requestIdBlock.setVisibility(View.VISIBLE);
            requestId.setText(Integer.toString(request.getRequestId()));
        }
        if(!request.getRequestRegistrationDate().equals("")) {
            requestRegistrationDateBlock.setVisibility(View.VISIBLE);
            requestRegistrationDate.setText(String.valueOf(request.getRequestRegistrationDate()));
        }
        if(request.getPeriodOfExecutionInDateFormat()!=null) {
            periodOfExecutionBlock.setVisibility(View.VISIBLE);
            periodOfExecution.setText(String.valueOf(request.getPeriodOfExecutionInDateFormat()));
        }
        if(!request.getDeclarer().equals("")) {
            declarerBlock.setVisibility(View.VISIBLE);
            declarer.setText(request.getDeclarer());
        }
        if(!request.getSubdivision().equals("")) {
            subdivisionBlock.setVisibility(View.VISIBLE);
            subdivision.setText(request.getSubdivision());
        }
        if(!request.getDataAboutDeclarer().equals("")) {
            dataAboutDeclarerBlock.setVisibility(View.VISIBLE);
            dataAboutDeclarer.setText(request.getDataAboutDeclarer());
        }
        if(!request.getTextOfRequest().equals("")) {
            textOfRequestBlock.setVisibility(View.VISIBLE);
            textOfRequest.setText(request.getTextOfRequest());
        }
        if(!request.getResponsibleForTheExecutionOfTheRequest().equals("")) {
            responsibleForTheExecutionOfTheRequestBlock.setVisibility(View.VISIBLE);
            responsibleForTheExecutionOfTheRequest.setText(request.getResponsibleForTheExecutionOfTheRequest());
        }
        if(!request.getActionsOverRequest().equals("")) {
            actionsOverRequestBlock.setVisibility(View.VISIBLE);
            actionsOverRequest.setText(request.getActionsOverRequest());
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
        SendRequestSetting(request);
        return root;
    }
}
