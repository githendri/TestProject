package com.hendri.exercise.testproject.utils;

import android.content.Context;

import com.hendri.exercise.testproject.application.MainApplication;
import com.hendri.exercise.testproject.database.DaoSession;
import com.hendri.exercise.testproject.database.DtbTodo;
import com.hendri.exercise.testproject.database.DtbTodoDao;

import java.util.List;

/**
 * Created by hendri on 11/23/2016.
 */

public class UtilData {
    //this is used when checklist need to be updated
    public static void insertOrReplaceDtbTodo(Context context, DtbTodo dtbTodo) {
        if (dtbTodo == null) return;
        DaoSession daoSession = (((MainApplication) context).getDaoSession());
        DtbTodoDao dtbTodoDao = daoSession.getDtbTodoDao();
        dtbTodoDao.insertOrReplace(dtbTodo);
    }

    public static void insertOrReplaceDtbTodo(Context context, List<DtbTodo> dtbTodoList) {
        if (dtbTodoList == null) return;
        DaoSession daoSession = (((MainApplication) context).getDaoSession());
        DtbTodoDao dtbTodoDao = daoSession.getDtbTodoDao();
        dtbTodoDao.insertOrReplaceInTx(dtbTodoList);
    }

    public static List<DtbTodo> getAllTodo(Context context) {
        DaoSession daoSession = (((MainApplication) context).getDaoSession());
        DtbTodoDao dtbTodoDao = daoSession.getDtbTodoDao();
        return dtbTodoDao.queryBuilder().list();
    }
}
