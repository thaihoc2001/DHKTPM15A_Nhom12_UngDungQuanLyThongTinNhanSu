package com.example.quanlynhansu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quanlynhansu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EmployeeDetailActivity extends AppCompatActivity {
    private Button btnUpdate,btnDelete;
    private TextView tvName,tvPosition,tvAge,tvDesc;
    private ImageView imgAvatar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_detail);
        btnDelete=findViewById(R.id.btnDelete_detail);
        btnUpdate=findViewById(R.id.btnUpdate_detail);
        tvAge=findViewById(R.id.txtAge_detail);
        tvDesc=findViewById(R.id.txtDescription_detail);
        tvName=findViewById(R.id.txtName_detail);
        tvPosition=findViewById(R.id.txtPosition_detail);
        imgAvatar=findViewById(R.id.imgAvatar_detail);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        tvPosition.setText(bundle.getString("position"));
        tvDesc.setText(bundle.getString("description"));
        tvName.setText(bundle.getString("name"));
        tvAge.setText(Integer.toString(bundle.getInt("age")));
        Picasso.get().load(bundle.getString("image")).into(imgAvatar);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmployee();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEmp();
            }
        });
    }

    private void deleteEmp() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Employee");
        try {
            databaseReference.removeValue();
            Toast.makeText(this, "Employee Deleted", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
        }

     //   Intent i=new Intent(this,ListEmployeeActivity.class);
    //    startActivity(i);
    }

    private void updateEmployee() {
        Intent i=new Intent(this,AddEmployeeActivity.class);
        startActivity(i);
    }
}
