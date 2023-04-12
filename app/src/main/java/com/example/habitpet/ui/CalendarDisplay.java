package com.example.habitpet.ui;

import static com.example.habitpet.ui.MainActivity.getDb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.CalendarView;
import com.example.habitpet.R;
import com.example.habitpet.data.entity.CalendarProgress;

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

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(
                getDb().calendarProgressDao().findYear("test"),
                getDb().calendarProgressDao().findMonth("test"),
                getDb().calendarProgressDao().findDay("test")
        );
        event.add(new EventDay(calendar1, R.drawable.ic_baseline_favorite_24));
        calendarView.setEvents(event);

        btSetTarget.setOnClickListener(v -> {
            new Thread(() -> {
                for (java.util.Calendar calendar : calendarView.getSelectedDates()) {
                    calendar.setTime(calendar.getTime());
                    getDb().calendarProgressDao().insert(
                            new CalendarProgress("test",
                                    calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DATE))
                    );
                    event.add(new EventDay(calendar, R.drawable.ic_baseline_favorite_24));
                    runOnUiThread(() -> {
                        calendarView.setEvents(event);
                    });
                }
            }).start();
        });

    }
}