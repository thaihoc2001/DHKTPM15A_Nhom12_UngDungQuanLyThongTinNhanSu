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
import com.example.quanlynhansu.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private Button btnLogin,btnAlready;
    private EditText edtUserName,edtGmail,edtPassWord,edtConfirm;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnAlready=findViewById(R.id.btnLoginNow);
        btnLogin=findViewById(R.id.btnLogin);
        edtUserName=findViewById(R.id.edtUserName);
        edtPassWord=findViewById(R.id.edtPassWord);
        edtConfirm=findViewById(R.id.edtConfirm);
        edtGmail=findViewById(R.id.edtGmail);
        firebaseAuth=FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(view -> {
            if(edtUserName.getText().length()==0){
                edtUserName.setError("Please enter your name!");
            }else if(edtGmail.getText().length()==0){
                edtGmail.setError("Please enter your email!");
            }else if(edtPassWord.getText().length()==0){
                edtPassWord.setError("Please enter your password!");
            }else if(edtConfirm.getText().toString().equals(edtPassWord.getText().toString())==false ){
                edtConfirm.setError("Password confirm is not match to password!");
                System.out.println(edtConfirm);
            }else{
                Register();

            }
        });
        btnAlready.setOnClickListener(view -> {
            AlreadyHaveAccount();
        });
    }

    private void Register() {
        final String username=edtUserName.getText().toString();
        final String gmail=edtGmail.getText().toString();
        final String password=edtPassWord.getText().toString();
        final String confirm=edtConfirm.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(gmail,confirm).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            System.out.println("Check loi!!!!!!!!!1");
                            User user= new User(username,gmail,confirm);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                    setValue(user).addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Register Successfully!", Toast.LENGTH_SHORT).show();
                                    edtUserName.setText("");
                                    edtConfirm.setText("");
                                    edtPassWord.setText("");
                                    edtGmail.setText("");
                                    edtUserName.requestFocus();

                                }else{
                                    Toast.makeText(RegisterActivity.this, "Failure! please check internet connection!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this, "Unsuccessful!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                }



    private void AlreadyHaveAccount() {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}