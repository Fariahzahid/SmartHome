package com.example.smart_home.Contact_Person_Screen;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Edit_Profile extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    TextView aname,aemail,aphoneno,aaddress,agender,editname,editemail,editphoneno,editaddress;
    Spinner editgender;
    ImageView profileimage;
    Button save_button;
    //String value,userid;
    FirebaseAuth fAuth;

    //FIREBASE STORAGE
    StorageReference storageReference;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;

    //FIREBASE USER
    FirebaseUser user;

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_edit_profile);
        GlobalVariables globalVariable=(GlobalVariables)getApplication();
       userid  = globalVariable.getUserIDContactPerson();

        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        //FIREBASE FIRSTORE
        fStore = FirebaseFirestore.getInstance();
        userdata(userid);

        editname = (EditText) findViewById(R.id.text_contactperson_editname);
        editemail =(EditText) findViewById(R.id.text_contactperson_editemail);
        editaddress = (EditText) findViewById(R.id.text_contactperson_editaddress);
        editphoneno = (EditText) findViewById(R.id.text_contactperson_editphoneno);
        editgender = (Spinner) findViewById(R.id.text_contactperson_editgender);
        GenderSpinner();
        save_button = (Button) findViewById(R.id.contact_person_saveeditprofile);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateuserprofile(userid);
            }
        });

    }
    private void userdata(String UserID){
        aname = (TextView) findViewById(R.id.text_contactperson_name);
        aemail = (TextView) findViewById(R.id.text_contactperson_email);
        aphoneno = (TextView) findViewById(R.id.text_contactperson_phoneno);
        aaddress = (TextView) findViewById(R.id.text_contactperson_address);
        agender = (TextView) findViewById(R.id.text_contactperson_gender);
        profileimage = (ImageView) findViewById(R.id.contactperson_profile_image);

        StorageReference profileRef = storageReference.child("profile/" +fAuth.getCurrentUser().getUid()+".jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileimage);
            }
        });
        final DocumentReference documentReference = fStore.collection("Contact Person").document(UserID);

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
    private void updateuserprofile(final String UserID){
        final String email = editemail.getText().toString();
        user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Contact_Person_Edit_Profile.this,"Email Updated",Toast.LENGTH_SHORT).show();
                DocumentReference documentReference = fStore.collection("Contant Person").document(UserID);
                Map<String,Object> edited = new HashMap<>();
                edited.put("Email",email);
                if (editname != null) {
                    edited.put("Name",editname.getText().toString());
                }
                if(editphoneno!=null){
                    edited.put("PhoneNo",editphoneno.getText().toString());
                }
                if(editaddress!=null){
                    edited.put("PhoneNo",editaddress.getText().toString());
                }
                if(editgender!=null){
                    edited.put("Gender",editgender.getSelectedItem().toString());
                }
                documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Contact_Person_Edit_Profile.this,"Profile Updated",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Contact_Person_Edit_Profile.this,"Email not updates,",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void GenderSpinner(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"Male");
        categories.add("Female");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editgender.setAdapter(aa);
        editgender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gender = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + gender,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }
}
