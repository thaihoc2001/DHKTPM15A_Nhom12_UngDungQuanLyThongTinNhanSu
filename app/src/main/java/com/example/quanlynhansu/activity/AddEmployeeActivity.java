package com.example.quanlynhansu.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynhansu.R;
import com.example.quanlynhansu.entity.Employee;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Constants;

public class AddEmployeeActivity extends AppCompatActivity {
    EditText edtName, edtOld, edtPosition, edtDescription;
    Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);
        findId();
        btnSave.setOnClickListener((v) -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Employee");

            String name = edtName.getText().toString();
            String position = edtPosition.getText().toString();
            int age = 0;
            try {
                age = Integer.parseInt(edtOld.getText().toString());
            }catch (NumberFormatException e){
                System.out.println(e);
            }
            String description = edtDescription.getText().toString();

            String key = database.getReference("Employee").push().getKey();
//            String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
            System.out.println("key" + key);
//            System.out.println(database.getReference("Employee").child(Constants.US));
            if (name != "" && position != "" && age != 0 && description != "") {
                Employee employee = new Employee(name, position, age, description);
                myRef.child(key).setValue(employee, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(AddEmployeeActivity.this, "Add employee complate", Toast.LENGTH_SHORT).show();
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
    }
}
