package com.example.quanlynhansu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynhansu.MainActivity;
import com.example.quanlynhansu.R;
import com.example.quanlynhansu.adapter.EmployeeAdapter;
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
    EmployeeAdapter employeeAdapter1;
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
        list = new ArrayList<Employee>();
//        employeeAdapter = new EmployeeAdapter(this, list);
        employeeAdapter1 = new EmployeeAdapter(this, list);
        listView.setAdapter(employeeAdapter1);
//        recyclerView.setAdapter(employeeAdapter);
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    try {
                        Employee employee = dataSnapshot.getValue(Employee.class);
                        list.add(employee);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
                employeeAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int idLayout, long l) {
                Intent i=new Intent(ListEmployeeActivity.this,EmployeeDetailActivity.class);
                Bundle b=new Bundle();
                b.putString("name",list.get(idLayout).getName());
                b.putString("description",list.get(idLayout).getDescription());
                b.putInt("age",list.get(idLayout).getAge());
                b.putString("position",list.get(idLayout).getPosition());
                b.putString("image", list.get(idLayout).getImage());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}
