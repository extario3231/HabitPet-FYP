package com.example.habitpet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.habitpet.data.AppDatabase;
import com.example.habitpet.data.dao.HabitDao;
import com.example.habitpet.data.entity.Habit;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private HabitDao habitDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        habitDao = db.habitDao();
    }

    @Test
    public void insertAndReadTest() {
        Habit habit = new Habit("tennis", "2023/8/9", "5669", true);
        habitDao.insert(habit);
        Habit tennis = habitDao.findByName("tennis");
        assertThat(tennis.getName(), equalTo(habit.getName()));
    }

    @Test
    public void updateAndReadTest() {
        Habit habit = new Habit("tennis", "2023/8/9", "5669", true);
        habitDao.insert(habit);
        Habit habitToUpdate = habitDao.findByName("tennis");
        habitToUpdate.setName("football");
        habitDao.update(habitToUpdate);
        Habit football = habitDao.findByName("football");
        assertThat(football.getName(), equalTo("football"));
    }

    @Test
    public void findHabitByDateTest() {
        Habit habitToInsert = new Habit("tennis", "2023/8/9", "5669", true);
        habitDao.insert(habitToInsert);
        List<Habit> habit = habitDao.findByDate("2023/8/9");
        assertThat(habit.get(0).getName(), equalTo("tennis"));
    }

    @Test
    public void findBuiltInHabitTest() {
        Habit habit1 = new Habit("tennis", "2023/8/9", "5669", true);
        habitDao.insert(habit1);
        Habit habit2 = new Habit("basketball", "2023/8/9", "5669", false);
        habitDao.insert(habit2);
        List<Habit> habit = habitDao.findByIsBuiltin(true);
        assertThat(habit.get(0).getName(), equalTo("basketball"));
    }
}