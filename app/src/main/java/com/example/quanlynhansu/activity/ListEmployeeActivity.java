package com.example.quanlynhansu.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlynhansu.R;
import com.example.quanlynhansu.adapter.EmployeeAdapter;
import com.example.quanlynhansu.adapter.EmployeeAdapter1;
import com.example.quanlynhansu.entity.Employee;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListEmployeeActivity extends AppCompatActivity {

//    RecyclerView recyclerView;
    ListView listView;
    EmployeeAdapter1 employeeAdapter1;
    DatabaseReference database;
//    EmployeeAdapter employeeAdapter;
    ArrayList<Employee> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_employee);


        listView = findViewById(R.id.listViewItem);
//        recyclerView = findViewById(R.id.vlistitem);
        database = FirebaseDatabase.getInstance().getReference("Employee");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
        list = new ArrayList<Employee>();
//        employeeAdapter = new EmployeeAdapter(this, list);
        employeeAdapter1 = new EmployeeAdapter1(this, list);
        listView.setAdapter(employeeAdapter1);
//        recyclerView.setAdapter(employeeAdapter);
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Employee employee = dataSnapshot.getValue(Employee.class);
                    list.add(employee);
                }
                employeeAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
