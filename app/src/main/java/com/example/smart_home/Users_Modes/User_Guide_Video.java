package com.example.smart_home.Users_Modes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.example.smart_home.User_Voice_Mode.User_Home_Voice_Recognition;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class User_Guide_Video extends AppCompatActivity {
    Button btnPlay;
    VideoView myVideo;

    MediaController mediaController;
    String userid;
    String username,useremail,userphoneno,useraddress,usergender,userdisabilityone,userdisabilitytwo,userdisabilitythree;
    //FIREBASE STORAGE
    StorageReference storageReference;
    //String value,userid;
    FirebaseAuth fAuth;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide_vidio);

        btnPlay = (Button) findViewById(R.id.btn_Play);
        myVideo = (VideoView) findViewById(R.id.videoPlayer);
        GlobalVariables globalVariable=(GlobalVariables)getApplication();
        userid  = globalVariable.getUserIDUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                username = documentSnapshot.getString("Name");
                useremail = documentSnapshot.getString("Email");
                userphoneno =documentSnapshot.getString("Phone No");
                useraddress = documentSnapshot.getString("Address");
                usergender = documentSnapshot.getString("Gender");
                userdisabilityone = documentSnapshot.getString("DisabilityOne");
                userdisabilitytwo = documentSnapshot.getString("DisabilityTwo");
                userdisabilitythree = documentSnapshot.getString("DisabilityThree");
            }
        });

        mediaController = new MediaController(this);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userdisabilityone == "Muteness Disability" || userdisabilityone == "Hearing Impairment"
                || userdisabilitytwo == "Muteness Disability" || userdisabilitytwo == "Hearing Impairment"
                || userdisabilitythree == "Muteness Disability" || userdisabilitythree == "Hearing Impairment"){
                    Toast.makeText(User_Guide_Video.this, "Sign Language Video Display",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(User_Guide_Video.this, User_Home_Voice_Recognition.class);
                    startActivity(intent);
                }

                if(userdisabilityone == "Vision Impairment" || userdisabilityone == "Physical Impairment"
                || userdisabilitytwo == "Vision Impairment" || userdisabilitytwo == "Physical Impairment"
                || userdisabilitythree == "Vision Impairment" || userdisabilitythree == "Physical Impairment"){
                    Toast.makeText(User_Guide_Video.this, "Voice and Screen Video Display",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(User_Guide_Video.this,User_Home.class);
                    startActivity(intent);
                }

//                String videoPath =
//                        "android.resource://com.example.videotest/"+R.raw.video;
//                Uri uri = Uri.parse(videoPath);
//                myVideo.setVideoURI(uri);
//                myVideo.setMediaController(mediaController);
//                mediaController.setAnchorView(myVideo);
//                myVideo.start();

            }
        });
    }
}
