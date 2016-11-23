package com.hendri.exercise.testproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.hendri.exercise.testproject.R;
import com.hendri.exercise.testproject.adapter.TodoListAdapter;
import com.hendri.exercise.testproject.utils.UtilData;

/**
 * Created by hendri on 11/23/2016.
 */

public class TodoListActivity extends AppCompatActivity {
    private RecyclerView rvTodoList;
    private TodoListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rvTodoList = (RecyclerView) findViewById(R.id.rvTodoList);
        adapter = new TodoListAdapter(TodoListActivity.this, UtilData.getAllTodo(getApplicationContext()));
        rvTodoList.setAdapter(adapter);
    }
}
