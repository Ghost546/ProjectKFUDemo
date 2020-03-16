package com.example.projectkfudemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.joda.time.LocalDate;
import java.util.List;

public class CurrentRequestStateAdapter extends ArrayAdapter<Request> {
    private LayoutInflater inflater;
    private int layout;
    private List<Request> requests;


    public CurrentRequestStateAdapter(Context context, int resource, List<Request> requests) {
        super(context, resource, requests);
        this.requests = requests;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView idView = view.findViewById(R.id.request_code);
        TextView textView = view.findViewById(R.id.text_of_request);
        TextView statusView = view.findViewById(R.id.status);
        TextView dateView = view.findViewById(R.id.date);

        Request request = requests.get(position);

        idView.setText(Integer.toString(request.getCode()));
        textView.setText(request.getWorksList().get(0).getDescription());
        statusView.setText(request.getStatusOfRequest());
        dateView.setText(String.valueOf(request.getPeriodOfExecution()));

        return view;
    }
}
