package com.example.projectkfudemo.architecturalcomponents.ui.globalsearch;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectkfudemo.architecturalcomponents.ui.LayoutVisibilityInterface;
import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.architecturalcomponents.ui.ProgressBarVisibilityInterface;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment.ViewModelGlobalSearch;
import com.example.projectkfudemo.requests.Request;
import com.example.projectkfudemo.requests.RequestList;
import com.example.projectkfudemo.parametrclasses.User;

import java.util.List;

import kotlinx.coroutines.GlobalScope;


public class GlobalSearchFragment extends Fragment implements View.OnClickListener, GlobalSearchInterface, LayoutVisibilityInterface, ProgressBarVisibilityInterface {
    private String TAG = this.getClass().getSimpleName();

    static Bundle args;
    MainActivity mainActivity;
    private LayoutInflater myInflater;

    private Button searchButton;
    private EditText editRequestNumber;
    private EditText editRequestRegistrationDateIdStart;
    private EditText editRequestRegistrationDateIdFinish;
    private EditText editDeclarer;
    private EditText editSubdivision;
    private EditText editAddress;
    private EditText editNumberOfCabinet;
    private EditText editTextOfRequest;
    private String stringRequestNumber;
    private String stringRequestRegistrationDateIdStart;
    private String stringRequestRegistrationDateIdFinish;
    private String stringDeclarer;
    private String stringSubdivision;
    private String stringAddress;
    private String stringNumberOfCabinet;
    private String stringTextOfRequest;
    private Spinner spinnerApplicationExecutorsDepartment;
    private Spinner spinnerFullNameOfExecutor;
    private Spinner spinnerStatusOfRequest;
    private Spinner spinnerRequestRegistration;
    private Spinner spinnerTypeOfRequest;
    private Integer integerRequestNumber;
    private Integer integerNumberOfCabinet;
    private Integer integerApplicationExecutorsDepartment;
    private Integer integerFullNameOfExecutor; //сюда getSearchWorkerStrings
    private Integer integerStatusOfRequest;
    private Integer integerRequestRegistration; //сюда getSearchDeclarersStrings
    private Integer integerTypeOfRequest;
    private ProgressBar mainProgressBar;
    private ProgressBar searchButtonProgressBar;
    private RelativeLayout searchLayout;

    private Boolean allDataReady;

    ArrayAdapter<String> adapterRequestRegistrationSpinner;
    ArrayAdapter<String> adapterFullNameOfExecutorSpinner;
    ArrayAdapter<CharSequence> adapterTypeOfRequest;
    ArrayAdapter<CharSequence> adapterStatusOfRequest;

    User user;

    public static GlobalSearchFragment newInstance(Bundle arg) {
        GlobalSearchFragment fragment = new GlobalSearchFragment();
        args = arg;
        return fragment;
    }

    public void setId(View rootView) {
        searchButton = rootView.findViewById(R.id.search_button);
        searchButton.setOnClickListener(this);
        editRequestNumber = rootView.findViewById(R.id.request_number);
        editRequestRegistrationDateIdStart = rootView.findViewById(R.id.request_registration_date_id_edit_start);
        editRequestRegistrationDateIdFinish = rootView.findViewById(R.id.request_registration_date_id_edit_finish);
        editDeclarer = rootView.findViewById(R.id.declarer);
        editSubdivision = rootView.findViewById(R.id.subdivision);
        editAddress = rootView.findViewById(R.id.address);
        editNumberOfCabinet = rootView.findViewById(R.id.number_of_cabinet);
        editTextOfRequest = rootView.findViewById(R.id.text_of_request);
        spinnerApplicationExecutorsDepartment = rootView.findViewById(R.id.application_executors_department);
        spinnerFullNameOfExecutor = rootView.findViewById(R.id.full_name_of_executor);
        spinnerStatusOfRequest = rootView.findViewById(R.id.status_of_request);
        spinnerRequestRegistration = rootView.findViewById(R.id.request_registration);
        spinnerTypeOfRequest = rootView.findViewById(R.id.type_of_request);
        mainProgressBar = rootView.findViewById(R.id.global_search_progress_bar);
        searchButtonProgressBar = rootView.findViewById(R.id.search_button_progress_bar_id);
        searchLayout = rootView.findViewById(R.id.global_search_layout);
    }

