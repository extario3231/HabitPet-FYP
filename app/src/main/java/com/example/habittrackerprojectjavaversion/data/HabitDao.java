package com.example.habittrackerprojectjavaversion.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HabitDao {
    @Query("SELECT * FROM habit")
    List<Habit> getAll();

    @Query("SELECT * FROM habit WHERE name = :name")
    Habit getByName(String name);

    @Query("SELECT * FROM habit WHERE all_tasks_completed = 'Y'")
    List<Habit> getAllByAllTaskCompletion();

    @Query("SELECT * FROM habit WHERE date = :date")
    List<Habit> findByDate(String date);

    @Query("SELECT * FROM habit WHERE date BETWEEN :startDate AND :endDate")
    List<Habit> findByPeriod(String startDate, String endDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Habit> habits);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Habit habit);

    @Delete
    void delete(Habit habit);

    @Update
    void update(Habit habit);
}