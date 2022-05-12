package com.example.quanlynhansu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynhansu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    Button btnAdd, btnList;
    ImageView btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        btnList = findViewById(R.id.btnList);
        btnLogout = findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();

        btnAdd.setOnClickListener((v) -> {
            showFormAddEmployee();
        });
        btnList.setOnClickListener((v) -> {
            showListEmployee();
        });
        btnLogout.setOnClickListener((v) -> {
            mAuth.signOut();
            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            if(user == null){
                logout();
            };
        });
    }

    private void showFormAddEmployee() {
        final Intent intentAddEmployee = new Intent(this, AddEmployeeActivity.class);
        startActivity(intentAddEmployee);
    }

    private void showListEmployee() {
        final Intent intentListEmployee = new Intent(this, ListEmployeeActivity.class);
        startActivity(intentListEmployee);
    }

    private void logout() {
        final Intent intentLogout = new Intent(this, LoginActivity.class);
        startActivity(intentLogout);
    }
}
