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

    public String addingString(String startString, String addString) {
        String finalString;
        finalString = startString + "\n" + addString;
        return finalString;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.text);
        TextView textTopSpace = convertView.findViewById(R.id.text_top_space);
        TextView textBottomSpace = convertView.findViewById(R.id.text_bottom_space);
        TextView textGroupName = convertView.findViewById(R.id.group_name);

        Workers worker = workersList.get(position);



        if (worker.getFullname()!=null & !worker.getTechGroup().getGroupName().equals("")) {
            String outputText;
            outputText = worker.getFullname() + "\n";
            for(int i = 0; i<workersList.size(); i++) {
                if(!workersList.get(i).getFullname().equals(workersList.get(position).getFullname())) {
                    if(workersList.get(i).getTechGroup().getGroupName().equals(workersList.get(position).getTechGroup().getGroupName())) {
                        outputText = addingString(outputText, workersList.get(i).getFullname());
                        workersList.get(i).getTechGroup().setGroupName("");
                    }
                }
            }
            textView.setText(outputText);
            textGroupName.setText(worker.getTechGroup().getGroupName());
            textView.setVisibility(View.VISIBLE);
            textGroupName.setVisibility(View.VISIBLE);
            textBottomSpace.setVisibility(View.VISIBLE);
            textTopSpace.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
