package com.example.projectkfudemo.architecturalcomponents.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.requests.Request;

import java.util.List;

public class RequestStateAdapter extends ArrayAdapter<Request> {
    private LayoutInflater inflater;
    private int layout;
    private List<Request> requests;


    public RequestStateAdapter(Context context, int resource, List<Request> requests) {
        super(context, resource, requests);
        this.requests = requests;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView idView = convertView.findViewById(R.id.request_code);
        TextView textView = convertView.findViewById(R.id.text_of_request);
        TextView statusView = convertView.findViewById(R.id.status);
        TextView dateView = convertView.findViewById(R.id.date);

        Request request = requests.get(position);

        idView.setText(Integer.toString(request.getCode()));
        textView.setText(request.getDescriptionOnPrint());
        statusView.setText("Статус: " + request.getStatusOfRequest());
        dateView.setText(String.valueOf(request.getPeriodOfExecution()));

        return convertView;
    }

}
