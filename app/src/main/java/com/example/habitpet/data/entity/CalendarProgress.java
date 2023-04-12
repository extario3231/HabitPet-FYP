package com.example.habitpet.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calendar_progress")
public class CalendarProgress {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="calendar_progress_id")
    private long calendarProgressId;
    @ColumnInfo(name="habit_name")
    private String habitName;
    @ColumnInfo(name="year")
    private int year;
    @ColumnInfo(name="month")
    private int month;
    @ColumnInfo(name="day")
    private int day;

    public CalendarProgress(String habitName, int year, int month, int day){
        this.habitName = habitName;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public long getCalendarProgressId() {
        return calendarProgressId;
    }

    public void setCalendarProgressId(long calendarProgressId) {
        this.calendarProgressId = calendarProgressId;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
