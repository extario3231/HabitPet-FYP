package com.example.habittrackerprojectjavaversion.ui;

import static com.example.habittrackerprojectjavaversion.ui.taskFragment.setIsTaskCompleted;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habittrackerprojectjavaversion.R;
import com.example.habittrackerprojectjavaversion.data.NameMapping;

import java.util.ArrayList;

public class HabitAdapter extends ArrayAdapter<NameMapping> {

    public HabitAdapter(Activity context, ArrayList<NameMapping> showhabitlist){
        super(context,0, showhabitlist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        final NameMapping currentName = getItem(position);


        TextView default_text_view = listItemView.findViewById(R.id.default_text_view);
        default_text_view.setText(currentName.getHabitname());

        ImageView image = listItemView.findViewById(R.id.image);
        if(currentName.getImageResId() == 0){
            image.setImageURI(currentName.getmUri());
        }
        else{
            image.setImageResource(currentName.getImageResId());
        }

        Button button = listItemView.findViewById(R.id.remove);
        //button.setOnClickListener(new ItemButton_Click(position));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentName.NFavorite();
            }
        });


        ImageButton tickbutton = (ImageButton) listItemView.findViewById(R.id.tick);
        tickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You gained 30 EXP for your pet!",
                        Toast.LENGTH_SHORT).show();
                setIsTaskCompleted(true);
            }
        });

        return listItemView;
    }


}

