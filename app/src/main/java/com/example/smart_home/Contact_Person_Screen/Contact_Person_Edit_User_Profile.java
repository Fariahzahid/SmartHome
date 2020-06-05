package com.example.smart_home.Contact_Person_Screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Edit_User_Profile extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    TextView editemail;
    EditText editgender,editdisability,editname,editphoneno,editaddress;
    ImageView profileimage;
    Button save_button;
    FloatingActionButton disone,distwo;
    private Spinner gender ,disabilityspinnerone,disbilityspinnertwo,disbilityspinnerthree;
    LinearLayout dislayoutone,dislayouttwo,dislayoutthree;
    //String value,userid;
    FirebaseAuth fAuth;

    //FIREBASE STORAGE
    StorageReference storageReference;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;

    //FIREBASE USER
    FirebaseUser user;

    String userid;

    private Uri mainImageURI;
    //REQUEST CODE
    public static final int REQUEST = 1;
    public static final int REQUESTTWO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_edit_user_profile);

        GlobalVariables globalVariable=(GlobalVariables)getApplication();
        userid  = globalVariable.getUserIDContactPerson();

        // user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        //FIREBASE FIRSTORE
        fStore = FirebaseFirestore.getInstance();
        userdata(userid);

        editname = (EditText) findViewById(R.id.user_edit_name);
        editemail = (TextView) findViewById(R.id.user_edit_email);
        editaddress = (EditText) findViewById(R.id.user_edit_address);
        editphoneno = (EditText) findViewById(R.id.user_edit_phoneno);
        editgender = (EditText) findViewById(R.id.user_edit_gender);
        editdisability = (EditText) findViewById(R.id.user_edit_disability);
        gender = (Spinner) findViewById(R.id.edit_gender_spinner);
        disabilityspinnerone = findViewById(R.id.disability_spinner_one);
        disbilityspinnertwo = findViewById(R.id.disability_spinner_two);
        disbilityspinnerthree = findViewById(R.id.disability_spinnerthree);
        disone = findViewById(R.id.button_add_userone);
        distwo = findViewById(R.id.button_add_usertwo);
        dislayouttwo = (LinearLayout) findViewById(R.id.dislayouttwo);
        dislayoutthree = (LinearLayout) findViewById(R.id.dislayoutthree);
        profileimage = (ImageView) findViewById(R.id.user_profile_image);

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LETS THE USER SEE THE PERMISION
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //LETS THE USER CHOSE TO EXEXPT IT OR DENIE IT
                    if (ContextCompat.checkSelfPermission(Contact_Person_Edit_User_Profile.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(Contact_Person_Edit_User_Profile.this, "Permision denied", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(Contact_Person_Edit_User_Profile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    } else {
                        CHOOSEFOTO();
                    }
                } else {
                    CHOOSEFOTO();
                }
            }
        });
        GenderSpinner();
        DisabilitySpinnerOne();
        DisabilitySpinnerTwo();
        DisabilitySpinnerThree();

        disone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dislayouttwo.setVisibility(View.VISIBLE);
                disone.setVisibility(View.GONE);
            }
        });
        distwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dislayoutthree.setVisibility(View.VISIBLE);
                distwo.setVisibility(View.GONE);
            }
        });
