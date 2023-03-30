package com.example.habittrackerprojectjavaversion.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.habittrackerprojectjavaversion.R;
import com.example.habittrackerprojectjavaversion.data.PetModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Choosepet extends AppCompatActivity implements SelectListener {
    ArrayList<PetModel> petModels = new ArrayList<>();
    ImageView petImageView;
    Handler handler;
    int[] petImages = {R.drawable.dog_rv,R.drawable.cat_rv,R.drawable.bd,R.drawable.bd};
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
        String[] petNames = getResources().getStringArray(R.array.petname);
        String[] petSounds = getResources().getStringArray(R.array.petsound);

        for (int i = 0 ; i < petNames.length;i++){
            petModels.add(new PetModel(petNames[i],petSounds[i],petImages[i]));
        }
    }


    @Override
    public void onItemClicked(PetModel petModel) {
        Toast.makeText(this,"You are successfully changed to " + petModel.getPetname(),Toast.LENGTH_SHORT).show();

        petImageView = (ImageView) findViewById(R.id.petImageView);
        Thread thread = new Thread(()->{
            if (petModel.getPetname() == "Dog"){
                handler.post(()->petImageView.setImageResource(R.drawable.babydog));
                finish();
            } else if(petModel.getPetname() == "Cat"){
                handler.post(()->petImageView.setImageResource(R.drawable.testingimage));
                finish();
            } else {
                handler.post(()->petImageView.setImageResource(R.drawable.babybird));
                finish();
            }
        });

        thread.start();
    }
}