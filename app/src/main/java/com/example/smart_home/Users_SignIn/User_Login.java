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

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.example.smart_home.Users_Modes.User_Guide_Video;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class User_Login extends AppCompatActivity {

    EditText email, password;
    Button user_login;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        fStore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();


        email = (EditText) findViewById(R.id.user_login_email);
        password = (EditText) findViewById(R.id.user_login_password);
        user_login = (Button) findViewById(R.id.user_btn_login);


        if (mFirebaseAuth.getCurrentUser() != null) {
            fStore = FirebaseFirestore.getInstance();
            userID = mFirebaseAuth.getCurrentUser().getUid();
            GlobalVariables globalVariable=(GlobalVariables)getApplication();
            globalVariable.setUserIDUser(userID);
            startActivity(new Intent(User_Login.this, User_Guide_Video.class));
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
                                userID = mFirebaseAuth.getCurrentUser().getUid();
                                //startActivity(new Intent(getApplicationContext(), User_Home.class));
                                GlobalVariables globalVariable=(GlobalVariables)getApplication();
                                globalVariable.setUserIDUser(userID);
                                Intent intent = new Intent(User_Login.this, User_Guide_Video.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(User_Login.this, "Error."+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            });
        }
    //}
}
