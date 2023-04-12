package com.example.habitpet.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.habitpet.data.entity.CalendarProgress;

import java.util.List;

@Dao
public interface CalendarProgressDao {
    @Query("SELECT * FROM calendar_progress")
    List<CalendarProgress> findProgress();
    @Query("SELECT * FROM calendar_progress WHERE habit_name = :name")
    List<CalendarProgress> findProgressByName(String name);
    @Query("SELECT year FROM calendar_progress WHERE habit_name = :name")
    int findYear(String name);
    @Query("SELECT month FROM calendar_progress WHERE habit_name = :name")
    int findMonth(String name);
    @Query("SELECT day FROM calendar_progress WHERE habit_name = :name")
    int findDay(String name);
    @Insert
    void insert(CalendarProgress calendarProgress);
    @Update
    void update(CalendarProgress calendarProgress);
}