package com.example.projectkfudemo.architecturalcomponents.ui.currenttask;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.architecturalcomponents.ui.UIList;
import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.architecturalcomponents.models.RequestStateAdapter;
import com.example.projectkfudemo.architecturalcomponents.models.Search;
import com.example.projectkfudemo.parametrclasses.User;
import com.example.projectkfudemo.requests.RequestList;

import java.io.Serializable;
import java.util.List;


public class CurrentTaskFragment extends Fragment implements Serializable, UIList {

    static private Bundle args;
    private Bundle saveRequests;
    MainActivity mainActivity;
    private boolean mAlreadyLoaded = false;
    private boolean firstLoad = true;

    private final String REQUEST_LIST_SAVING_KEY = "requestListSavingKey";

    //requestList
    public RequestList myRequestList = new RequestList();//requestList.ger

    private EditText searchEditText;
    
    private volatile RequestStateAdapter requestAdapter = null;

    private ListView requestListView = null;

    LayoutInflater myInflater;

    private List<Request> getStates() {
        return myRequestList.getRequests();
    }

    public static CurrentTaskFragment newInstance(Bundle arg) {
        CurrentTaskFragment fragment = new CurrentTaskFragment();
        args = arg;
        return fragment;
    }

    public ListView setRequestListView(int position) {
        requestAdapter = new RequestStateAdapter(myInflater.getContext(), R.layout.task, getStates());
        requestListView.setAdapter(requestAdapter);
        System.out.println("Операция пройдена");
        return requestListView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(REQUEST_LIST_SAVING_KEY, (Serializable) myRequestList);
        super.onSaveInstanceState(outState);
    }

    public void setRequestListView() {
        requestAdapter = new RequestStateAdapter(myInflater.getContext(), R.layout.task, getStates());
        requestListView.setAdapter(requestAdapter);
    }

    private boolean getAlreadyLoaded() {
        return mAlreadyLoaded;
    }

    private void setAlreadyLoaded(boolean mAlreadyLoaded) {
        this.mAlreadyLoaded = mAlreadyLoaded;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        currentTaskViewModel = ViewModelProviders.of(this).get(CurrentTaskViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_current_task_list, container, false);

        mainActivity = (MainActivity) getActivity();
        myInflater = inflater;
        mainActivity.getViewModelCurrentTask().setInterface(this);

        User user = (User) args.getSerializable("user");

        requestListView = rootView.findViewById(R.id.currentTasksList);

        Spinner categorySpinner = rootView.findViewById(R.id.status);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(inflater.getContext(),
                R.array.statuses_current_tasks, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mainActivity.getViewModelCurrentTask().getLiveDataCurrentTaskSelectedPosition().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mainActivity.getViewModelCurrentTask().setOnChangedSelectedPosition();
            }
        });
        mainActivity.getViewModelCurrentTask().getLiveDataCurrentTaskRequestList().observe(getViewLifecycleOwner(), new Observer<RequestList>() {
            @Override
            public void onChanged(RequestList requestList) {
                myRequestList = requestList;
                setRequestListView();
            }
        });
        if(mainActivity.getViewModelCurrentTask().getFirstLoad()) {
            mainActivity.getViewModelCurrentTask().sendRequest();
            mainActivity.getViewModelCurrentTask().setFirstLoad(false);
        }
        // Вызываем адаптер
        categorySpinner.setAdapter(adapter);
        if (mainActivity.getViewModelCurrentTask().getLiveDataCurrentTaskSelectedPosition().getValue()!=null) {
            if(mainActivity.getViewModelCurrentTask().getLiveDataCurrentTaskSelectedPosition().getValue()!=0) {
                categorySpinner.setSelection(mainActivity.getViewModelCurrentTask().getLiveDataCurrentTaskSelectedPosition().getValue());
            }
        }

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                if(savedInstanceState!=null) {
                    setRequestListView();
                } else {
                    if(mainActivity.getViewModelCurrentTask().getAlreadyLoaded()){
                        mainActivity.getViewModelCurrentTask().setAlreadyLoaded(false);
                    } else {
                        mainActivity.getViewModelCurrentTask().setOnSelectedPosition(selectedItemPosition);
                    }
                }
//                if (mainActivity.getViewModelCurrentTask().getFirstLoad()){
//                    mainActivity.getViewModelCurrentTask().setOnSelectedPosition(selectedItemPosition);
//                    mainActivity.getViewModelCurrentTask().setFirstLoad(false);
//                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        searchEditText = rootView.findViewById(R.id.search_current_task_edit_text);
        if(mainActivity.getViewModelCurrentTask().getSearchText().length()!=0) {
            searchEditText.setText(mainActivity.getViewModelCurrentTask().getSearchText());
        }
        searchEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(String.valueOf(s).length()!=0) {
                    Search search = new Search(String.valueOf(s), getStates());
                    mainActivity.getViewModelCurrentTask().getLiveDataCurrentTaskRequestList().postValue(search.getResultListOnView());
                    mainActivity.getViewModelCurrentTask().setSearchText(String.valueOf(s));
                } else {
                    mainActivity.getViewModelCurrentTask().getLiveDataCurrentTaskRequestList().postValue(mainActivity.getViewModelCurrentTask().getRequestList());
                    mainActivity.getViewModelCurrentTask().setSearchText("");
                }
            }
        });

        //получаем элемент ListView

        // устанавливаем адаптер

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
//                    Gson gson = new Gson();
//                    getArguments().putString("list",gson.toJson(states));
                    mainActivity.startFragmentGeneralView(selectedRequest);
                } else {
                    System.out.println("selectedRequest is null");
                }
                //((MainActivity)getActivity())
            }
        };

        requestListView.setOnItemClickListener(itemListener);
        mainActivity.getViewModelCurrentTask().setFirstLoad(false);
        if (savedInstanceState == null && !mainActivity.getViewModelCurrentTask().getAlreadyLoaded()) {
            mainActivity.getViewModelCurrentTask().setAlreadyLoaded(true);
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
    public void setRequestList() {

    }

    @Override
    public void setList() {

    }
}