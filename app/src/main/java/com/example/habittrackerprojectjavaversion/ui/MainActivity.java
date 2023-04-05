package com.example.habittrackerprojectjavaversion.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.habittrackerprojectjavaversion.R;
import com.example.habittrackerprojectjavaversion.data.AppDatabase;
import com.example.habittrackerprojectjavaversion.data.entity.PetProgress;
import com.example.habittrackerprojectjavaversion.data.repo.PetProgressRepo;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends Activity {
    private ImageView myImageView;
    private static AppDatabase db;
    private PetProgressRepo progressRepo;
    private final String TAG = "MainActivity";
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initData();

        setListeners();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.show_and_hide);
        myImageView.startAnimation(animation);

        compositeDisposable = new CompositeDisposable();
        db = AppDatabase.getDatabase(getApplicationContext());
        progressRepo = new PetProgressRepo(db);
        Disposable disposable = initDb();
        compositeDisposable.add(disposable);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public static AppDatabase getDb() {
        return db;
    }

    public Disposable initDb() {
        PetProgress progress = db.petProgressDao().findProgress();
        if (progress != null) return Disposable.empty();

        return progressRepo.insert(new PetProgress("/res/drawable/cat.png", 0))
                .subscribe(() -> Log.i(TAG, "init complete."),
                        err -> Log.e(TAG, "init failed: " + err.getMessage()));
    }
}
