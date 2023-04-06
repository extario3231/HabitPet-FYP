package com.example.habitpet.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.example.habitpet.R;

import java.util.Calendar;

public class SetTime extends AppCompatActivity {

    PendingIntent pending_intent;
    AlarmManager alarm_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        notification_cannel();
        pending_intent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(this, broadcast_receiver.class), PendingIntent.FLAG_IMMUTABLE);
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Button dailyButton = findViewById(R.id.daily_button);
        dailyButton.setOnClickListener(e -> {
            set_notification_alarm(1 * 1000);
            finish();
        });

        Button twoDaysButton = findViewById(R.id.two_days_button);
        twoDaysButton.setOnClickListener(e -> {
            set_notification_alarm(2* 24 * 60 * 60 * 1000);
            finish();
        });
        Button weeklyButton = findViewById(R.id.weekly_button);
        weeklyButton.setOnClickListener(e -> {
            set_notification_alarm(7* 24 * 60 * 60 * 1000);
            finish();
        });

        /*Button stop_notifications = findViewById(R.id.stop_notifications);
        stop_notifications.setOnClickListener(e->{
            cancel_notification_alarm();
        }); */

    }

    public void set_notification_alarm(long interval) {
        long triggerAtMillis = System.currentTimeMillis() + interval;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        if (Build.VERSION.SDK_INT >= 23) {
            alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pending_intent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarm_manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pending_intent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarm_manager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pending_intent);
        } else {
            alarm_manager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pending_intent);
        }
    }


    public void cancel_notification_alarm() {
        alarm_manager.cancel(pending_intent);
    }

    private void notification_cannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "Hey! I am hungry!";
            String description = "Finish the task and feed me!";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Notification",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}