    @Override
    public void setSpinners() {
        adapterTypeOfRequest = ArrayAdapter.createFromResource(getContext(), R.array.type_request, android.R.layout.simple_spinner_item);
        adapterStatusOfRequest =ArrayAdapter.createFromResource(getContext(), R.array.status_of_request_for_global_search, android.R.layout.simple_spinner_item);
        spinnerTypeOfRequest.setAdapter(adapterTypeOfRequest);
        spinnerStatusOfRequest.setAdapter(adapterStatusOfRequest);
    }

    public void setSpinnerRequestRegistration(List<String> list) {
        adapterRequestRegistrationSpinner = new ArrayAdapter<>(myInflater.getContext(), android.R.layout.simple_spinner_item, list);
        adapterRequestRegistrationSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRequestRegistration.setAdapter(adapterRequestRegistrationSpinner);
    }

    public void setSpinnerFullNameOfExecutor(List<String> list) {
        adapterFullNameOfExecutorSpinner = new ArrayAdapter<>(myInflater.getContext(), android.R.layout.simple_spinner_item, list);
        adapterFullNameOfExecutorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFullNameOfExecutor.setAdapter(adapterFullNameOfExecutorSpinner);
    }

    public void checkDataForShow() {
        if(mainActivity.getViewModelMainActivity().getLiveDataSearchWorkerString().getValue()!=null && mainActivity.getViewModelMainActivity().getLiveDataSearchDeclarerString().getValue()!=null) {
            setAllDataReady(true);
        } else {
            setAllDataReady(false);
        }
    }

    public void tryToHideMainProgressBar() {
        if(getAllDataReady()) {
            hideProgressBar();
            showLayout();
        }
    }

    public void setParams() {
        if(editDeclarer.getText().length()!=0){
            stringDeclarer = editDeclarer.getText().toString();
        } else {
            stringDeclarer = null;
        }

        stringRequestNumber = editRequestNumber.getText().toString();
        try {
            integerRequestNumber = Integer.parseInt(stringRequestNumber);
        }
        catch (NumberFormatException e) {
            integerRequestNumber = null;
            //Вывод ошибки на экран, приостановка данного метода и метода отправки запроса
        }
        if (editRequestRegistrationDateIdStart.getText().length() != 0) {
            stringRequestRegistrationDateIdStart = editRequestRegistrationDateIdStart.getText().toString();
        } else {
            stringRequestRegistrationDateIdStart = null;
        }

        if (editRequestRegistrationDateIdFinish.getText().length() != 0) {
            stringRequestRegistrationDateIdFinish = editRequestRegistrationDateIdFinish.getText().toString();
        } else {
            stringRequestRegistrationDateIdFinish = null;
        }

        integerTypeOfRequest = spinnerTypeOfRequest.getSelectedItemPosition();
        if(integerTypeOfRequest == 0 || integerTypeOfRequest == -1) {
            integerTypeOfRequest = null;
        }

        integerStatusOfRequest = spinnerStatusOfRequest.getSelectedItemPosition();
        if(integerStatusOfRequest == 0 || integerStatusOfRequest == -1) {
            integerStatusOfRequest = null;
        }

        integerRequestRegistration = spinnerRequestRegistration.getSelectedItemPosition();
        if(integerRequestRegistration == 0 || integerRequestRegistration == -1) {
            integerRequestRegistration = null;
        } else {
            integerRequestRegistration = mainActivity.getViewModelMainActivity()
                    .getLiveDataSearchDeclarers().getValue().getDeclarersList()
                    .get(spinnerRequestRegistration.getSelectedItemPosition()).getId()-1; // -1 потому что в spinner первый элемент по индексу 1, а в массиве первый элемент под индексом 0, а дальше простая математика
        }

        integerApplicationExecutorsDepartment = spinnerApplicationExecutorsDepartment.getSelectedItemPosition();
        if(integerApplicationExecutorsDepartment == 0 || integerApplicationExecutorsDepartment == -1) {
            integerApplicationExecutorsDepartment = null;
        } else {
            integerApplicationExecutorsDepartment = mainActivity.getViewModelMainActivity()
                    .getLiveDataSearchWorkers().getValue().getWorkersList()
                    .get(spinnerApplicationExecutorsDepartment.getSelectedItemPosition()).getId()-1; // -1 потому что в spinner первый элемент по индексу 1, а в массиве первый элемент под индексом 0
        }

        if (editTextOfRequest.getText().toString().length() != 0) {
            stringTextOfRequest = editTextOfRequest.getText().toString();
        } else {
            stringTextOfRequest = null;
        }

    }

