package com.example.smart_home.Contact_Person_SignIn;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.smart_home.Contact_Person_Screen.Contact_Person_Users_List;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {
    //Context Activity
    Context c;
    private static final String TAG = "MyActivity";

    //Layout
    EditText name,email,phoneno,password,repassword,address;
    ImageView profilepicture;
    Button register;
    private Spinner gender ;
    ProgressDialog mDialog;
    //Strings
    String userID;
    //USER ID
    private String user_id;
    //URI
    private Uri mainImageURI;
    //REQUEST CODE
    public static final int REQUEST = 1;
    public static final int FUCK_UP = 2;
    //FIREBASE STORAGE
    private StorageReference storageReference;
    //FIREBASE AUTHENTICATION
    FirebaseAuth fAuth;
    //FIREBASE FIRESTORE
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_register);

        profilepicture = findViewById(R.id.contact_person_profileimage);
        name = findViewById(R.id.contact_person_register_name);
        email = findViewById(R.id.contact_person_register_email);
        phoneno = findViewById(R.id.contact_person_register_phoneno);
        password = findViewById(R.id.contact_person_register_password);
        address = findViewById(R.id.contact_person_register_address);
        repassword = findViewById(R.id.contact_person_register_conpassword);
        register = findViewById(R.id.contact_person_register_button);
        gender = findViewById(R.id.gender_spinner);

        GenderSpinner(); //Select Gender
        //FIREBASE AUTH
        fAuth = FirebaseAuth.getInstance();

        //FIREBASE STORAGE
        storageReference = FirebaseStorage.getInstance().getReference();
        //FIREBASE FIRSTORE
        fStore = FirebaseFirestore.getInstance();

        //SETONCLICKLISTENRERS
        profilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LETS THE USER SEE THE PERMISION
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //LETS THE USER CHOSE TO EXEXPT IT OR DENIE IT
                    if (ContextCompat.checkSelfPermission(Register.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(Register.this, "Permision denied", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(Register.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    } else {
                        CHOOSEFOTO();
                    }
                } else {
                    CHOOSEFOTO();
                }
            }
        });


        //IF THE USER SAVES IT.
        register.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                RegisterButton();

            }
        });
    }

    private void GenderSpinner(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add(0, "Male");
        categories.add("Female");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(aa);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void RegisterButton(){
        final String regemail = email.getText().toString().trim();
        final String regpassword = password.getText().toString().trim();
        final String regphoneno = phoneno.getText().toString();
        final String regname = name.getText().toString();
        final String regaddress = address.getText().toString();
        final String reggender = gender.getSelectedItem().toString();
        final String confirnpass = repassword.getText().toString();
//                final String ProfileImage = imageUri.toString();
        if (TextUtils.isEmpty(regemail)) {
            email.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(regpassword)) {
            password.setError("password is empty");
            return;
        }
        if (regpassword.length() < 6) {
            password.setError("Password must be 6 characters long");
            return;
        }

        if(regpassword.equals(confirnpass)){

        }else {
            password.setError("Password not matched");
            return;
        }

        fAuth.createUserWithEmailAndPassword(regemail, regpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //send verification link
                    FirebaseUser userfirebase = fAuth.getCurrentUser();
                    userfirebase.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Register.this, "Verification email has been sent to your email !", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "OnFailure:  Email nor sent" +e.getMessage());

                        }
                    });

                    //Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("Contact Person").document(userID);
                    final StorageReference ImagesPath = storageReference.child("profile").child(user_id + ".jpg");

                    Map<String, Object> user = new HashMap<>();
                    user.put("UserID", userID);
                    user.put("Name", regname);
                    user.put("Email", regemail);
                    user.put("Password", regpassword);
                    user.put("PhoneNo", regphoneno);
                    user.put("Address", regaddress);
                    user.put("Gender", reggender);
                    user.put("ContactPerson",  "Contact Person");
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            String note = "Success";
                            String settext ="User Profile Created" ;

                            GlobalVariables globalVariable=(GlobalVariables)getApplication();  //Call the global variable class
                            globalVariable.setUserIDContactPerson(userID);              //Setting contact Person UserID in global Variables

                            AlertDoalogBoxSuccess(Register.this,note,settext);
                            System.out.println(note +"note   "+settext +"setText");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String note = "Failure";
                            String settext ="User Profile not Created" ;
                            AlertDoalogBoxFailure(Register.this,note,settext);

                        }
                    });

                    SAVE_NAME_AND_PHOTO(userID);
                } else {

                }
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
                profilepicture.setImageURI(mainImageURI);
            } else if (resultCode == FUCK_UP) {
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

                    Glide.with(Register.this).load(downUri).into(profilepicture);
                    fStore.collection("Contact Person").document(user_id).collection("ProfileImage").document().set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });
                }else {
                    String ERROR = task.getException().getMessage();
                    Toast.makeText(Register.this, ERROR, Toast.LENGTH_SHORT).show();
                }
            }

        });

    }


    private  void AlertDoalogBoxSuccess(Activity activity,String note,String setetxt){
        String noteone = note;
        String settexttwo= setetxt;
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_box);


        TextView notetext = (TextView) dialog.findViewById(R.id.TextNote);
        TextView info = (TextView) dialog.findViewById(R.id.Text);
        final ImageView image = (ImageView) findViewById(R.id.profileimage);
        notetext.setText(noteone);
        info.setText(settexttwo);

        Button okButton = (Button) dialog.findViewById(R.id.confirmbuttom);
        okButton.setText("OK");
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                startActivity(new Intent(Register.this, Contact_Person_Users_List.class));
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private  void AlertDoalogBoxFailure(Activity activity,String note,String setetxt){
        String noteone = note;
        String settexttwo= setetxt;
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_box);


        TextView notetext = (TextView) dialog.findViewById(R.id.TextNote);
        TextView info = (TextView) dialog.findViewById(R.id.Text);
        ImageView image = (ImageView) findViewById(R.id.profileimage);
        notetext.setText(noteone);
        info.setText(settexttwo);

        Button okButton = (Button) dialog.findViewById(R.id.confirmbuttom);
        okButton.setText("OK");
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}







