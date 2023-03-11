package com.example.habittrackerprojectjavaversion.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PetProgressDao {
    @Query("SELECT * FROM pet_progress")
    PetProgress getProgress();

    @Insert
    void insert(PetProgress progress);

    @Update
    void updateProgress(PetProgress newProgress);
}
