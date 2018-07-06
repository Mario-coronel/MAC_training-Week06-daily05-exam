package com.example.admin.nycschools.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.nycschools.R;

public class SchoolDetails extends AppCompatActivity {

    TextView name, address, email, math, reading, writing;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);
        bindViews();
        bundle = getIntent().getExtras();
        name.setText("School: " + bundle.getString("name"));
        address.setText("Adress: " + bundle.getString("address"));
        email.setText("Email: " + bundle.getString("email"));
        math.setText("Math SAT AVG: " + bundle.getString("math"));
        reading.setText("Reading SAT AVG: " + bundle.getString("read"));
        writing.setText("Write SAT AVG: "+bundle.getString("write"));


    }

    private void bindViews() {
        name = (TextView) findViewById(R.id.tvdName);
        address = (TextView) findViewById(R.id.tvdAddress);
        email = (TextView) findViewById(R.id.tvdEmail);
        math = (TextView) findViewById(R.id.tvdMath);
        reading = (TextView) findViewById(R.id.tvdReading);
        writing = (TextView) findViewById(R.id.tvdWriting);
    }
}
