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

    TextView requestIdBlock;
    TextView requestId;
    TextView requestRegistrationDateBlock;
    TextView requestRegistrationDate;
    TextView periodOfExecutionBlock;
    TextView periodOfExecution;
    TextView statusOfRequestBlock;
    TextView statusOfRequest;
    TextView acceptedTheRequestBlock;
    TextView acceptedTheRequest;
    TextView declarerBlock;
    TextView declarer;
    TextView subdivisionBlock;
    TextView subdivision;
    TextView dataAboutDeclarerBlock;
    TextView dataAboutDeclarer;
    TextView textOfRequestBlock;
    TextView textOfRequest;
    TextView responsibleForTheExecutionOfTheRequestBlock;
    TextView responsibleForTheExecutionOfTheRequest;
    TextView actionsOverRequestBlock;
    TextView actionsOverRequest;

    public static RequestGeneralViewFragment newInstance(Request request) {
        RequestGeneralViewFragment fragment = new RequestGeneralViewFragment();

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

        }
    }

    private void VisibleSetting(MyRequest request) {                                                //настраивает фрагмент для отображения в виде текущей заявки

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_general_view_request, container, false);


        return root;
    }
}
