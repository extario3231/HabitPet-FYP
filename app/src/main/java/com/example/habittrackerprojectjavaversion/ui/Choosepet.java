package com.example.habittrackerprojectjavaversion.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.habittrackerprojectjavaversion.R;
import com.example.habittrackerprojectjavaversion.data.PetModel;

import java.util.ArrayList;

public class Choosepet extends AppCompatActivity {
    ArrayList<PetModel> petModels = new ArrayList<>();

    int[] petImages = {R.drawable.dog_rv,R.drawable.cat_rv,R.drawable.bd,R.drawable.bd};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepet);

        RecyclerView recyclerView = findViewById(R.id.mRecycleViewpet);

        setupPetModels();

        Pet_RecycleViewAdapter adapter = new Pet_RecycleViewAdapter(this,petModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupPetModels(){
        String[] petNames = getResources().getStringArray(R.array.petname);
        String[] petSounds = getResources().getStringArray(R.array.petsound);

        for (int i = 0 ; i < petNames.length;i++){
            petModels.add(new PetModel(petNames[i],petSounds[i],petImages[i]));
        }
    }


}