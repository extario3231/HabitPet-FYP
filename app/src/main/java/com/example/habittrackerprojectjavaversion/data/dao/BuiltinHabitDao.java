package com.example.habittrackerprojectjavaversion.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.habittrackerprojectjavaversion.data.entity.BuiltinHabit;

import java.util.List;

@Dao
public interface BuiltinHabitDao {
    @Insert
    void insert(BuiltinHabit habit);

    @Insert
    void insertAll(List<BuiltinHabit> habits);

    @Query("SELECT * FROM builtin_habit")
    List<BuiltinHabit> getAll();

    @Query("SELECT * FROM builtin_habit WHERE name = :name")
    BuiltinHabit getByName(String name);
}
