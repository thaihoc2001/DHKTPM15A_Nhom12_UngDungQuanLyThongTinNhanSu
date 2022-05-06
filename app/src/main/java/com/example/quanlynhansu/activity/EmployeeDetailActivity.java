package com.example.quanlynhansu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quanlynhansu.R;
import com.google.firebase.auth.FirebaseAuth;
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
    }
}
