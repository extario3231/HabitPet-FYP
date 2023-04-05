package com.example.habittrackerprojectjavaversion.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.habittrackerprojectjavaversion.data.dao.BuiltinHabitDao;
import com.example.habittrackerprojectjavaversion.data.dao.HabitDao;
import com.example.habittrackerprojectjavaversion.data.dao.PetProgressDao;
import com.example.habittrackerprojectjavaversion.data.dao.TaskDao;
import com.example.habittrackerprojectjavaversion.data.entity.BuiltinHabit;
import com.example.habittrackerprojectjavaversion.data.entity.Habit;
import com.example.habittrackerprojectjavaversion.data.entity.PetProgress;
import com.example.habittrackerprojectjavaversion.data.entity.Task;

@Database(entities = {Habit.class, PetProgress.class, Task.class, BuiltinHabit.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract HabitDao habitDao();
    public abstract PetProgressDao petProgressDao();
    public abstract TaskDao taskDao();
    public abstract BuiltinHabitDao builtinHabitDao();

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
