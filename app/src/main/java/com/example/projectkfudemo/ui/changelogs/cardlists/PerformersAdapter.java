package com.example.projectkfudemo.ui.changelogs.cardlists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.forjson.Workers;
import com.example.projectkfudemo.forjson.Works;

import java.util.List;

public class PerformersAdapter extends ArrayAdapter<Workers> {
    private LayoutInflater inflater;
    private int layout;
    private List<Workers> workersList;


    public PerformersAdapter(Context context, int resource, List<Workers> workersList) {
        super(context, resource, workersList);
        this.workersList = workersList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.text);
        TextView textSpace = convertView.findViewById(R.id.text_space);

        Workers worker = workersList.get(position);

        if (worker.getFullname()!=null) {
            textView.setText(worker.getFullname());
            textView.setVisibility(View.VISIBLE);
            textSpace.setVisibility(View.VISIBLE);
        }



        return convertView;
    }
}
