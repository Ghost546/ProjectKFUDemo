package com.example.projectkfudemo.architecturalcomponents.ui.requestgeneralview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.architecturalcomponents.ui.ViewModelGet;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.ViewModelInterface;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.requestgeneralviewfragment.ViewModelRequestGeneralView;
import com.example.projectkfudemo.parametrclasses.User;
import com.example.projectkfudemo.requests.CurrentRequest;
import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity;
import com.example.projectkfudemo.requests.MyRequest;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.requests.RequestGeneral;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RequestGeneralViewFragment extends Fragment implements View.OnClickListener {
    final String TAG = this.getClass().getName();

    private MainActivity mainActivity;

    static Bundle args;

    private User user;

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

    RequestGeneral requestGeneral;

    private Button changeLogsButton;

    FloatingActionButton fabGeneral;
    FloatingActionButton fab1;                                                                      //рег карточка заявки
    FloatingActionButton fab2;                                                                      //комментарий исполнителя

    int count = 0;

    public void addCount(int k) {
        this.count += k;
    }

    public void setCount(int k) {
        this.count = k;
    }

    public int getKCount() {
        return count;
    }

    private Request request;

    public static RequestGeneralViewFragment newInstance(Request request, Bundle arg) {
        RequestGeneralViewFragment fragment = new RequestGeneralViewFragment();
        fragment.setCount(0);
        fragment.request = request;
        args = arg;
        return fragment;
    }

    private void sendRequestSetting(Request request) {
        generalSetting(request);
        if(request.getThatIsCurrentRequest()) {
            Log.i(TAG, "!Заявка из списка Текущие заявки");
            requestGeneral = new CurrentRequest(request);
            visibleSetting((CurrentRequest) requestGeneral);
        }
        if(request.getThatIsMyRequest()) {
            Log.i(TAG, "!Заявка из списка Мои заявки");
            requestGeneral = new MyRequest(request);
            visibleSetting((MyRequest) requestGeneral);
        }
        if(!request.getThatIsMyRequest() & !request.getThatIsCurrentRequest()) {
            Log.i(TAG, "!Вероятно заявка с глобального поиска");
            visibleSetting(request);
        }
    }

    private void generalSetting(Request request) {
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

    public void visibleSetting(CurrentRequest request) {                                           //настраивает фрагмент для отображения в виде текущей заявки
        Log.i(TAG,"!метод getThatIsCurrentRequest() возвращает: " + request.getRequest().getThatIsCurrentRequest());
        setFabByStatus(request, request.getStatus().getId());
    }

    private void visibleSetting(MyRequest request) {                                                //настраивает фрагмент для отображения в виде текущей заявки
        setFabByStatus(request, request.getStatus().getId());
    }

    private void visibleSetting(Request request) {

    }

    public void visibleSetting() {
        fabGeneral.setVisibility(View.GONE);
        fab1.setVisibility(View.GONE);
        fab2.setVisibility(View.GONE);
    }



    public void setFabByStatus(RequestGeneral requestGeneral, int status) {
        if(requestGeneral.getRequest().getThatIsCurrentRequest() && status == 1) {
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //здесь запрос "назначить заявку на себя"
                    assignToOneself();

                }
            });
        } else {
            if(requestGeneral.getRequest().getThatIsMyRequest() && status == 3) {
                fab2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainActivity.startFragmentAddCommentToRequest(request);
                    }
                });
            } else{
                fab2.setVisibility(View.GONE);
            }
        }
    }

    private void assignToOneself() {
        getViewModel().setUser(user);
        getViewModel().sendAssign();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    //id на тип заявки
    //1 - устная заявка
    //2 - письменная заявка
    //3 - телефорнная заявка
    //4 - email заявка
    //5 - web заявка
    private void setByRequestType(FloatingActionButton fab) {
        int idType = request.getType().getId();
        switch (idType) {
            case 1:
                fab.setImageResource(R.drawable.campaign_24px);
                break;
            case 2:
                fab.setImageResource(R.drawable.edit_white_18dp);
                break;
            case 3:
                fab.setImageResource(R.drawable.phone_24px);
                break;
            case 4:
                fab.setImageResource(R.drawable.alternate_email_24px);
                break;
            case 5:
                fab.setImageResource(R.drawable.language_24px);
                break;
        }
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

        fabGeneral = (FloatingActionButton) root.findViewById(R.id.fab_general);
        fab1 = root.findViewById(R.id.fab_1);
        fab2 = root.findViewById(R.id.fab_2);
    }

    @Override
    public void onResume() {
        super.onResume();
        setCount(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        setCount(0);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) args.getSerializable("user");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_general_view_request, container, false);

        mainActivity = (MainActivity) getActivity();

        setIds(root);



        sendRequestSetting(request);
        setByRequestType(fabGeneral);

        Animation show_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_show);
        Animation hide_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_hide);

        Animation show_fab_2 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab2_show);
        Animation hide_fab_2 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab2_hide);
        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        fabGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getKCount()%2 == 0) {
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
                addCount(1);
                if(getKCount()==10) {
                    setCount(0);
                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLogsButtonClick();
            }
        });

        return root;
    }

    private void changeLogsButtonClick() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.startFragmentChangeLogsView(request);
    }

    public ViewModelRequestGeneralView getViewModel() {
        return mainActivity.getViewModelRequestGeneralView();
    }

    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

}
