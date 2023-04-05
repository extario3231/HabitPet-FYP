package com.example.habittrackerprojectjavaversion.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pet_progress")
public class PetProgress {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "progress_id")
    private long progressId;
    @ColumnInfo(name = "pet_image_path")
    private String petImagePath;
    private int progress;

    public PetProgress(String petImagePath, int progress) {
        this.petImagePath = petImagePath;
        this.progress = progress;
    }

    public long getProgressId() {
        return progressId;
    }

    public void setProgressId(long progressId) {
        this.progressId = progressId;
    }

    public String getPetImagePath() {
        return petImagePath;
    }

    public void setPetImagePath(String petImagePath) {
        this.petImagePath = petImagePath;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
