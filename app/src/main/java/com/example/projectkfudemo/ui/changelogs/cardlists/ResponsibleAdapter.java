package com.example.projectkfudemo.ui.changelogs.cardlists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.Request;
import com.example.projectkfudemo.forjson.Works;

import java.util.List;

public class ResponsibleAdapter extends ArrayAdapter<Works> {
    private LayoutInflater inflater;
    private int layout;
    private List<Works> worksList;


    public ResponsibleAdapter(Context context, int resource, List<Works> works) {
        super(context, resource, works);
        this.worksList = works;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView numberView = (TextView) convertView.findViewById(R.id.number);
        TextView dateOfView = (TextView) convertView.findViewById(R.id.date_of);
        TextView serviceNameView = (TextView) convertView.findViewById(R.id.service_name);
        TextView typeNameView = (TextView) convertView.findViewById(R.id.type_name);
        TextView descriptionView = (TextView) convertView.findViewById(R.id.description);

        Works works = worksList.get(position);

//        numberView.setText(String.valueOf(worksList.get(position)));
        dateOfView.setText(works.getDate());
        serviceNameView.setText(works.getServiceName());
        typeNameView.setText(works.getTypeName());
        descriptionView.setText(works.getDescription());

        return convertView;
    }
}





//