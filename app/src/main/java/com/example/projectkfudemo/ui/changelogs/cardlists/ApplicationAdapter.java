package com.example.projectkfudemo.ui.changelogs.cardlists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.forjson.Log;
import com.example.projectkfudemo.forjson.Workers;

import org.joda.time.format.DateTimeFormat;

import java.util.List;

public class ApplicationAdapter extends ArrayAdapter<Log> {
    private LayoutInflater inflater;
    private int layout;
    private List<Log> logsList;


    public ApplicationAdapter(Context context, int resource, List<Log> logsList) {
        super(context, resource, logsList);
        this.logsList = logsList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
        }

        TextView dateView = convertView.findViewById(R.id.change_date);
        TextView dateSpace = convertView.findViewById(R.id.change_date_space);
        TextView statusView = convertView.findViewById(R.id.status_name);
        TextView statusSpace = convertView.findViewById(R.id.status_name_space);
        TextView userView = convertView.findViewById(R.id.change_user_name);
        TextView userSpace = convertView.findViewById(R.id.change_user_name_space);
        TextView commentsView = convertView.findViewById(R.id.comments);


        Log log = logsList.get(position);

        if(log.getChangeUserFullName()!=null) {
            dateView.setText(log.getChangeDateString());
            dateView.setVisibility(View.VISIBLE);
            dateSpace.setVisibility(View.VISIBLE);
        }

        if(log.getStatusName()!=null) {
            statusView.setText(log.getStatusName());
            statusView.setVisibility(View.VISIBLE);
            statusSpace.setVisibility(View.VISIBLE);
        }

        if(log.getChangeUserFullName()!=null) {
            userView.setText(log.getChangeUserFullName());
            userView.setVisibility(View.VISIBLE);
            userSpace.setVisibility(View.VISIBLE);
        }

        if(log.getComment()!=null) {
            commentsView.setText(log.getComment());
            commentsView.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
