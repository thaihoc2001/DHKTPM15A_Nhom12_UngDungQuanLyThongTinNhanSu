package com.example.quanlynhansu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynhansu.R;
import com.example.quanlynhansu.entity.Employee;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EmployeeDetailActivity extends AppCompatActivity {
    private Button btnUpdate, btnDelete;
    private TextView tvName, tvPosition, tvAge, tvDesc;
    private ImageView imgAvatar, btnBack;
    DatabaseReference database;
    int idPosition;
    String idEmployeeFB;
    int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_detail);
        btnDelete = findViewById(R.id.btnDelete_detail);
        btnUpdate = findViewById(R.id.btnUpdate_detail);
        tvAge = findViewById(R.id.txtAge_detail);
        tvDesc = findViewById(R.id.txtDescription_detail);
        tvName = findViewById(R.id.txtName_detail);
        tvPosition = findViewById(R.id.txtPosition_detail);
        imgAvatar = findViewById(R.id.imgAvatar_detail);
        btnBack = findViewById(R.id.btn_back_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tvPosition.setText(bundle.getString("position"));
        tvDesc.setText(bundle.getString("description"));
        tvName.setText(bundle.getString("name"));
        tvAge.setText(Integer.toString(bundle.getInt("age")));
        Picasso.get().load(bundle.getString("image")).into(imgAvatar);

        idPosition = Integer.parseInt(bundle.getString("idPosition"));
        System.out.println("idPositon" + idPosition);

        database = FirebaseDatabase.getInstance().getReference("Employee");
        btnUpdate.setOnClickListener(v -> {
            Intent i = new Intent(EmployeeDetailActivity.this, AddEmployeeActivity.class);
            Bundle b = new Bundle();
            b.putString("name", bundle.getString("name"));
            b.putString("description", bundle.getString("description"));
            b.putInt("age", bundle.getInt("age"));
            b.putString("position", bundle.getString("position"));
            b.putString("image", bundle.getString("image"));
            b.putString("idPosition", idPosition + "");
            i.putExtras(b);
            startActivity(i);
        });

        btnBack.setOnClickListener((v) -> {
            showListEmployee();
        });

        btnDelete.setOnClickListener(v -> {
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        try {
                            System.out.println("idPositon2" + idPosition);
                            Employee employee = dataSnapshot.getValue(Employee.class);
                            System.out.println("i ne" + i);
                            if (i == idPosition) {
                                idEmployeeFB = dataSnapshot.getKey();
                                database.child(idEmployeeFB).removeValue().addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(EmployeeDetailActivity.this, "Delete Successfully!", Toast.LENGTH_SHORT).show();
                                        showListEmployee();
                                    } else {
                                        Toast.makeText(EmployeeDetailActivity.this, "Delete fail!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return;
                            }
                            i = i + 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }

    private void showListEmployee() {
        final Intent intentListEmployee = new Intent(this, ListEmployeeActivity.class);
        startActivity(intentListEmployee);
    }
}
