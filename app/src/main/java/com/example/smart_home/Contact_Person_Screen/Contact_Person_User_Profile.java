package com.example.smart_home.Contact_Person_Screen;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.smart_home.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class Contact_Person_User_Profile extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    TextView aname,aemail,aphoneno,aaddress,auserid,agender;
    FirebaseFirestore fStore;
    String value;
    private Spinner user_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_user_profile);

        Intent intent = getIntent();
        value = intent.getStringExtra("UserId");
        System.out.println(value +"USER ID ==");

        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"USER PROFILE");
        categories.add("USER MODES");

        user_settings = findViewById(R.id.cp_User_Profile);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_settings.setAdapter(aa);
        user_settings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("USER PROFILE")){
                    userdata();

                }else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(),"Selected"+item,Toast.LENGTH_SHORT).show();
                }
                if(parent.getItemAtPosition(position).equals("USER MODES")){
                    Intent intent = new Intent(Contact_Person_User_Profile.this, Contact_Person_User_Modes.class);
                    intent.putExtra("UserID",value);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    private void userdata(){
        //auserid = (TextView) findViewById(R.id.userid);
        aname = (TextView) findViewById(R.id.text_cp_user_name_2);
        aemail = (TextView) findViewById(R.id.text_cp_user_email_2);
        aphoneno = (TextView) findViewById(R.id.text_cp_user_phoneno_2);
        aaddress = (TextView) findViewById(R.id.text_cp_user_address_2);
        agender = (TextView) findViewById(R.id.text_cp_useraddress4);

        fStore = FirebaseFirestore.getInstance();
       // System.out.println("User Id" +value +"NAme");
        //Log.d(TAG,value);


        final DocumentReference documentReference = fStore.collection("USER").document(value);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                aname.setText(documentSnapshot.getString("Name"));
                aemail.setText(documentSnapshot.getString("Email"));
                aphoneno.setText(documentSnapshot.getString("PhoneNo"));
                aaddress.setText(documentSnapshot.getString("Address"));
                //auserid.setText(documentSnapshot.getString("UserId"));
                agender.setText(documentSnapshot.getString("Gender"));

                String a =  documentSnapshot.getString("Name");
                String b =  documentSnapshot.getString("Email");
                String c =  documentSnapshot.getString("PhoneNo");
                String d =  documentSnapshot.getString("Address");
                String ef =  documentSnapshot.getString("Gender");

                System.out.println("name    " +a +"     email     " +b +"    phoneno     " +c +"       address     "+d +"      gender    "+ef);
            }
        });
    }
}


