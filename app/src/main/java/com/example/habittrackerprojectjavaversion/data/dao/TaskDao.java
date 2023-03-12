package com.example.habittrackerprojectjavaversion.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.habittrackerprojectjavaversion.data.entity.HabitAndTasks;
import com.example.habittrackerprojectjavaversion.data.entity.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Transaction
    @Query("SELECT * FROM habit WHERE name = :habitName")
    List<HabitAndTasks> getByHabit(String habitName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTask(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}
