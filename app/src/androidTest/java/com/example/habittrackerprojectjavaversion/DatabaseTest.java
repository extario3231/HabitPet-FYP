package com.example.habittrackerprojectjavaversion;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.habittrackerprojectjavaversion.data.AppDatabase;
import com.example.habittrackerprojectjavaversion.data.dao.HabitDao;
import com.example.habittrackerprojectjavaversion.data.dao.TaskDao;
import com.example.habittrackerprojectjavaversion.data.entity.Habit;
import com.example.habittrackerprojectjavaversion.data.entity.HabitAndTasks;
import com.example.habittrackerprojectjavaversion.data.entity.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private HabitDao habitDao;
    private TaskDao taskDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        habitDao = db.habitDao();
        taskDao = db.taskDao();
    }

    @Test
    public void insertAndReadTest() {
        Habit habit = new Habit("tennis", "2023/8/9", "N", "5669");
        habitDao.insert(habit);
        Habit tennis = habitDao.findByName("tennis");
        assertThat(tennis.getName(), equalTo(habit.getName()));
    }

    @Test
    public void updateAndReadTest() {
        Habit habit = new Habit("tennis", "2023/8/9", "N", "5669");
        habitDao.insert(habit);
        Habit habitToUpdate = habitDao.findByName("tennis");
        habitToUpdate.setName("football");
        habitDao.update(habitToUpdate);
        Habit football = habitDao.findByName("football");
        assertThat(football.getName(), equalTo("football"));
    }

    @Test
    public void findHabitByDateTest() {
        Habit habitToInsert = new Habit("tennis", "2023/8/9", "N", "5669");
        habitDao.insert(habitToInsert);
        List<Habit> habit = habitDao.findByDate("2023/8/9");
        assertThat(habit.get(0).getName(), equalTo("tennis"));
    }

    @Test
    public void findHabitByTask() {
        Habit habitToInsert = new Habit("tennis", "2023/8/9", "N", "5669");
        long id = habitDao.insert(habitToInsert);
        Task taskToAdd = new Task("do", 23, id);
        taskDao.addTask(taskToAdd);
        List<HabitAndTasks> task = taskDao.findByHabit("tennis");
        assertThat(task.get(0).getTasks().get(0).getName(), equalTo("do"));
    }
}