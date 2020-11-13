package com.example.smart_home.Contact_Person_Screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.smart_home.Contact_Person_SignIn.Login;
import com.example.smart_home.Contact_Person_SignIn.Register;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.MainActivity;
import com.example.smart_home.R;
import com.example.smart_home.Users_Modes.User_Guide_Video;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Add_New_Users extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    //LAYOUT
    EditText name,email,phoneno,password,repassword,address;
    Button register;
    Spinner gender;
    SwitchCompat mutedisability,hearingdisability,physicaldisability,colorblindness,visiondisability,dylexiadisorder;
    ImageView profileimage;
    Boolean status = false;
    TextView test;
    private String mute = "NO",deaf="NO",phydisability="NO",blind="NO",dylexia="NO",colorblind="NO";
    String contactemail,contactpassword;
    //USER ID
    private String userID;
    //URI
    private Uri mainImageURI;
    //REQUEST CODE
    public static final int REQUEST = 1;
    public static final int REQUEST_TWO = 2;
    //FIREBASE STORAGE
    private StorageReference storageReference;
    //FIREBASE AUTHENTICATION
    FirebaseAuth fAuth;
    //FIREBASE FIRESTORE
    FirebaseFirestore fStore;
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
        test = (TextView) findViewById(R.id.disability_spinner_two);

        mutedisability = findViewById(R.id.switch1);
        hearingdisability = findViewById(R.id.switch2);
        physicaldisability = findViewById(R.id.switch3);
        colorblindness = findViewById(R.id.switch4);
        visiondisability = findViewById(R.id.switch5);
        dylexiadisorder = findViewById(R.id.switch6);

        profileimage = findViewById(R.id.user_profile_image);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        GenderSpinner();
        AddDisabilities();


        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //LETS THE USER SEE THE PERMISION
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //LETS THE USER CHOSE TO EXEXPT IT OR DENIE IT
                    if (ContextCompat.checkSelfPermission(Contact_Person_Add_New_Users.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(Contact_Person_Add_New_Users.this, "Permision denied", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(Contact_Person_Add_New_Users.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    } else {
                        CHOOSEFOTO();
                    }
                } else {
                    CHOOSEFOTO();
                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });

    }
    private void AddDisabilities(){
            mutedisability.setChecked(status);
            hearingdisability.setChecked(status);
            physicaldisability.setChecked(status);
            colorblindness.setChecked(status);
            visiondisability.setChecked(status);
            dylexiadisorder.setChecked(status);

        mutedisability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    mute = "YES";
                }else if (!isChecked){
                    mute ="NO";
                }

            }
        });
        hearingdisability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    deaf = "YES";
                }else if (!isChecked){
                    deaf ="NO";
                }

            }
        });
        physicaldisability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    phydisability = "YES";
                }else if (!isChecked){
                    phydisability ="NO";
                }

            }
        });
        colorblindness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    colorblind = "YES";
                }else if (!isChecked){
                    colorblind ="NO";
                }

            }
        });
        visiondisability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    blind = "YES";
                }else if (!isChecked){
                    blind ="NO";
                }

            }
        });
        dylexiadisorder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    dylexia = "YES";
                }else if (!isChecked){
                    dylexia ="NO";
                }

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
    private void RegisterUser(){

        final GlobalVariables globalVariable=(GlobalVariables)getApplication();  //Call the global variable class
        String Contact_Person_User_Id = globalVariable.getUserIDContactPerson();

        final String regemail = email.getText().toString().trim();
        final String regpassword = password.getText().toString().trim();
        final String regphoneno = phoneno.getText().toString();
        final String regname = name.getText().toString();
        final String regaddress = address.getText().toString();
        final String reggender = gender.getSelectedItem().toString();
        final String regconpassword= repassword.getText().toString().trim();
        final String cp_userid = Contact_Person_User_Id.toString();


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
                if(!regpassword.equals(regconpassword)){
                    password.setError("Password Not Matched");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(regemail,regpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Contact_Person_Add_New_Users.this, "User Created.",Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            globalVariable.setUserIDUser(userID);
                            DocumentReference documentReference = fStore.collection("USER").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("UserID",userID);
                            user.put("Name", regname);
                            user.put("Email",regemail);
                            user.put("Password",regpassword);
                            user.put("Phone No", regphoneno);
                            user.put("Address", regaddress);
                            user.put("Gender",reggender);
                            user.put("Role",  "User");
                            user.put("ContactPersonUserId",cp_userid);
                            user.put("Muteness Disability",mute);
                            user.put("Hearing Impairment",deaf);
                            user.put("Vision Impairment",blind);
                            user.put("Color Blindness",colorblind);
                            user.put("Physical Impairment",phydisability);
                            user.put("Dylexia Disorder",dylexia);

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

//                            Intent intent = new Intent(Contact_Person_Add_New_Users.this,Contact_Person_Users_List.class);
//                            startActivity(intent);
                            SAVE_NAME_AND_PHOTO(userID);



                          //  contactemail = globalVariable.getContactpersonemail();
                          //  contactpassword = globalVariable.getContactpersonpassword();
                          //  userID = fAuth.getCurrentUser().getUid();

                          //  fAuth.signOut();
//                            fAuth.signInWithEmailAndPassword(contactemail,contactpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if(task.isSuccessful()){
//                                        Toast.makeText(Contact_Person_Add_New_Users.this, "User Logged In.",Toast.LENGTH_SHORT).show();
//                                        userID =fAuth.getCurrentUser().getUid();
//                                        GlobalVariables globalVariable=(GlobalVariables)getApplication();
//                                        globalVariable.setUserIDContactPerson(userID);
//
//                                        Intent intent = new Intent(Contact_Person_Add_New_Users.this, Contact_Person_User_Modes.class);
//                                        startActivity(intent);
//
//                                    }
//                                    else{
//                                        //Toast.makeText(Contact_Person_Add_New_Users.this, "Error."+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//
//                                    }
//                                }
//                            });


                        }else {
                            Toast.makeText(Contact_Person_Add_New_Users.this, "Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }

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
            } else if (resultCode == REQUEST_TWO) {
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

                    Glide.with(Contact_Person_Add_New_Users.this).load(downUri).into(profileimage);
                    fStore.collection("USER").document(user_id).collection("ProfileImage").document("Image").set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });
                }else {
                    String ERROR = task.getException().getMessage();
                    Toast.makeText(Contact_Person_Add_New_Users.this, ERROR, Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}
