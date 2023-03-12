package com.example.habittrackerprojectjavaversion.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.habittrackerprojectjavaversion.data.entity.PetProgress;

@Dao
public interface PetProgressDao {
    @Query("SELECT * FROM pet_progress")
    PetProgress getProgress();

    @Insert
    void insert(PetProgress progress);

    @Update
    void updateProgress(PetProgress newProgress);
}
