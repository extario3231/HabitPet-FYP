package com.example.habitpet.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.habitpet.R;
import com.example.habitpet.data.SummaryNameMapping;

import java.util.ArrayList;

public class SummaryAdapter extends ArrayAdapter<SummaryNameMapping>{
    public SummaryAdapter(Activity context, ArrayList<SummaryNameMapping> showhabitlist){
        super(context, 0, showhabitlist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_summary_item, parent, false);
        }


        final SummaryNameMapping currentName = getItem(position);


        TextView default_text_view = listItemView.findViewById(R.id.default_text_view);
        default_text_view.setText(currentName.getHabitname());


        return listItemView;
    }


}
