package com.example.quanlynhansu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.database.core.Constants;

public class AddEmployeeActivity extends AppCompatActivity {
    EditText edtName, edtOld, edtPosition, edtDescription;
    Button btnSave;
    ImageView btnBack;
    int idPosition, i = 0;
    String idEmployeeFB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);
        findId();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle != null){
            edtPosition.setText(bundle.getString("position"));
            edtDescription.setText(bundle.getString("description"));
            edtName.setText(bundle.getString("name"));
            edtOld.setText(Integer.toString(bundle.getInt("age")));
            idPosition = bundle.getInt("idPosition");
        }
        btnBack.setOnClickListener((v) -> {
            if(bundle != null){
                showListEmployee();
            }else{
                showHome();
            }

        });
        btnSave.setOnClickListener((v) -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Employee");
            final String key = database.getReference("Employee").push().getKey();
            final String name = edtName.getText().toString();
            final String position = edtPosition.getText().toString();
            final int age = Integer.parseInt(edtOld.getText().toString());
            String description = edtDescription.getText().toString();
            if (name != "" && position != "" && age != 0 && description != "" && bundle == null) {
                Employee employee = new Employee(name, position, age, description);
                myRef.child(key).setValue(employee, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(AddEmployeeActivity.this, "Add employee complate", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            try {
                                System.out.println("idPositon2" + idPosition);
                                Employee employee = dataSnapshot.getValue(Employee.class);
                                System.out.println("i ne" + i);
                                if(i == idPosition){
                                    Employee employeeUpdate = new Employee(name, position, age, description);
                                    idEmployeeFB = dataSnapshot.getKey();
                                    myRef.child(idEmployeeFB).setValue(employeeUpdate).addOnCompleteListener(task -> {
                                        if(task.isSuccessful()){
                                            Toast.makeText(AddEmployeeActivity.this, "Update Successfully!", Toast.LENGTH_SHORT).show();
                                            showListEmployee();
                                        }else{
                                            Toast.makeText(AddEmployeeActivity.this, "Update fail!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    return;
                                }
                                i = i + 1;
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


    private void findId() {
        edtName = findViewById(R.id.txtName_add_e);
        edtOld = findViewById(R.id.txtOld_add_e);
        edtPosition = findViewById(R.id.txtPosition_add_e);
        edtDescription = findViewById(R.id.txtDescription_add_e);
        btnSave = findViewById(R.id.btnSave_add_e);
        btnBack = findViewById(R.id.btn_back_add);
    }
    private void showListEmployee() {
        final Intent intentListEmployee = new Intent(this,ListEmployeeActivity.class);
        startActivity(intentListEmployee);
    }
    private void showHome() {
        final Intent intentHome = new Intent(this, HomeActivity.class);
        startActivity(intentHome);
    }
}
