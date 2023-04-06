package com.example.habitpet.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.habitpet.data.entity.BuiltinHabit;

import java.util.List;

@Dao
public interface BuiltinHabitDao {
    @Insert
    void insert(BuiltinHabit habit);

    @Insert
    void insertAll(List<BuiltinHabit> habits);

    @Query("SELECT * FROM builtin_habit")
    List<BuiltinHabit> findAll();

    @Query("SELECT * FROM builtin_habit WHERE name = :name")
    BuiltinHabit findByName(String name);
}
