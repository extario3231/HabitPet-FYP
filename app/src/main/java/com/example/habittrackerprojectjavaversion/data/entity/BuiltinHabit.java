package com.example.habittrackerprojectjavaversion.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "builtin_habit")
public class BuiltinHabit {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String name;

    public BuiltinHabit(String name) {
        this.name = name;
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
}
