package com.example.projectkfudemo.architecturalcomponents.ui.changelogs.cardlists;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.parametrclasses.forjson.Works;

import java.util.List;

public class ResponsibleAdapter extends ArrayAdapter<Works> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<Works> worksList;


    public ResponsibleAdapter(Context context, int resource, List<Works> works) {
        super(context, resource, works);
        this.worksList = works;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView numberView = convertView.findViewById(R.id.number);
        TextView numberViewHead = convertView.findViewById(R.id.number_head);
        TextView numberSpace = convertView.findViewById(R.id.number_space);
//        TextView dateOfView = (TextView) convertView.findViewById(R.id.date_of);
//        TextView dateOfSpace = convertView.findViewById(R.id.date_of_space);
        TextView serviceNameView = convertView.findViewById(R.id.service_name);
        TextView serviceNameViewHead = convertView.findViewById(R.id.service_name_head);
        TextView serviceNameSpace = convertView.findViewById(R.id.service_name_space);
        TextView typeNameView = convertView.findViewById(R.id.type_name);
        TextView typeNameViewHead = convertView.findViewById(R.id.type_name_head);
        TextView typeNameSpace = convertView.findViewById(R.id.type_name_space);
        TextView descriptionView = convertView.findViewById(R.id.description);
        TextView descriptionViewHead = convertView.findViewById(R.id.description_head);

        Works works = worksList.get(position);

        numberView.setText(" " + (position + 1));
        numberView.setVisibility(View.VISIBLE);
        numberViewHead.setVisibility(View.VISIBLE);
        numberSpace.setVisibility(View.VISIBLE);

        if(works.getServiceName()!= null) {
            serviceNameView.setText(" " + works.getServiceName());
            serviceNameView.setVisibility(View.VISIBLE);
            serviceNameViewHead.setVisibility(View.VISIBLE);
            serviceNameSpace.setVisibility(View.VISIBLE);
        }
        if(works.getTypeName()!=null) {
            typeNameView.setText(" " + works.getTypeName());
            typeNameView.setVisibility(View.VISIBLE);
            typeNameViewHead.setVisibility(View.VISIBLE);
            typeNameSpace.setVisibility(View.VISIBLE);
        }
        if(works.getDescription()!=null) {
            descriptionView.setText(" " + works.getDescription());
            descriptionViewHead.setVisibility(View.VISIBLE);
            descriptionView.setVisibility(View.VISIBLE);
        }


        return convertView;
    }
}





//