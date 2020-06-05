package com.example.smart_home.Contact_Person_Screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.smart_home.Contact_Person_SignIn.Register;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.Continuation;
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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Edit_Profile extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    TextView email;
    EditText editname,editgender,editphoneno,editaddress,editpassword;
    Spinner gender;
    ImageView profileimage;
    Button save_button;
    String uripic;
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
        setContentView(R.layout.activity_contact_person_edit_profile);
        GlobalVariables globalVariable=(GlobalVariables)getApplication();
       userid  = globalVariable.getUserIDContactPerson();

      // user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        //FIREBASE FIRSTORE
        fStore = FirebaseFirestore.getInstance();
        userdata(userid);

        email = (TextView) findViewById(R.id.text_contactperson_email);
        editname = (EditText) findViewById(R.id.text_contactperson_editname);
        editaddress = (EditText) findViewById(R.id.text_contactperson_editaddress);
        editphoneno = (EditText) findViewById(R.id.text_contactperson_editphoneno);
        editgender = (EditText) findViewById(R.id.text_contactperson_gender);
        gender = (Spinner) findViewById(R.id.contactperson_editgender);

        profileimage = (ImageView) findViewById(R.id.contactperson_profile_image);
        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LETS THE USER SEE THE PERMISION
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //LETS THE USER CHOSE TO EXEXPT IT OR DENIE IT
                    if (ContextCompat.checkSelfPermission(Contact_Person_Edit_Profile.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(Contact_Person_Edit_Profile.this, "Permision denied", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(Contact_Person_Edit_Profile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    } else {
                        CHOOSEFOTO();
                    }
                } else {
                    CHOOSEFOTO();
                }

            }
        });
        GenderSpinner();
        save_button = (Button) findViewById(R.id.contact_person_saveeditprofile);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateuserprofile(userid);
                Intent intent = new Intent(Contact_Person_Edit_Profile.this,Contact_Person_Profile.class);
                startActivity(intent);
            }
        });
        
        

    }

    private void userdata(String UserID){

        final DocumentReference documentReference = fStore.collection("Contact Person").document(UserID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                editname.setText(documentSnapshot.getString("Name"));
                email.setText(documentSnapshot.getString("Email"));
                editphoneno.setText(documentSnapshot.getString("PhoneNo"));
                editaddress.setText(documentSnapshot.getString("Address"));
                editgender.setText(documentSnapshot.getString("Gender"));
            }
        });

    }
    private void updateuserprofile(final String UserID){
            user = fAuth.getCurrentUser();
                    DocumentReference documentReference = fStore.collection("Contact Person").document(UserID);
                    Map<String,Object> edited = new HashMap<>();
                        edited.put("Name",editname.getText().toString());
                        edited.put("PhoneNo",editphoneno.getText().toString());
                        edited.put("Address",editaddress.getText().toString());
                        edited.put("Gender",gender.getSelectedItem().toString());
                    documentReference.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Contact_Person_Edit_Profile.this,"Profile Updated",Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Contact_Person_Edit_Profile.this,"Profile not Updated",Toast.LENGTH_SHORT).show();

                        }
                    });
                    if(profileimage!=null) {
                        SAVE_NAME_AND_PHOTO(UserID);
                    }
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
        final StorageReference ImagesPath = storageReference.child("profile").child(userID + ".jpg");
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

                    Glide.with(Contact_Person_Edit_Profile.this).load(downUri).into(profileimage);
                    fStore.collection("Contact Person").document(user_id).collection("ProfileImage").document().set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });
                }else {
                    String ERROR = task.getException().getMessage();
                    Toast.makeText(Contact_Person_Edit_Profile.this, ERROR, Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
