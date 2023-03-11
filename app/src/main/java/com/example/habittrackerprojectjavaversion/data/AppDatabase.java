package com.example.habittrackerprojectjavaversion.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Habit.class, PetProgress.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract HabitDao habitDao();
    public abstract PetProgressDao petProgressDao();

    public static AppDatabase getDatabase(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, AppDatabase.class, "habit-data.db")
                    // For database migration
//                    .addMigrations(new Migration(1, 2) {
//                        @Override
//                        public void migrate(@NonNull SupportSQLiteDatabase database) {
//                            database.execSQL("ALTER TABLE habit ADD COLUMN imageId INTEGER NOT NULL DEFAULT 0");
//                            database.execSQL("CREATE TABLE pet_progress(id INTEGER PRIMARY KEY NOT NULL, " +
//                                    "progress INTEGER NOT NULL, imageId INTEGER NOT NULL)");
//                        }
//                    })
                    .build();
        return instance;
    }
}
