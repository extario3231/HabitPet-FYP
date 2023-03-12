package com.example.habittrackerprojectjavaversion.data;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HabitRepo {
    private AppDatabase db;
    private HabitDao habitDao;

    public HabitRepo(AppDatabase db) {
        this.db = db;
        this.habitDao = db.habitDao();
    }

    public Completable getAll() {
        return Completable.fromAction(() -> habitDao.getAll()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insert(Habit habit) {
        return Completable.fromAction(() -> habitDao.insert(habit)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Habit> getByName(String name) {
        return Single.just(habitDao.getByName(name)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Habit>> findByDate(String date) {
        return Single.just(habitDao.findByDate(date)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Habit>> getAllByAllTaskCompletion() {
        return Single.just(habitDao.getAllByAllTaskCompletion()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Habit>> findByPeriod(String startDate, String endDate) {
        return Single.just(habitDao.findByPeriod(startDate, endDate)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insertAll(List<Habit> habits) {
        return Completable.fromAction(() -> habitDao.insertAll(habits)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable delete(Habit habit) {
        return Completable.fromAction(() -> habitDao.delete(habit)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable update(Habit habit) {
        return Completable.fromAction(() -> habitDao.update(habit)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
