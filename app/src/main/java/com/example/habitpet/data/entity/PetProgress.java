package com.example.habitpet.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pet_progress")
public class PetProgress {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "progress_id")
    private long progressId;
    @ColumnInfo(name = "pet_name")
    private String petName;
    @ColumnInfo(name = "is_pet")
    private boolean isPet;
    @ColumnInfo(name = "progress")
    private int progress;
    @ColumnInfo(name = "petLv")
    private int petLv;

    public PetProgress(String petName, boolean isPet, int progress, int petLv) {
        this.petName = petName;
        this.isPet = isPet;
        this.progress = progress;
        this.petLv = petLv;
    }

    public long getProgressId() {
        return progressId;
    }

    public void setProgressId(long progressId) {
        this.progressId = progressId;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getPetLv() {
        return petLv;
    }

    public void setPetLv(int petLv) {
        this.petLv = petLv;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public boolean isPet() {
        return isPet;
    }

    public void setPet(boolean pet) {
        isPet = pet;
    }
}
