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

    @Insert
    void insert(PetProgress progress);

    @Update
    void updateProgress(PetProgress newProgress);
}
