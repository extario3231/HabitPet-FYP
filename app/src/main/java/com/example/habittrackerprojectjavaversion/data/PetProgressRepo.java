package com.example.habittrackerprojectjavaversion.data;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PetProgressRepo {
    private AppDatabase db;
    private PetProgressDao petProgressDao;

    public PetProgressRepo(AppDatabase db) {
        this.db = db;
        this.petProgressDao = db.petProgressDao();
    }

    public Single<PetProgress> getProgress() {
        return Single.just(petProgressDao.getProgress()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insert(PetProgress progress) {
        return Completable.fromAction(() -> petProgressDao.insert(progress)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable update(PetProgress newProgress) {
        return Completable.fromAction(() -> petProgressDao.updateProgress(newProgress)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}