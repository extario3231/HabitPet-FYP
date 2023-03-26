package com.example.habittrackerprojectjavaversion.data.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class HabitAndTasks {
    @Embedded
    private Habit habit;
    @Relation(parentColumn = "habit_id", entityColumn = "habit_task_id")
    private List<Task> tasks;

    public HabitAndTasks(Habit habit, List<Task> tasks) {
        this.habit = habit;
        this.tasks = tasks;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
