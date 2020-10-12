package com.example.projectkfudemo.architecturalcomponents.ui.globalsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectkfudemo.architecturalcomponents.ui.MainActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.architecturalcomponents.viewmodels.globalsearchfragment.ViewModelGlobalSearch;
import com.example.projectkfudemo.requests.RequestList;
import com.example.projectkfudemo.parametrclasses.User;


public class GlobalSearchFragment extends Fragment implements View.OnClickListener, GlobalSearchInterface {
    static Bundle args;
    MainActivity mainActivity;

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
    private Integer integerFullNameOfExecutor;
    private Integer integerStatusOfRequest;
    private Integer integerRequestRegistration;
    private Integer integerTypeOfRequest;

    ViewModelGlobalSearch viewModelGlobalSearch;

    ArrayAdapter<String> adapterRequestRegistrationSpinner;
    ArrayAdapter<String> adapterFullNameOfExecutorSpinner;

    User user;

    RequestList requestList;

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
    }

    public void setSpinners(LayoutInflater inflater) {
        if(mainActivity.getViewModelMainActivity().getLiveDataSearchDeclarers().getSearchDeclarerStrings()!=null) {
            adapterRequestRegistrationSpinner = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_spinner_item, mainActivity.getViewModelMainActivity().getLiveDataSearchDeclarers().getSearchDeclarerStrings());
            adapterRequestRegistrationSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerRequestRegistration.setAdapter(adapterRequestRegistrationSpinner);
        }
        if(mainActivity.getViewModelMainActivity().getLiveDataSearchWorkers().getSearchWorkerStrings()!=null) {
            adapterFullNameOfExecutorSpinner = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_spinner_item, mainActivity.getViewModelMainActivity().getLiveDataSearchWorkers().getSearchWorkerStrings());
            adapterFullNameOfExecutorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerFullNameOfExecutor.setAdapter(adapterFullNameOfExecutorSpinner);
        }
    }

    public void setParams() {
        stringDeclarer = editDeclarer.getText().toString();
        stringRequestNumber = editRequestNumber.getText().toString();
        try {
            integerRequestNumber = Integer.parseInt(stringRequestNumber);
        }
        catch (NumberFormatException e) {
            integerRequestNumber = null;
            //Вывод ошибки на экран, приостановка данного метода и метода отправки запроса
        }
        stringRequestRegistrationDateIdStart = editRequestRegistrationDateIdStart.getText().toString();
        stringRequestRegistrationDateIdFinish = editRequestRegistrationDateIdFinish.getText().toString();

        integerTypeOfRequest = spinnerTypeOfRequest.getSelectedItemPosition();
        if(integerTypeOfRequest==0) integerTypeOfRequest = null;

        integerStatusOfRequest = spinnerStatusOfRequest.getSelectedItemPosition();
        if(integerStatusOfRequest==0) integerStatusOfRequest = null;

        integerRequestRegistration = spinnerRequestRegistration.getSelectedItemPosition();
        if(integerRequestRegistration==0) integerRequestRegistration = null;

        integerApplicationExecutorsDepartment = spinnerApplicationExecutorsDepartment.getSelectedItemPosition();
        if(integerApplicationExecutorsDepartment==0) integerApplicationExecutorsDepartment = null;
    }

    private void onSearchButtonClick(User user) {
        stringRequestNumber = editRequestNumber.toString();
        stringRequestRegistrationDateIdStart = editRequestRegistrationDateIdStart.toString();
        stringRequestRegistrationDateIdFinish = editRequestRegistrationDateIdFinish.toString();
        stringDeclarer = editDeclarer.toString();

        viewModelGlobalSearch.setUser(user);
        viewModelGlobalSearch.setObjectForRequests();
        viewModelGlobalSearch.setParamsForGlobalSearch(
                stringDeclarer,
                integerRequestNumber,
                stringRequestRegistrationDateIdStart,
                stringRequestRegistrationDateIdFinish,
                integerTypeOfRequest,
                integerStatusOfRequest,
                integerRequestRegistration,
                integerFullNameOfExecutor
        );

        viewModelGlobalSearch.sendRequest();
        viewModelGlobalSearch.getLiveDataSearchResultFromServer().observe(this, new Observer<RequestList>() {
            @Override
            public void onChanged(RequestList requestList) {
                showResultFragment(requestList);
            }
        });
    }

    @Override
    public void showResultFragment(RequestList requestList) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.startFragmentGlobalSearchResult(requestList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_global_search, container, false);
        user = (User) args.getSerializable("user");

        viewModelGlobalSearch =  new ViewModelProvider(this).get(ViewModelGlobalSearch.class);

        setId(rootView);

        mainActivity = (MainActivity) getActivity();

        setSpinners(inflater);

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
                setParams();
                onSearchButtonClick(user);
                break;
        }
    }

}
