package com.example.habitpet.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.habitpet.data.dao.CalendarProgressDao;
import com.example.habitpet.data.dao.HabitDao;
import com.example.habitpet.data.dao.PetProgressDao;
import com.example.habitpet.data.entity.CalendarProgress;
import com.example.habitpet.data.entity.Habit;
import com.example.habitpet.data.entity.PetProgress;

@Database(entities = { Habit.class, PetProgress.class, CalendarProgress.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract HabitDao habitDao();
    public abstract PetProgressDao petProgressDao();
    public abstract CalendarProgressDao calendarProgressDao();

    public static AppDatabase getDatabase(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, AppDatabase.class, "habit-data.db")
                    // For database migration
//                      .addMigrations(new Migration(1, 2) {
//                      @Override
//                        public void migrate(@NonNull SupportSQLiteDatabase database) {
//                            database.execSQL("CREATE TABLE task(id PRIMARY KEY NOT NULL," +
//                                    " name TEXT, habit_name TEXT)");
//                        }
//                    })
                    .allowMainThreadQueries()
                    .build();
        instance.getOpenHelper().getWritableDatabase();
        return instance;
    }
}
