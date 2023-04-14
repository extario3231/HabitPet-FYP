package com.example.habitpet.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habit")
public class Habit {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "habit_id")
    private long habitId;
    @NonNull
    private String name;
    @ColumnInfo(name = "start_date")
    private String startDate;
    @ColumnInfo(name = "image_path")
    private String imagePath;
    @ColumnInfo(name = "is_builtin")
    private boolean isBuiltin;
    @ColumnInfo(name= "is_wanted_habit")
    private boolean isWantedHabit;

    public Habit(@NonNull String name, String startDate, String imagePath, boolean isBuiltin) {
        this.name = name;
        this.startDate = startDate;
        this.imagePath = imagePath;
        this.isBuiltin = isBuiltin;
    }

    public long getHabitId() {
        return habitId;
    }

    public void setHabitId(long habitId) {
        this.habitId = habitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isBuiltin() {
        return isBuiltin;
    }

    public void setBuiltin(boolean builtin) {
        isBuiltin = builtin;
    }

    public boolean isWantedHabit() {
        return isWantedHabit;
    }

    public void setWantedHabit(boolean wantedHabit) {
        isWantedHabit = wantedHabit;
    }
}