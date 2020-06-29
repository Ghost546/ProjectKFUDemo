package com.example.projectkfudemo.ui.changelogs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeLogsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeLogsFragment extends Fragment {

//    private static Bundle arg;

    private Request request;
    private TextView changeLogsText;

    public ChangeLogsFragment() {
        // Required empty public constructor
    }

    public static ChangeLogsFragment newInstance(Request request) {
        ChangeLogsFragment fragment = new ChangeLogsFragment();
        fragment.request = request;
        return fragment;
    }

    private void setIds(View root) {
        changeLogsText = root.findViewById(R.id.change_logs_text);
    }

    private void setView(Request request) {
        String text = "";
        for(int i = 0; i < request.getActionsOverRequest().size(); i++) {
            text += request.getActionsOverRequest().get(i).getComment() + "\n";
            text += "\n";
        }
        changeLogsText.setText(text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_change_logs, container, false);
        setIds(root);
        setView(request);
        return root;
    }

}