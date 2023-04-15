package com.example.habitpet.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.habitpet.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NextActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    homeFragment homeFrag = new homeFragment();
    taskFragment taskFrag = new taskFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFrag).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getTitle().toString()){
                    case "Home":
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFrag).commit();
                        return true;
                    case "Task":
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, taskFrag).commit();
                        return true;
                }
                return false;
            }
        });
    }
}