package com.example.habitpet.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.habitpet.data.entity.Habit;

import java.util.List;

@Dao
public interface HabitDao {
    @Query("SELECT * FROM habit")
    List<Habit> findAll();

    @Query("SELECT * FROM habit WHERE name = :name")
    Habit findByName(String name);

    @Query("SELECT * FROM habit WHERE is_builtin = :isBuiltin")
    List<Habit> findByIsBuiltin(boolean isBuiltin);

    @Query("SELECT * FROM habit WHERE start_date = :date")
    List<Habit> findByDate(String date);

    @Query("SELECT * FROM habit WHERE start_date BETWEEN :startDate AND :endDate")
    List<Habit> findByPeriod(String startDate, String endDate);
    @Query("SELECT name FROM habit WHERE is_wanted_habit = 1")
    String findWantedHabit();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Habit> habits);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Habit habit);

    @Delete
    void delete(Habit habit);

    @Query("DELETE FROM habit WHERE habit_id = :id")
    void deleteById(int id);

    @Update
    void update(Habit habit);
}