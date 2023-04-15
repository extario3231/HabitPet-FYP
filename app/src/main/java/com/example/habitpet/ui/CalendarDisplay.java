package com.example.habitpet.ui;

import static com.example.habitpet.ui.MainActivity.getDb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
        TextView tv = findViewById(R.id.wantedHabitName);
        CalendarView calendarView = findViewById(R.id.calendarView);
        btSetTarget = findViewById(R.id.button_SetTarget);

        tv.setText(getDb().habitDao().findWantedHabit());
        List<EventDay> event = new ArrayList<>();
        ArrayList<Calendar> calendarArrayList = new ArrayList<>();

        List<Integer> yearList = getDb().calendarProgressDao().findYear(getDb().habitDao().findWantedHabit());
        List<Integer> monthList = getDb().calendarProgressDao().findMonth(getDb().habitDao().findWantedHabit());
        List<Integer> dayList = getDb().calendarProgressDao().findDay(getDb().habitDao().findWantedHabit());

        for(int i=0;i<100;i++){
            Calendar calendar = Calendar.getInstance();
            calendarArrayList.add(calendar);
        }

        for(int j=0;j<yearList.size();j++){
            calendarArrayList.get(j).set(
                    yearList.get(j),
                    monthList.get(j),
                    dayList.get(j)
            );
            event.add(new EventDay(calendarArrayList.get(j), R.drawable.ic_baseline_favorite_24));
        }

        calendarView.setEvents(event);

        btSetTarget.setOnClickListener(v -> {
            new Thread(() -> {
                for (java.util.Calendar calendar : calendarView.getSelectedDates()) {
                    calendar.setTime(calendar.getTime());
                    getDb().calendarProgressDao().insert(
                            new CalendarProgress(getDb().habitDao().findWantedHabit(),
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