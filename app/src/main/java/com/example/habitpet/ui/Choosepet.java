package com.example.habitpet.ui;

import static com.example.habitpet.ui.MainActivity.getDb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.habitpet.R;
import com.example.habitpet.data.PetModel;
import com.example.habitpet.data.entity.PetProgress;

import java.util.ArrayList;

public class Choosepet extends AppCompatActivity implements SelectListener {
    ArrayList<PetModel> petModels = new ArrayList<>();

    int[] petImages = {R.drawable.dog_rv,R.drawable.cat_rv,R.drawable.bd};
    String[] petNames = new String[]{"Dog", "Cat", "Bird"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepet);

        RecyclerView recyclerView = findViewById(R.id.mRecycleViewpet);

        setupPetModels();

        Pet_RecycleViewAdapter adapter = new Pet_RecycleViewAdapter(this,petModels,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupPetModels(){
        String[] petSounds = getResources().getStringArray(R.array.petsound);

        for (int i = 0 ; i < petNames.length;i++){
            petModels.add(new PetModel(petNames[i],petSounds[i],petImages[i]));
        }
    }
    @Override
    public void onItemClicked(PetModel petModel) {
        Toast.makeText(this,"You are successfully changed to " + petModel.getPetname(),Toast.LENGTH_SHORT).show();

        // define PetProgress objects first
        PetProgress pg = getDb().petProgressDao().findPetName("cat");
        PetProgress pg2 = getDb().petProgressDao().findPetName("dog");
        PetProgress pg3 = getDb().petProgressDao().findPetName("bird");

        // get the name from user action
        if (petModel.getPetname()=="Cat"){
            // set the true value to the object with the same getPetname()
            pg.setPet(true);
            pg2.setPet(false);
            pg3.setPet(false);
            // update PetProgress
            getDb().petProgressDao().updateProgress(pg);
            getDb().petProgressDao().updateProgress(pg2);
            getDb().petProgressDao().updateProgress(pg3);
        } else if(petModel.getPetname()=="Dog"){
            // set the true value to the object with the same getPetname()
            pg.setPet(false);
            pg2.setPet(true);
            pg3.setPet(false);
            // update PetProgress
            getDb().petProgressDao().updateProgress(pg);
            getDb().petProgressDao().updateProgress(pg2);
            getDb().petProgressDao().updateProgress(pg3);
        } else if(petModel.getPetname()=="Bird"){
            // set the true value to the object with the same getPetname()
            pg.setPet(false);
            pg2.setPet(false);
            pg3.setPet(true);
            // update PetProgress
            getDb().petProgressDao().updateProgress(pg);
            getDb().petProgressDao().updateProgress(pg2);
            getDb().petProgressDao().updateProgress(pg3);
        }
        // kill the activity after user choosing pet
        finish();
    }
}