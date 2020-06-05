package com.example.smart_home.Contact_Person_Screen;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Contact_Person_User_Profile extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    Button edit_profile,mode_settings;
    TextView aname,aemail,aphoneno,aaddress,agender;
    ImageView profileimage;

    String userid;
    //FIREBASE STORAGE
    StorageReference storageReference;
    //String value,userid;
    FirebaseAuth fAuth;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_user_profile);

        GlobalVariables globalVariable=(GlobalVariables)getApplication();
        userid  = globalVariable.getUserIDUser();

        storageReference = FirebaseStorage.getInstance().getReference();

        userdata(userid);
        edit_profile = (Button) findViewById(R.id.edit_user_profile);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact_Person_User_Profile.this, Contact_Person_Edit_User_Profile.class);
                startActivity(intent);
            }
        });
        mode_settings = (Button) findViewById(R.id.edit_user_mode_settings);
        mode_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact_Person_User_Profile.this, Contact_Person_User_Modes.class);
                startActivity(intent);
            }
        });

    }
    private void userdata(String id){
        aname = (TextView) findViewById(R.id.text_cp_user_name_2);
        aemail = (TextView) findViewById(R.id.text_cp_user_email_2);
        aphoneno = (TextView) findViewById(R.id.text_cp_user_phoneno_2);
        aaddress = (TextView) findViewById(R.id.text_cp_user_address_2);
        agender = (TextView) findViewById(R.id.text_cp_useraddress4);
        profileimage = (ImageView) findViewById(R.id.cp_user_profile_image);
        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id);
        StorageReference profileRef = storageReference.child("User_Profile/" +id+".jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileimage);
            }
        });
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                aname.setText(documentSnapshot.getString("Name"));
                aemail.setText(documentSnapshot.getString("Email"));
                aphoneno.setText(documentSnapshot.getString("PhoneNo"));
                aaddress.setText(documentSnapshot.getString("Address"));
                agender.setText(documentSnapshot.getString("Gender"));
            }
        });
    }
}


