package com.example.habittrackerprojectjavaversion.data;

import android.os.Looper;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HabitRepo {
    private final HabitDao habitDao;

    public HabitRepo(AppDatabase db) {
        this.habitDao = db.habitDao();
    }

    public Single<List<Habit>> getAll() {
        return Single.just(habitDao.getAll()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable insert(Habit habit) {
        return Completable.fromAction(() -> habitDao.insert(habit)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Single<Habit> getByName(String name) {
        return Single.just(habitDao.getByName(name)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Single<List<Habit>> findByDate(String date) {
        return Single.just(habitDao.findByDate(date)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Single<List<Habit>> getAllByAllTaskCompletion() {
        return Single.just(habitDao.getAllByAllTaskCompletion()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Single<List<Habit>> findByPeriod(String startDate, String endDate) {
        return Single.just(habitDao.findByPeriod(startDate, endDate)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable insertAll(List<Habit> habits) {
        return Completable.fromAction(() -> habitDao.insertAll(habits)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable delete(Habit habit) {
        return Completable.fromAction(() -> habitDao.delete(habit)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable deleteById(int id) {
        return Completable.fromAction(() -> habitDao.deleteById(id)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable update(Habit habit) {
        return Completable.fromAction(() -> habitDao.update(habit)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }
}