    private void onSearchButtonClick(User user) {

        mainActivity.getViewModelGlobalSearch().setUser(user);
        mainActivity.getViewModelGlobalSearch().setObjectForRequests();
        mainActivity.getViewModelGlobalSearch().setParamsForGlobalSearch(
                stringDeclarer,
                integerRequestNumber,
                stringRequestRegistrationDateIdStart,
                stringRequestRegistrationDateIdFinish,
                integerTypeOfRequest,
                integerStatusOfRequest,
                integerRequestRegistration,
                integerFullNameOfExecutor,
                stringTextOfRequest
        );

        mainActivity.getViewModelGlobalSearch().getLiveDataSearchResultFromServer().observe(this, new Observer<RequestList>() {
            @Override
            public void onChanged(RequestList requestList) {
                Log.i(TAG, "!вызов onChanged в SearchResultFromServer");
                if(requestList==null) {
                    Log.i(TAG, "!requestList = null");
                } else {
                    Log.i(TAG, "!размер requestList: " + requestList.getRequests().size());
                }
                hideSearchButtonProgressBar();
                searchButton.setClickable(true);
                showResultFragment(requestList);
            }
        });

        mainActivity.getViewModelGlobalSearch().sendRequest();
        Log.i(TAG, "!Вызов sendRequest");

    }

    @Override
    public void showResultFragment(RequestList requestList) {
        editRequestNumber.setText(null);
        Log.i(TAG, "!Вызван метод showResultFragment");
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.startFragmentGlobalSearchResult(requestList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_global_search, container, false);
        user = (User) args.getSerializable("user");

        myInflater = inflater;
        mainActivity = (MainActivity) getActivity();

        setId(rootView);

        showProgressBar();
        hideSearchButtonProgressBar();
        hideLayout();

        setSpinners();

        checkDataForShow();

        mainActivity.getViewModelMainActivity().setGlobalSearchInterface(this);

        mainActivity.getViewModelGlobalSearch().setInterface(this);
        spinnerRequestRegistration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerFullNameOfExecutor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

            }

            public void onNothingSelected(AdapterView<?> parent) {
                
            }
        });

        mainActivity.getViewModelMainActivity().getLiveDataSearchDeclarerString().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                Log.i(TAG, "!вызов onChanged в SearchDeclarerString");
                setSpinnerRequestRegistration(strings);
                checkDataForShow();
                tryToHideMainProgressBar();
            }
        });
        mainActivity.getViewModelMainActivity().getLiveDataSearchWorkerString().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                Log.i(TAG, "!вызов onChanged в SearchWorkerString");
                setSpinnerFullNameOfExecutor(strings);
                checkDataForShow();
                tryToHideMainProgressBar();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mainActivity != null) {
            mainActivity.switchSelectedItemSearch();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                showSearchButtonProgressBar();
                searchButton.setClickable(false);
                setParams();
                onSearchButtonClick(user);
                break;
        }
    }

    @Override
    public void showLayout() {
        searchLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLayout() {
        searchLayout.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        mainProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mainProgressBar.setVisibility(View.GONE);
    }

    public void showSearchButtonProgressBar() {
        searchButtonProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideSearchButtonProgressBar() {
        searchButtonProgressBar.setVisibility(View.GONE);
    }

    public Boolean getAllDataReady() {
        return allDataReady;
    }

    public void setAllDataReady(Boolean allDataReady) {
        Log.i(TAG, "В allDataReady входит значение: " + allDataReady);
        this.allDataReady = allDataReady;
    }
}
