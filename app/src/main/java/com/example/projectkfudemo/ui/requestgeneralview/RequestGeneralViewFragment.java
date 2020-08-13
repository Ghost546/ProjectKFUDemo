package com.example.projectkfudemo.ui.requestgeneralview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.CurrentRequest;
import com.example.projectkfudemo.MainActivity;
import com.example.projectkfudemo.MyRequest;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class RequestGeneralViewFragment extends Fragment implements View.OnClickListener {

    private LinearLayout requestCodeBlock;
    private TextView requestCode;
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

    private Button changeLogsButton;

    FloatingActionButton fab1;                                                                      //рег карточка заявки
    FloatingActionButton fab2;                                                                      //комментарий исполнителя

    int k = 0;

    public void addK(int k) {
        this.k += k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }

    private Request request;

    public static RequestGeneralViewFragment newInstance(Request request) {
        RequestGeneralViewFragment fragment = new RequestGeneralViewFragment();
        fragment.setK(0);
//        Bundle args = new Bundle();
//        Gson gson = new Gson();
//        args.putString("req",gson.toJson(request));
//        getArguments().putString("list",gson.toJson(states));
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
        if(request.getCode()!=0) {
            requestCodeBlock.setVisibility(View.VISIBLE);
            requestCode.setText(Integer.toString(request.getCode()));
        }
        if(!request.getAcceptedTheRequest().equals("")) {
            acceptedTheRequestBlock.setVisibility(View.VISIBLE);
            acceptedTheRequest.setText(request.getAcceptedTheRequest());
        }
        if(request.getRequestRegistrationDate()!=null) {
            requestRegistrationDateBlock.setVisibility(View.VISIBLE);
            requestRegistrationDate.setText(String.valueOf(request.getRequestRegistrationDate()));
        }
        if(request.getPeriodOfExecution()!=null) {
            periodOfExecutionBlock.setVisibility(View.VISIBLE);
            periodOfExecution.setText(String.valueOf(request.getPeriodOfExecution()));
        }
        if(!request.getDeclarer().equals("")) {
            declarerBlock.setVisibility(View.VISIBLE);
            String text = "";
            text += request.getDeclarer() + "\n";
            text += "(" + request.getDeclarantPost()+ ")";
            declarer.setText(text);
        }
        if(request.getSubdivisionList() != null) {
            String text = "";
            subdivisionBlock.setVisibility(View.VISIBLE);
            for(int i = 0; i<request.getSubdivisionList().size(); i++) {
                text += request.getSubdivisionList().get(i).getName() + "\n";
            }
            subdivision.setText(text);
        }
        if(!request.getContactFullName().equals("")) {                                              //условие не доработано, оставлено условно, пока ни на что не влияет
            dataAboutDeclarerBlock.setVisibility(View.VISIBLE);
            String text = "";
            text += "Адрес: "+request.getBuilding().getName() + " (" + request.getBuilding().getAddress() + ")" + "\n";
            text += "Кабинет: " +request.getCabinet()+"\n";
            text += "Контакт: " + request.getContactFullName() + "\n";
            text += "Телефон: " + request.getDeclarantPhone();
            dataAboutDeclarer.setText(text);
        }
        if(request.getWorksList() != null) {
            textOfRequestBlock.setVisibility(View.VISIBLE);
            textOfRequest.setText(request.getWorksList().get(0).getDescription());
        }
        if(!request.getResponsibleForTheExecutionOfTheRequest().equals("")) {
            responsibleForTheExecutionOfTheRequestBlock.setVisibility(View.VISIBLE);
            responsibleForTheExecutionOfTheRequest.setText(request.getResponsibleForTheExecutionOfTheRequest());
        }
        if(request.getStatus().getId() == 1) {
            //здесь метод измененяющий кнопку "добавить комментарий" на "назначить на себя"
        } else {
            fab2.setVisibility(View.GONE);
        }
    }

    private void VisibleSetting(MyRequest request) {                                                //настраивает фрагмент для отображения в виде текущей заявки
        if(request.getCode()!=0) {
            requestCodeBlock.setVisibility(View.VISIBLE);
            requestCode.setText(Integer.toString(request.getCode()));
        }
        if(request.getRequestRegistrationDate()!=null) {
            requestRegistrationDateBlock.setVisibility(View.VISIBLE);
            requestRegistrationDate.setText(String.valueOf(request.getRequestRegistrationDate()));
        }
        if(request.getPeriodOfExecution()!=null) {
            periodOfExecutionBlock.setVisibility(View.VISIBLE);
            periodOfExecution.setText(String.valueOf(request.getPeriodOfExecution()));
        }
        if(!request.getDeclarer().equals("")) {
            declarerBlock.setVisibility(View.VISIBLE);
            String text = "";
            text += request.getDeclarer() + "\n";
            text += "(" + request.getDeclarantPost() + ")";
            declarer.setText(text);
        }
        if(request.getSubdivisionList() != null) {
            String text = "";
            subdivisionBlock.setVisibility(View.VISIBLE);
            for(int i = 0; i<request.getSubdivisionList().size(); i++) {
                text += request.getSubdivisionList().get(i).getName() + "\n";
            }
            subdivision.setText(text);
        }
        if(!request.getContactFullName().equals("")) {
            dataAboutDeclarerBlock.setVisibility(View.VISIBLE);
            String text = "";
            text += "Адрес: "+request.getBuilding().getName() + " (" + request.getBuilding().getAddress() + ")" + "\n";
            text += "Кабинет: " +request.getCabinet()+"\n";
            text += "Контакт: " + request.getContactFullName() + "\n";
            text += "Телефон: " + request.getDeclarantPhone();
            dataAboutDeclarer.setText(text);
        }
        if(request.getWorksList() != null) {
            textOfRequestBlock.setVisibility(View.VISIBLE);
            textOfRequest.setText(request.getWorksList().get(0).getDescription());
        }
        if(!request.getResponsibleForTheExecutionOfTheRequest().equals("")) {
            responsibleForTheExecutionOfTheRequestBlock.setVisibility(View.VISIBLE);
            responsibleForTheExecutionOfTheRequest.setText(request.getResponsibleForTheExecutionOfTheRequest());
        }
        if(request.getActionsOverRequest()!=null) {
            actionsOverRequestBlock.setVisibility(View.VISIBLE);
            String text = "";
            for(int i = 0; i < request.getCommentsList().size(); i++) {
                text += request.getCommentsList().get(i).getComment() + "\n";
                text += "\n";
                for(int j = 0; j < request.getCommentsList().get(i).getWorksInCommentsList().size(); j++) {
                    text += request.getCommentsList().get(i).getWorksInCommentsList().get(j).getName();
                    text += "\n";
                }
            }
            actionsOverRequest.setText(text);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

    }


    private void setIds(View root) {
        requestCodeBlock=root.findViewById(R.id.request_code_block);
        requestCode=root.findViewById(R.id.request_code);
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

        fab1 = root.findViewById(R.id.fab_1);
        fab2 = root.findViewById(R.id.fab_2);



    }

    @Override
    public void onResume() {
        super.onResume();
        setK(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        setK(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_general_view_request, container, false);
        setIds(root);

        SendRequestSetting(request);

        FloatingActionButton fabGeneral = (FloatingActionButton) root.findViewById(R.id.fab_general);
        Animation show_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_show);
        Animation hide_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_hide);

        Animation show_fab_2 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab2_show);
        Animation hide_fab_2 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab2_hide);
        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        fabGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getK()%2 == 0) {
                    layoutParams1.rightMargin += (int) (fab1.getWidth() * 0.25);
                    layoutParams1.bottomMargin += (int) (fab1.getHeight() * 1.7);
                    fab1.setLayoutParams(layoutParams1);
                    fab1.startAnimation(show_fab_1);
                    fab1.setClickable(true);

                    layoutParams2.rightMargin += (int) (fab2.getWidth() * 0.25);
                    layoutParams2.bottomMargin += (int) (fab2.getHeight() * 2.9);
                    fab2.setLayoutParams(layoutParams2);
                    fab2.startAnimation(show_fab_2);
                    fab2.setClickable(true);
                } else {
                    layoutParams1.rightMargin -= (int) (fab1.getWidth() * 0.25);
                    layoutParams1.bottomMargin -= (int) (fab1.getHeight() * 1.7);
                    fab1.setLayoutParams(layoutParams1);
                    fab1.startAnimation(hide_fab_1);
                    fab1.setClickable(false);

                    layoutParams2.rightMargin -= (int) (fab2.getWidth() * 0.25);
                    layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 2.9);
                    fab2.setLayoutParams(layoutParams2);
                    fab2.startAnimation(hide_fab_2);
                    fab2.setClickable(false);
                }
                addK(1);
                if(getK()==10) {
                    setK(0);
                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLogsButtonClick();
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return root;
    }

    private void changeLogsButtonClick() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.startFragmentChangeLogsView(request);
    }

    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}
