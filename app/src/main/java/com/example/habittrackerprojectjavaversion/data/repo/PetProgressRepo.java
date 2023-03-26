package com.example.habittrackerprojectjavaversion.data.repo;

import android.os.Looper;

import com.example.habittrackerprojectjavaversion.data.AppDatabase;
import com.example.habittrackerprojectjavaversion.data.dao.PetProgressDao;
import com.example.habittrackerprojectjavaversion.data.entity.PetProgress;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PetProgressRepo {
    private final PetProgressDao petProgressDao;

    public PetProgressRepo(AppDatabase db) {
        this.petProgressDao = db.petProgressDao();
    }

    public Single<PetProgress> getProgress() {
        return Single.just(petProgressDao.findProgress()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable insert(PetProgress progress) {
        return Completable.fromAction(() -> petProgressDao.insert(progress)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }

    public Completable update(PetProgress newProgress) {
        return Completable.fromAction(() -> petProgressDao.updateProgress(newProgress)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.from(Looper.myLooper(), true));
    }
}
