package com.example.habittrackerprojectjavaversion.data;

import android.os.Looper;

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
        return Single.just(taskDao.getAll()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Single<List<HabitAndTasks>> getByHabit(String habitName) {
        return Single.just(taskDao.getByHabit(habitName)).subscribeOn(Schedulers.io())
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
