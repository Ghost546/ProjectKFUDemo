package com.example.projectkfudemo.architecturalcomponents.ui.changelogs.cardlists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.parametrclasses.forjson.Workers;

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
        for(int i = 0; i < workersList.size(); i++) {
            for(int j = 0; j < workersList.size(); j++) {
                String text;
                text = workersList.get(i).getFullname();                                            //берем имя
                if(!workersList.get(i).getFullname().equals(workersList.get(j).getFullname())) {    //ищем отличные от него имена
                    if(workersList.get(i).getTechGroup().getGroupName().equals(workersList.get(j).getTechGroup().getGroupName())) { //ищем у имен
                        text = addingString(text, workersList.get(j).getFullname());
                        workersList.get(i).setFullname(text);
                        workersList.remove(j);
                        j--;
                    }
                }

            }
        }
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
            outputText = worker.getFullname();

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
