package com.hendri.exercise.testproject.rest;

import com.hendri.exercise.testproject.database.DtbTodo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hendri on 11/23/2016.
 */

public interface ApiInterface {

    @GET("todos")
    Call<List<DtbTodo>> getTodoList();
}
