package com.example.smart_home.Contact_Person_SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.Contact_Person_Screen.Contact_Person_Home;
import com.example.smart_home.MainActivity;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.util.Log.d;


public class Register extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    EditText name,email,phoneno,password,repassword;
    Button register;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_register);

        name = findViewById(R.id.contact_person_register_name);
        email = findViewById(R.id.contact_person_register_email);
        phoneno = findViewById(R.id.contact_person_register_phoneno);
        password= findViewById(R.id.contact_person_register_password);
        repassword=findViewById(R.id.contact_person_register_repassword);
        register = findViewById(R.id.contact_person_btn_register);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Contact_Person_Home.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String regemail = email.getText().toString().trim();
                final String regpassword = password.getText().toString().trim();
                final String regphoneno = phoneno.getText().toString();
                final String regname = name.getText().toString();
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
                progressBar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(regemail,regpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created.",Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Contact Person").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Name", regname);
                            user.put("Email",regemail);
                            user.put("Password",regpassword);
                            user.put("Phone No", regphoneno);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "OnSuccess: User Profile Created " +userID);
                                    Toast.makeText(Register.this, "User Profile is Created !",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "OnFailire: User Profile Created " +e.toString());

                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else {
                            Toast.makeText(Register.this, "Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}
