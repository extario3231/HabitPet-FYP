package com.example.habitpet.ui;

import android.net.Uri;

import com.example.habitpet.R;
import com.example.habitpet.data.NameMapping;

import java.util.ArrayList;
import java.util.List;

public class habits extends ArrayList<NameMapping> {

    public ArrayList<NameMapping> allhabits = new ArrayList<NameMapping>();
    // List of books for display in the items of gridview
    public ArrayList<NameMapping> selectedhabitList = new ArrayList<NameMapping>();

    public habits(){
        NameMapping nameMapping;

        nameMapping = new NameMapping("Sleep and wake up early", R.mipmap.sleep);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Sport regularly", R.mipmap.sport);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Football", R.mipmap.football);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Basketball", R.mipmap.basketball);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Badminton", R.mipmap.badminton);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Tennis", R.mipmap.tennis);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Table Tennis", R.mipmap.tabletennis);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Golf", R.mipmap.golf);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Baseball", R.mipmap.baseball);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Gym",  R.mipmap.gym);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Yoga",  R.mipmap.yoga);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Drinking more water", R.mipmap.water);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Eating Healthy food", R.mipmap.food);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Lazy",R.mipmap.lazy);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Track spending",R.mipmap.trackspending);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("Check email",R.mipmap.checkemail);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("No smoking",  R.mipmap.nosmoke);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("No alcohol",  R.mipmap.noalcohol);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("No soda",  R.mipmap.nosoda);
        allhabits.add(nameMapping);

        nameMapping = new NameMapping("No sweets",  R.mipmap.nosweets);
        allhabits.add(nameMapping);


    }

    public void setSelection(String selection) {
        // Clear book list for display
        selectedhabitList.clear();

        // Add book to selected list according to spinner selection (either all or favorite)
        if (selection.equals("All")) {
            for (NameMapping nameMapping : allhabits) {
                selectedhabitList.add(nameMapping);
            }
        } else {
            for (NameMapping nameMapping : allhabits) {
                if (nameMapping.isFavorite())
                    selectedhabitList.add(nameMapping);
            }
        }
    }

    public List<NameMapping> getSelectedhabitList() {
        return selectedhabitList;
    }

    public void addhabit(String habit){
        NameMapping nameMapping;
        nameMapping = new NameMapping(habit, R.mipmap.custom);
        nameMapping.toggleFavorite();
        allhabits.add(nameMapping);
    }

    public void addhabitwithimage(String habit, Uri uri){
        NameMapping nameMapping;
        nameMapping = new NameMapping(habit, uri);
        nameMapping.toggleFavorite();
        allhabits.add(nameMapping);
    }

    public void setfavor(int pos){
        NameMapping nameMapping = allhabits.get(pos);
        nameMapping.toggleFavorite();

    }

}



