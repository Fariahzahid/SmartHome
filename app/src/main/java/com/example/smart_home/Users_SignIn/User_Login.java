package com.example.smart_home.Users_SignIn;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.Contact_Person_Screen.Contact_Person_Users_List;
import com.example.smart_home.Contact_Person_SignIn.Register;
import com.example.smart_home.R;
import com.example.smart_home.Users_Modes.User_Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class User_Login extends AppCompatActivity {

    EditText email, password;
    Button user_login;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        email = (EditText) findViewById(R.id.user_login_email);
        password = (EditText) findViewById(R.id.user_login_password);
        user_login = (Button) findViewById(R.id.user_btn_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        if (mFirebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(User_Login.this, User_Home.class));
            finish();
        }
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regemail = email.getText().toString().trim();
                String regpassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(regemail)){
                    email.setError("Email is required");
                    return;

                }

                if(TextUtils.isEmpty(regpassword)){
                    password.setError("password is empty");
                    return;
                }
                if(regpassword.length()< 6){
                    password.setError("Password must be 6 characters long");
                    return;
                }

                mFirebaseAuth.signInWithEmailAndPassword(regemail,regpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(User_Login.this, "User Logged In.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Contact_Person_Users_List.class));
                        }
                        else{
                            Toast.makeText(User_Login.this, "Error."+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),SignIn.class));

                        }
                    }
                });

            }
        });

    }
}
