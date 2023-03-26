package com.example.habittrackerprojectjavaversion.data.entity;

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
    @NonNull
    @ColumnInfo(name = "start_date")
    private String startDate;
    @NonNull
    @ColumnInfo(name = "all_tasks_completed", defaultValue = "N")
    private String areAllTasksCompleted;
    @ColumnInfo(name = "image_id")
    private int imageId;

    public Habit(String name, String startDate, String areAllTasksCompleted, int imageId) {
        this.name = name;
        this.startDate = startDate;
        this.areAllTasksCompleted = areAllTasksCompleted;
        this.imageId = imageId;
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

    public String getAreAllTasksCompleted() {
        return areAllTasksCompleted;
    }

    public void setAreAllTasksCompleted(String areAllTasksCompleted) {
        this.areAllTasksCompleted = areAllTasksCompleted;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}