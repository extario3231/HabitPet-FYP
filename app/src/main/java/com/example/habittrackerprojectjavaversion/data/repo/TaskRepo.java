package com.example.habittrackerprojectjavaversion.data.repo;

import android.os.Looper;

import com.example.habittrackerprojectjavaversion.data.AppDatabase;
import com.example.habittrackerprojectjavaversion.data.entity.HabitAndTasks;
import com.example.habittrackerprojectjavaversion.data.dao.TaskDao;
import com.example.habittrackerprojectjavaversion.data.entity.Task;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TaskRepo {
    private final TaskDao taskDao;

    public TaskRepo(AppDatabase db) {
        this.taskDao = db.taskDao();
    }

    public Single<List<Task>> getAll() {
        return Single.just(taskDao.findAll()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Single<List<HabitAndTasks>> getByHabit(String habitName) {
        return Single.just(taskDao.findByHabit(habitName)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable addTasks(List<Task> tasks) {
        return Completable.fromAction(() -> taskDao.addTasks(tasks)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable addTask(Task task) {
        return Completable.fromAction(() -> taskDao.addTask(task)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable update(Task task) {
        return Completable.fromAction(() -> taskDao.update(task)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable delete(Task task) {
        return Completable.fromAction(() -> taskDao.delete(task)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }
}
