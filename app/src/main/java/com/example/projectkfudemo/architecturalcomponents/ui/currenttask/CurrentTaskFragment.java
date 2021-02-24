package com.example.projectkfudemo.architecturalcomponents.ui.currenttask;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.projectkfudemo.architecturalcomponents.ui.DataRequestList;
import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.architecturalcomponents.ui.TasksVisibilityInterface;
import com.example.projectkfudemo.architecturalcomponents.ui.ViewModelGet;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.currenttaskfragment.ViewModelCurrentTask;
import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.architecturalcomponents.models.RequestStateAdapter;
import com.example.projectkfudemo.architecturalcomponents.models.Search;
import com.example.projectkfudemo.parametrclasses.User;
import com.example.projectkfudemo.requests.RequestGeneral;
import com.example.projectkfudemo.requests.RequestList;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class CurrentTaskFragment extends Fragment implements Serializable, TasksVisibilityInterface, DataRequestList, ViewModelGet {
    final String TAG = this.getClass().getName();

    static private Bundle args;
    private MainActivity mainActivity;

    private final String REQUEST_LIST_SAVING_KEY = "requestListSavingKey";

    private EditText searchEditText;
    
    private volatile RequestStateAdapter requestAdapter = null;

    private ListView currentTaskRequestListView = null;
    private ProgressBar currentTaskProgressBar = null;
    private LinearLayout currentTaskMessageByLinearLayout = null;

    LayoutInflater myInflater;

    public static CurrentTaskFragment newInstance(Bundle arg) {
        CurrentTaskFragment fragment = new CurrentTaskFragment();
        args = arg;
        return fragment;
    }

    private List<Request> getStates() {
        return getRequestList().getRequests();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(REQUEST_LIST_SAVING_KEY, (Serializable) getRequestList());
        super.onSaveInstanceState(outState);
    }

    public void setRequestListView() {
        hideProgressBar();
        requestAdapter = new RequestStateAdapter(myInflater.getContext(), R.layout.task, getStates());
        currentTaskRequestListView.setAdapter(requestAdapter);
        if(getStates().size()==0) {
            hideList();
            showMessage();
        } else {
            hideMessage();
            hideProgressBar();
            showList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current_task_list, container, false);

        mainActivity = (MainActivity) getActivity();
        myInflater = inflater;

        User user = (User) args.getSerializable("user");
        Log.i(TAG, "!userId = " + user.getUserId() + " p2 = " + user.getP2());

        currentTaskRequestListView = rootView.findViewById(R.id.current_tasks_list);
        currentTaskProgressBar = rootView.findViewById(R.id.current_tasks_progress_bar);
        currentTaskMessageByLinearLayout = rootView.findViewById(R.id.current_task_message_about_result_from_server);

        Spinner categorySpinner = rootView.findViewById(R.id.status);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(inflater.getContext(), R.array.statuses_current_tasks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        getViewModel().getLiveDataCurrentTaskSelectedPosition().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(getViewModel().getCategory()!=integer) {
                    hideList();
                    showProgressBar();
                    getViewModel().setOnChangedSelectedPosition();
                } else {
                    hideProgressBar();
                    showList();
                }
            }
        });

        if(!getViewModel().getAlreadyLoaded()) {
            showProgressBar();
        } else {
            if(getViewModel().getRequestList().getRequests().size()==0) {
                showMessage();
            } else {
                showList();
            }
        }

        getViewModel().getLiveDataCurrentTaskRequestList().observe(getViewLifecycleOwner(), new Observer<RequestList>() {
            @Override
            public void onChanged(RequestList requestList) {
                Log.i(TAG, "! сработал метод onChanged на LiveDataCurrentTaskRequestList");
                setRequestList(requestList);
                setRequestListView();
            }
        });

        if(getViewModel().getFirstLoad()) {
            getViewModel().sendRequestCurrentTask();
            getViewModel().setFirstLoad(false);
        }

        // Вызываем адаптер
        categorySpinner.setAdapter(adapter);
        if (getViewModel().getLiveDataCurrentTaskSelectedPosition().getValue()!=null) {
            if(getViewModel().getLiveDataCurrentTaskSelectedPosition().getValue()!=0) {
                categorySpinner.setSelection(getViewModel().getLiveDataCurrentTaskSelectedPosition().getValue());
            }
        }
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                if(savedInstanceState!=null) {
                    setRequestListView();
                } else {
                    if(getViewModel().getAlreadyLoaded()){
                        getViewModel().setAlreadyLoaded(false);
                    } else {
                        if(currentTaskMessageByLinearLayout.getVisibility()==View.VISIBLE){
                            hideMessage();
                        }
                        if(currentTaskRequestListView.getVisibility()==View.VISIBLE){
                            hideList();
                        }
                        if(currentTaskProgressBar.getVisibility()==View.GONE) {
                            showProgressBar();
                        }
                        getViewModel().setOnSelectedPosition(selectedItemPosition);
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchEditText = rootView.findViewById(R.id.search_current_task_edit_text);
        if(getViewModel().getSearchText().length()!=0) {
            searchEditText.setText(getViewModel().getSearchText());
        }
        searchEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(String.valueOf(s).length()!=0) {
                    Search search = new Search(String.valueOf(s), getStates());
                    getViewModel().getLiveDataCurrentTaskRequestList().postValue(search.getResultListOnView());
                    getViewModel().setSearchText(String.valueOf(s));
                } else {
                    getViewModel().getLiveDataCurrentTaskRequestList().postValue(getViewModel().getRequestList());
                    getViewModel().setSearchText("");
                }
                hideMessage();
            }
        });

        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // получаем выбранный пункт
                Request selectedRequest = (Request) parent.getItemAtPosition(position);
                selectedRequest.setThatIsCurrentRequest();
                //настраиваем и отправляем будущий фрагмент
                //запускаем фрагмент
                if (selectedRequest != null) {
                    mainActivity.startFragmentGeneralView(selectedRequest);
                } else {
                    System.out.println("selectedRequest is null");
                }
            }
        };

        currentTaskRequestListView.setOnItemClickListener(itemListener);
        getViewModel().setFirstLoad(false);



        if (savedInstanceState == null && !getViewModel().getAlreadyLoaded()) {
            getViewModel().setAlreadyLoaded(true);
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mainActivity != null) {
            mainActivity.switchSelectedItemCurrentTask();
        }
    }

    @Override
    public void showProgressBar() {
        currentTaskProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        currentTaskProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showList() {
        currentTaskRequestListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        currentTaskRequestListView.setVisibility(View.GONE);
    }

    @Override
    public void showMessage() {
        currentTaskMessageByLinearLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessage() {
        currentTaskMessageByLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void changeTextMessage(String mutableMessage) {

    }

    @Override
    public void setRequestList(@NotNull RequestList requestList) {
        mainActivity.getViewModelCurrentTask().setRequestList(requestList);
    }

    @Override
    public RequestList getRequestList() {
        return mainActivity.getViewModelCurrentTask().getRequestList();
    }


    @Override
    public ViewModelCurrentTask getViewModel() {
        return mainActivity.getViewModelCurrentTask();
    }
}

//if(currentTaskMessageByLinearLayout.getVisibility()==View.VISIBLE){
//  hideMessage();
//}
//if(currentTaskRequestListView.getVisibility()==View.VISIBLE){
//  hideList();
//}
//if(currentTaskProgressBar.getVisibility()==View.GONE) {
//  showProgressBar();
//}