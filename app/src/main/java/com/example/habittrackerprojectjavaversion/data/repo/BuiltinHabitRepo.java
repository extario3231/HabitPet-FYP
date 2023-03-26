package com.example.habittrackerprojectjavaversion.data.repo;

import android.os.Looper;

import com.example.habittrackerprojectjavaversion.data.AppDatabase;
import com.example.habittrackerprojectjavaversion.data.dao.BuiltinHabitDao;
import com.example.habittrackerprojectjavaversion.data.entity.BuiltinHabit;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BuiltinHabitRepo {
    private final BuiltinHabitDao builtinHabitDao;

    public BuiltinHabitRepo(AppDatabase db) {
        this.builtinHabitDao = db.builtinHabitDao();
    }

    public Single<List<BuiltinHabit>> getAll() {
        return Single.just(builtinHabitDao.findAll()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Single<BuiltinHabit> getByName(String name) {
        return Single.just(builtinHabitDao.findByName(name)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable insert(BuiltinHabit habit) {
        return Completable.fromAction(() -> builtinHabitDao.insert(habit)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable insertAll(List<BuiltinHabit> habits) {
        return Completable.fromAction(() -> builtinHabitDao.insertAll(habits)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }
}
