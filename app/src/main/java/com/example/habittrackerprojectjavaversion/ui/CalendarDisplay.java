package com.example.habittrackerprojectjavaversion.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.CalendarView;
import com.example.habittrackerprojectjavaversion.R;

public class CalendarDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProgressDialog dialog = ProgressDialog.show(this, "", "Processing");
        new Thread(() -> {
            runOnUiThread(() -> {
                setContentView(R.layout.activity_calendar_display);
                dialog.dismiss();
                setView();
            });
        }).start();
    }

    private void setView() {
        Button btSetTarget, btClearTarget, btToday, btGetDay;
        CalendarView calendarView = findViewById(R.id.calendarView);
        btSetTarget = findViewById(R.id.button_SetTarget);

        List<EventDay> event = new ArrayList<>();
        btSetTarget.setOnClickListener(v -> {
            new Thread(() -> {
                for (java.util.Calendar calendar : calendarView.getSelectedDates()) {
                    calendar.setTime(calendar.getTime());
                    event.add(new EventDay(calendar, R.drawable.ic_baseline_favorite_24));
                    runOnUiThread(() -> {
                        calendarView.setEvents(event);
                    });
                }
            }).start();
        });

    }
}