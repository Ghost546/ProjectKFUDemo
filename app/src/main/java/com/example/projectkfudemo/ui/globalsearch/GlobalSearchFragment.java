package com.example.projectkfudemo.ui.globalsearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.projectkfudemo.MainActivity;
import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.RequestList;


public class GlobalSearchFragment extends Fragment implements View.OnClickListener {
    static Bundle args;

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

    RequestList requestList = new RequestList();

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

    private void onSearchButtonClick() {
        stringRequestNumber = editRequestNumber.toString();
        stringRequestRegistrationDateIdStart = editRequestRegistrationDateIdStart.toString();
        stringRequestRegistrationDateIdFinish = editRequestRegistrationDateIdFinish.toString();
        stringDeclarer = editDeclarer.toString();

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.startFragmentGlobalSearchResult(requestList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_global_search, container, false);

        setId(rootView);

        return rootView;
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                onSearchButtonClick();
                break;
        }
    }

}
