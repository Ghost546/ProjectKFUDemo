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
        TextView numberSpace = (TextView) convertView.findViewById(R.id.number_space);
        TextView dateOfView = (TextView) convertView.findViewById(R.id.date_of);
        TextView dateOfSpace = (TextView) convertView.findViewById(R.id.date_of_space);
        TextView serviceNameView = (TextView) convertView.findViewById(R.id.service_name);
        TextView serviceNameSpace = (TextView) convertView.findViewById(R.id.service_name_space);
        TextView typeNameView = (TextView) convertView.findViewById(R.id.type_name);
        TextView typeNameSpace = (TextView) convertView.findViewById(R.id.type_name_space);
        TextView descriptionView = (TextView) convertView.findViewById(R.id.description);

        Works works = worksList.get(position);

        numberView.setText(String.valueOf(position));
        numberView.setVisibility(View.VISIBLE);
        numberSpace.setVisibility(View.VISIBLE);

        if(works.getDate()!=null) {
            dateOfView.setText(works.getDate());
            dateOfView.setVisibility(View.VISIBLE);
            dateOfSpace.setVisibility(View.VISIBLE);
        }
        if(works.getServiceName()!= null) {
            serviceNameView.setText(works.getServiceName());
            serviceNameView.setVisibility(View.VISIBLE);
            serviceNameSpace.setVisibility(View.VISIBLE);
        }
        if(works.getTypeName()!=null) {
            typeNameView.setText(works.getTypeName());
            typeNameView.setVisibility(View.VISIBLE);
            typeNameSpace.setVisibility(View.VISIBLE);
        }
        if(works.getDescription()!=null) {
            descriptionView.setText(works.getDescription());
            descriptionView.setVisibility(View.VISIBLE);
        }


        return convertView;
    }
}





//