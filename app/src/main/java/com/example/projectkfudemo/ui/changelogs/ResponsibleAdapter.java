package com.example.projectkfudemo.ui.changelogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkfudemo.R;
import com.example.projectkfudemo.forjson.Works;

import java.util.List;

public class ResponsibleAdapter extends RecyclerView.Adapter<ResponsibleAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Works> works;

    ResponsibleAdapter(Context context, List<Works> phones) {
        this.works = phones;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public ResponsibleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.logs_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResponsibleAdapter.ViewHolder holder, int position) {
        Works work = works.get(position);
        holder.numberView.setText(String.valueOf(works.get(position)));
        holder.dateOfView.setText(work.getDate());

    }

    @Override
    public int getItemCount() {
        return works.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView numberView, dateOfView, serviceNameView;
        ViewHolder(View view){
            super(view);
            numberView = (TextView) view.findViewById(R.id.number);
            dateOfView = (TextView) view.findViewById(R.id.date_of);
            serviceNameView = (TextView) view.findViewById(R.id.service_name);
        }
    }
}
