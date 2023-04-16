package com.example.habitpet.ui;

import static com.example.habitpet.ui.MainActivity.getDb;

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

import com.example.habitpet.R;
import com.example.habitpet.data.NameMapping;
import com.example.habitpet.data.entity.Habit;
import com.example.habitpet.data.entity.PetProgress;

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

        ImageButton tickButton = (ImageButton) listItemView.findViewById(R.id.tick);
        Button removeButton = (Button) listItemView.findViewById(R.id.remove);
        tickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You gained 2 EXP for your pet!",
                        Toast.LENGTH_SHORT).show();

                PetProgress pp = getDb().petProgressDao().findPetName("cat");
                PetProgress pp2 = getDb().petProgressDao().findPetName("dog");
                PetProgress pp3 = getDb().petProgressDao().findPetName("bird");
                if(getDb().petProgressDao().isPet("cat") == true){
                    pp.setProgress(getDb().petProgressDao().findExp("cat")+2);
                    getDb().petProgressDao().updateProgress(pp);
                }
                else if(getDb().petProgressDao().isPet("dog") == true){
                    pp2.setProgress(getDb().petProgressDao().findExp("dog")+2);
                    getDb().petProgressDao().updateProgress(pp2);
                }
                else if(getDb().petProgressDao().isPet("bird") == true){
                    pp3.setProgress(getDb().petProgressDao().findExp("bird")+2);
                    getDb().petProgressDao().updateProgress(pp3);
                }
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentName.NFavorite();
                Habit habit = getDb().habitDao().findByName(currentName.getHabitname());
                getDb().habitDao().delete(habit);
                Toast.makeText(getContext(), "You lost 2 EXP for your pet!",
                        Toast.LENGTH_SHORT).show();

                PetProgress pp = getDb().petProgressDao().findPetName("cat");
                PetProgress pp2 = getDb().petProgressDao().findPetName("dog");
                PetProgress pp3 = getDb().petProgressDao().findPetName("bird");

                if(getDb().petProgressDao().isPet("cat") == true){
                    pp.setProgress(getDb().petProgressDao().findExp("cat")-2);
                    getDb().petProgressDao().updateProgress(pp);
                }
                else if(getDb().petProgressDao().isPet("dog") == true){
                    pp2.setProgress(getDb().petProgressDao().findExp("dog")-2);
                    getDb().petProgressDao().updateProgress(pp2);
                }
                else if(getDb().petProgressDao().isPet("bird") == true){
                    pp3.setProgress(getDb().petProgressDao().findExp("bird")-2);
                    getDb().petProgressDao().updateProgress(pp3);
                }

            }
        });

        return listItemView;
    }

}

