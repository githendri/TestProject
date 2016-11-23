package com.hendri.exercise.testproject.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.hendri.exercise.testproject.database.DaoMaster;
import com.hendri.exercise.testproject.database.DaoSession;

/**
 * Created by hendri on 11/23/2016.
 */

public class MainApplication extends Application {
    public DaoSession daoSession;
    public SQLiteDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "todo-db", null);
        db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

}
