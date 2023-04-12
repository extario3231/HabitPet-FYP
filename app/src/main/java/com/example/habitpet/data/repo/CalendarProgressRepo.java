package com.example.habitpet.data.repo;

import com.example.habitpet.data.AppDatabase;
import com.example.habitpet.data.dao.CalendarProgressDao;
import com.example.habitpet.data.entity.CalendarProgress;

public class CalendarProgressRepo {
    private final CalendarProgressDao calendarProgressDao;
    public CalendarProgressRepo(AppDatabase db){this.calendarProgressDao = db.calendarProgressDao();}

}
