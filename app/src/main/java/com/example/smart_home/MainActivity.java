package com.example.smart_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.smart_home.Contact_Person_Screen.Contact_Person_Users_List;
import com.example.smart_home.Contact_Person_SignIn.Login;
import com.example.smart_home.Users_Modes.User_Guide_Video;
import com.example.smart_home.Users_SignIn.User_Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    private Button contact_person,user;
    FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if (mFirebaseAuth.getCurrentUser() != null) {
            userID = mFirebaseAuth.getCurrentUser().getUid();

            DocumentReference documentReference = fStore.collection("Contact Person").document(userID);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "Document exists!");
                            Intent intent = new Intent(MainActivity.this, Contact_Person_Users_List.class);
                            GlobalVariables globalVariable=(GlobalVariables)getApplication();  //Call the global variable class
                            globalVariable.setUserIDContactPerson(userID);
                            startActivity(intent);
                            //finish();
                        } else {
                            Log.d(TAG, "Document does not exist!");
                        }
                    } else {
                        Log.d(TAG, "Failed with: ", task.getException());
                    }
                }
            });
            final DocumentReference documentReference1 = fStore.collection("USER").document(userID);
            documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "Document exists!");
                            Intent intent = new Intent(MainActivity.this, User_Guide_Video.class);
                            GlobalVariables globalVariable=(GlobalVariables)getApplication();  //Call the global variable class
                            globalVariable.setUserIDUser(userID);
                            startActivity(intent);
                        } else {
                            Log.d(TAG, "Document does not exist!");
                        }
                    } else {
                        Log.d(TAG, "Failed with: ", task.getException());
                    }
                }
            });

        }
        contact_person = (Button) findViewById(R.id.mainpage_contactperson);
        user = (Button) findViewById(R.id.mainpage_user);
        contact_person.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                    }
                });
                user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, User_Login.class);
                        startActivity(intent);
                    }
                });
            }
}
