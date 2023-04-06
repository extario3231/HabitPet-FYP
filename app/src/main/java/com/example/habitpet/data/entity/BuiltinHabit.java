package com.example.habitpet.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "builtin_habit")
public class BuiltinHabit {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "builtin_habit_id")
    private long builtinHabitId;
    @NonNull
    private String name;

    public BuiltinHabit(String name) {
        this.name = name;
    }

    public long getBuiltinHabitId() {
        return builtinHabitId;
    }

    public void setBuiltinHabitId(long builtinHabitId) {
        this.builtinHabitId = builtinHabitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