//        save_button = (Button) findViewById(R.id.contact_person_saveeditprofile);
//
//        save_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateuserprofile(userid);
//                Intent intent = new Intent(Contact_Person_Edit_User_Profile.this,Contact_Person_User_Profile.class);
//                startActivity(intent);
//            }
//        });


    }

    private void userdata(String UserID){

        StorageReference profileRef = storageReference.child("User_Profile/" +fAuth.getCurrentUser().getUid()+".jpg");
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
                editname.setText(documentSnapshot.getString("Name"));
                editemail.setText(documentSnapshot.getString("Email"));
                editphoneno.setText(documentSnapshot.getString("PhoneNo"));
                editaddress.setText(documentSnapshot.getString("Address"));
                editgender.setText(documentSnapshot.getString("Gender"));
                String one = documentSnapshot.getString("DisabilityOne");
                String two = documentSnapshot.getString("DisabilityTwo");
                String three = documentSnapshot.getString("DisabilityThree");

                if(one!=null && two!=null &&three!=null){
                    editdisability.setText("Disability One : " +one +"Disability Two : "+two +"Disability Three : " +three);

                }else if(one!= null && two!=null){
                    editdisability.setText("Disability One : " +one +"Disability Two : "+two);

                }else{
                    editdisability.setText("Disability One : " +one);
                }


            }
        });
    }
    private void updateuserprofile(final String UserID){
         user = fAuth.getCurrentUser();
            final String disablityone = disabilityspinnerone.getSelectedItem().toString();
            final String disabilitytwo = disbilityspinnertwo.getSelectedItem().toString();
            final String disabilitythree = disbilityspinnerthree.getSelectedItem().toString();

                    DocumentReference documentReference = fStore.collection("USER").document(UserID);
                    Map<String,Object> edited = new HashMap<>();
                    edited.put("Name",editname.getText().toString());
                    edited.put("Phone No",editphoneno.getText().toString());
                    edited.put("Address",editaddress.getText().toString());
                    edited.put("Gender",gender.getSelectedItem().toString());
                    if(disablityone!=null){
                        edited.put("DisabilityOne",disablityone);
                    }
                    if(disabilitytwo!=null){
                        edited.put("DisabilityTwo",disabilitytwo);
                    }
                    if(disabilitythree!=null){
                        edited.put("DisabilityThree",disabilitythree);
                    }
                    //SAVE_NAME_AND_PHOTO(UserID);
                    documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Contact_Person_Edit_User_Profile.this,"Profile Updated",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Contact_Person_Edit_User_Profile.this,"Profile not Updated",Toast.LENGTH_SHORT).show();

                        }
                    });
    }

    private void DisabilitySpinnerOne(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"NUll");
        categories.add("Mute");
        categories.add("HearingImpairment");
        categories.add("PhysicalImpairment");
        categories.add("ColorBlindness");
        categories.add("VisionImpairment");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disabilityspinnerone.setAdapter(aa);
        disabilityspinnerone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void DisabilitySpinnerTwo(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"NUll");
        categories.add("Mute");
        categories.add("HearingImpairment");
        categories.add("PhysicalImpairment");
        categories.add("ColorBlindness");
        categories.add("VisionImpairment");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disbilityspinnertwo.setAdapter(aa);
        disbilityspinnertwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void DisabilitySpinnerThree(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"NUll");
        categories.add("Mute");
        categories.add("HearingImpairment");
        categories.add("PhysicalImpairment");
        categories.add("ColorBlindness");
        categories.add("VisionImpairment");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disbilityspinnerthree.setAdapter(aa);
        disbilityspinnerthree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void GenderSpinner(){
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
    }
    //LETS THE USER GO TO THE GALLARY TO CHOOSE A PHOTO
    private void CHOOSEFOTO() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST);

    }
    //GETS THE URI FROM THE FOTO AND SETS THE FOTO INTO THE IMAGEVIEW
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST) {
            if (resultCode == RESULT_OK) {
                mainImageURI = data.getData();
                profileimage.setImageURI(mainImageURI);
            } else if (resultCode == REQUESTTWO) {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void SAVE_NAME_AND_PHOTO(String userID) {

        final String user_id = userID;
        final StorageReference ImagesPath = storageReference.child("User_Profile").child(userID + ".jpg");
        ImagesPath.putFile(mainImageURI).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return ImagesPath.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downUri = task.getResult();
                    downUri.toString();
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("image", downUri.toString());

                    Glide.with(Contact_Person_Edit_User_Profile.this).load(downUri).into(profileimage);
                    fStore.collection("USER").document(user_id).collection("ProfileImage").document().set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });
                }else {
                    String ERROR = task.getException().getMessage();
                    Toast.makeText(Contact_Person_Edit_User_Profile.this, ERROR, Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
