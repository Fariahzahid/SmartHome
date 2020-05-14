package com.example.smart_home.Contact_Person_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.Contact_Person_SignIn.Register;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Add_New_Users extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    EditText name,email,phoneno,password,repassword,address;
    Button register;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    private Spinner gender ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_add_new_user);

        name = findViewById(R.id.user_register_name);
        email = findViewById(R.id.user_register_email);
        phoneno = findViewById(R.id.user_register_phoneno);
        password= findViewById(R.id.user_register_password);
        address = findViewById(R.id.user_register_address);
        repassword=findViewById(R.id.user_register_conpassword);
        register = findViewById(R.id.user_register_button);
        gender = findViewById(R.id.gender_spinner);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"Male");
        categories.add("Female");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(aa);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gender = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + gender,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });


        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Contact_Person_Add_New_Users.class));
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String regemail = email.getText().toString().trim();
                final String regpassword = password.getText().toString().trim();
                final String regphoneno = phoneno.getText().toString();
                final String regname = name.getText().toString();
                final String regaddress = address.getText().toString();
                final String reggender = gender.getSelectedItem().toString();
                final String regconpassword= repassword.getText().toString().trim();
                if(TextUtils.isEmpty(regemail)){
                    email.setError("Email is required");
                    return;

                }
                if(TextUtils.isEmpty(regpassword)){
                    password.setError("password is empty");
                    return;
                }
                if(TextUtils.isEmpty(regname)){
                    password.setError("Name is empty");
                    return;
                }
                if(TextUtils.isEmpty(regphoneno)){
                    password.setError("Phone Number is empty");
                    return;
                }
                if(TextUtils.isEmpty(regaddress)){
                    password.setError("Address is empty");
                    return;
                }
                if(TextUtils.isEmpty(reggender)){
                    password.setError("Select Gender is empty");
                    return;
                }
                if(regpassword.length()< 6){
                    password.setError("Password must be 6 characters long");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(regemail,regpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Contact_Person_Add_New_Users.this, "User Created.",Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("USER").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("UserID",userID);
                            user.put("Name", regname);
                            user.put("Email",regemail);
                            user.put("Password",regpassword);
                            user.put("Phone No", regphoneno);
                            user.put("Address", regaddress);
                            user.put("Gender",reggender);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "OnSuccess: User Profile Created " +userID);
                                    Toast.makeText(Contact_Person_Add_New_Users.this, "User Profile is Created !",Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "OnFailire: User Profile Created " +e.toString());

                                }
                            });

                            Intent intent = new Intent(Contact_Person_Add_New_Users.this,Contact_Person_Users_List.class);
                            intent.putExtra("UserID",userID);
                            startActivity(intent);

                        }else {
                            Toast.makeText(Contact_Person_Add_New_Users.this, "Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }
}
