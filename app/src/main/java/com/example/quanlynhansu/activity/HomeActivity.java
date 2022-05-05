package com.example.quanlynhansu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynhansu.R;

public class HomeActivity extends AppCompatActivity {
    Button btnAdd, btnList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnList = findViewById(R.id.btnList);

        btnAdd.setOnClickListener((v) -> {
            showFormAddEmployee();
        });
        btnList.setOnClickListener((v) -> {
            showListEmployee();
        });
    }
    private void showFormAddEmployee() {
        final Intent intentAddEmployee = new Intent(this,AddEmployeeActivity.class);
        startActivity(intentAddEmployee);
    }
    private void showListEmployee() {
        final Intent intentListEmployee = new Intent(this,ListEmployeeActivity.class);
        startActivity(intentListEmployee);
    }
}
