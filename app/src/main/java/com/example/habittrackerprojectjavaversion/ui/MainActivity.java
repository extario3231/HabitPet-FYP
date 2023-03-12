package com.example.habittrackerprojectjavaversion.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.room.Room;

import com.example.habittrackerprojectjavaversion.R;
import com.example.habittrackerprojectjavaversion.data.AppDatabase;
import com.example.habittrackerprojectjavaversion.data.Habit;
import com.example.habittrackerprojectjavaversion.data.HabitDao;
import com.example.habittrackerprojectjavaversion.data.HabitRepo;
import com.example.habittrackerprojectjavaversion.data.PetProgress;
import com.example.habittrackerprojectjavaversion.data.PetProgressDao;
import com.example.habittrackerprojectjavaversion.data.PetProgressRepo;
import com.example.habittrackerprojectjavaversion.data.Task;
import com.example.habittrackerprojectjavaversion.data.TaskRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class MainActivity extends Activity {
    private ImageView myImageView;
    private static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initData();

        setListeners();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.show_and_hide);
        myImageView.startAnimation(animation);

        db = AppDatabase.getDatabase(getApplicationContext());
    }

    private void initViews() {
        myImageView = (ImageView)findViewById(R.id.imageView1);
    }

    private void initData() {
    }

    private void setListeners() {
        myImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView t_v = (ImageView)v;
                if(t_v == myImageView)
                {
                    Intent intent = new Intent(MainActivity.this,NextActivity.class) ;
                    startActivity(intent);
                    finish();
                }
                return false;
            }
        });
    }

    public static AppDatabase getDb() {
        return db;
    }
}
