package com.example.quanlynhansu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynhansu.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnCreate;
    EditText edtUserName, edtPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreate = findViewById(R.id.btnLoginNow);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtGmail);

        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener((v) -> {
            if (edtUserName.getText().length() == 0) {
                edtUserName.setError("Please enter your email!");
            } else if (edtPassword.getText().length() == 0) {
                edtPassword.setError("Please enter your password!");
            } else {
                signIn();
            }
        });
        btnCreate.setOnClickListener(view -> {
            CreateNow();
        });
    }

    private void CreateNow() {
        final Intent intentCreate = new Intent(this, RegisterActivity.class);
        startActivity(intentCreate);
    }

    private void signIn() {
        String email = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        final Intent intentUser = new Intent(this, HomeActivity.class);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").
                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("role");

                            startActivity(intentUser);

                            Toast.makeText(LoginActivity.this, "Authentication Successful.",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
