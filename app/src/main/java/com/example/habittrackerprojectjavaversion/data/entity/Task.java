package com.example.habittrackerprojectjavaversion.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "task", foreignKeys = {@ForeignKey(entity = Habit.class, parentColumns = {"id"},
        childColumns = {"habit_id"}, onDelete = ForeignKey.CASCADE)})
public class Task {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String name;
    @ColumnInfo(name = "habit_id")
    private long habitId;

    public Task(String name, long habitId) {
        this.name = name;
        this.habitId = habitId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHabitId() {
        return habitId;
    }

    public void setHabitId(long habitId) {
        this.habitId = habitId;
    }
}
