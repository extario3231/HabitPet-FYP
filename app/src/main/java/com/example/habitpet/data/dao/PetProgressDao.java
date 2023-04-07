package com.example.habitpet.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.habitpet.data.entity.PetProgress;

@Dao
public interface PetProgressDao {
    @Query("SELECT * FROM pet_progress")
    PetProgress findProgress();
    @Query("SELECT * FROM pet_progress WHERE pet_name = :name")
    PetProgress findPetName(String name);
    @Query("SELECT progress FROM pet_progress WHERE pet_name = :name")
    int findExp(String name);
    @Query("SELECT petLv FROM pet_progress WHERE pet_name = :name")
    int findLv(String name);
    @Query("SELECT is_pet FROM pet_progress WHERE pet_name = :name")
    boolean isPet(String name);
    @Insert
    void insert(PetProgress progress);
    @Update
    void updateProgress(PetProgress newProgress);
}
