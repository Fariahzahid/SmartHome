package com.example.smart_home.Contact_Person_Screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.smart_home.Contact_Person_SignIn.Login;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Contact_Person_Profile extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    TextView aname,aemail,aphoneno,aaddress,auserid,agender;
    ImageView profileimage;
    Button editprofile_button,log_out_button;
    //String value,userid;
    FirebaseAuth fAuth;

    //FIREBASE STORAGE
    StorageReference storageReference;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_profile);

        editprofile_button = (Button) findViewById(R.id.contact_person_editprofile);
        editprofile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contact_Person_Profile.this,Contact_Person_Edit_Profile.class);
                startActivity(intent);
            }
        });
        log_out_button = (Button) findViewById(R.id.contact_person_logout);
        log_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
        //FIREBASE STORAGE
        storageReference = FirebaseStorage.getInstance().getReference();
        //FIREBASE FIRSTORE
        fStore = FirebaseFirestore.getInstance();

        userdata();


    }
    private void userdata(){
        GlobalVariables globalVariable=(GlobalVariables)getApplication();
        aname = (TextView) findViewById(R.id.text_contactperson_name_2);
        aemail = (TextView) findViewById(R.id.text_contactperson_email_2);
        aphoneno = (TextView) findViewById(R.id.text_contactperson_phoneno_2);
        aaddress = (TextView) findViewById(R.id.text_contactperson_address_2);
        agender = (TextView) findViewById(R.id.text_contactperson_gender2);
        profileimage = (ImageView) findViewById(R.id.contactperson_profile_image);

        final String userid  = globalVariable.getUserIDContactPerson();

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        final DocumentReference documentReference = fStore.collection("Contact Person").document(userid);
        StorageReference profileRef = storageReference.child("profile/" +fAuth.getCurrentUser().getUid()+".jpg");
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
