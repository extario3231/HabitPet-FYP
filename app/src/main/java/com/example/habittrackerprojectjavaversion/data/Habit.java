package com.example.habittrackerprojectjavaversion.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habit")
public class Habit {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String date;
    @ColumnInfo(name = "all_tasks_completed")
    private Character areAllTasksCompleted;
    private int imageId;

    public Habit(String name, String date, Character areAllTasksCompleted, int imageId) {
        this.name = name;
        this.date = date;
        this.areAllTasksCompleted = areAllTasksCompleted;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Character getAreAllTasksCompleted() {
        return areAllTasksCompleted;
    }

    public void setAreAllTasksCompleted(Character areAllTasksCompleted) {
        this.areAllTasksCompleted = areAllTasksCompleted;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}