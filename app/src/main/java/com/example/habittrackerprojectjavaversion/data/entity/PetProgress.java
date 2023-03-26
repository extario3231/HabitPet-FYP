package com.example.habittrackerprojectjavaversion.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pet_progress")
public class PetProgress {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "progress_id")
    private long progressId;
    @ColumnInfo(name = "image_id")
    private int imageId;
    private int progress;

    public PetProgress(int imageId, int progress) {
        this.imageId = imageId;
        this.progress = progress;
    }

    public long getProgressId() {
        return progressId;
    }

    public void setProgressId(long progressId) {
        this.progressId = progressId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
