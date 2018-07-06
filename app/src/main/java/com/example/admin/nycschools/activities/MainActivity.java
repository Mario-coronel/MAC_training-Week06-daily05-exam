package com.example.admin.nycschools.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.nycschools.R;
import com.example.admin.nycschools.SchoolAdapter;
import com.example.admin.nycschools.model.School;
import com.example.admin.nycschools.network.NYCSchoolDataService;
import com.example.admin.nycschools.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SchoolAdapter adapter;
    List<School> schoolResponse;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rvSchoolList);

    }

    public void getSchools(View view) {

        NYCSchoolDataService service = RetrofitInstance.getRetrofitInstance().create(NYCSchoolDataService.class);
        Call<List<School>> call = service.getSchools();
        Log.wtf("URL Called", call.request().url() + "");
       call.enqueue(new Callback<List<School>>() {
           @Override
           public void onResponse(Call<List<School>> call, Response<List<School>> response) {
               schoolResponse = response.body();
               generateSchoolList(schoolResponse);
           }

           @Override
           public void onFailure(Call<List<School>> call, Throwable t) {
               Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

           }
       });

    }


    public void generateSchoolList(List<School> schools) {
        adapter = new SchoolAdapter(schools,this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }


}
