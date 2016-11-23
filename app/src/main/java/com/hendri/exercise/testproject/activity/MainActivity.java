package com.hendri.exercise.testproject.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hendri.exercise.testproject.R;
import com.hendri.exercise.testproject.database.DtbTodo;
import com.hendri.exercise.testproject.rest.ApiConstant;
import com.hendri.exercise.testproject.rest.ApiInterface;
import com.hendri.exercise.testproject.utils.UtilData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public Retrofit retrofit;
    public ApiInterface apiInterface;
    public OkHttpClient httpClient;
    public ProgressDialog alertDialog;
    private Button btnTodoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initServiceWithDialog();
        btnTodoList = (Button) findViewById(R.id.btnTodoList);
        btnTodoList.setOnClickListener(this);
        requestGetTodoList();
    }

    private void requestGetTodoList() {
        alertDialog.show();
        Call<List<DtbTodo>> requestTodoList = apiInterface.getTodoList();
        requestTodoList.enqueue(new Callback<List<DtbTodo>>() {
            @Override
            public void onResponse(Call<List<DtbTodo>> call, Response<List<DtbTodo>> response) {
                alertDialog.dismiss();
                UtilData.insertOrReplaceDtbTodo(getApplicationContext(), response.body());
            }

            @Override
            public void onFailure(Call<List<DtbTodo>> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, "failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initServiceWithDialog() {
        initService();
        initDialog();
    }

    public void initService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public void initDialog() {
        alertDialog = new ProgressDialog(this);
        alertDialog.setMessage(getString(R.string.label_loading));
        alertDialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, TodoListActivity.class);
        startActivity(i);
    }
}
