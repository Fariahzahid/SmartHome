package com.example.smart_home.Users_Modes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG = "MyActivity";

    String sleep_mode_bedroomlight,sleep_mode_bedroom_blinds,sleep_mode_bedroom_light_intensity,sleep_mode_bedrroom_nightlamp,sleep_mode_bedroom_chargingpoint,
            sleep_mode_lr_light,sleep_mode_lr_lightintensity,sleep_mode_lr_blinds,sleep_mode_lr_tablelamp,sleep_mode_lr_television,
            sleep_mode_kitchen_light,sleep_mode_kitchen_blinds,sleep_mode_kitchen_stove,sleep_mode_kitchen_oven,sleep_mode_kitchen_refrigrator,
            sleep_mode_wc_light,sleep_mode_wc_fan,moveout_mode_bedroomlight,moveout_mode_bedroom_blinds,moveout_mode_bedroom_light_intensity,

            moveout_mode_bedroom_nightlamp,moveout_mode_bedroom_chargingpoint,moveout_mode_bedroom_ac,moveout_mode_bedroom_actemp,moveout_mode_bedroom_heating,
            moveout_mode_bedroom_heating_temp, moveout_mode_lr_light,moveout_mode_lr_lightintensity,moveout_mode_lr_blinds,moveout_mode_lr_tablelamp,moveout_mode_lr_television,
            moveout_mode_lr_ac,moveout_mode_lr_actemp,moveout_mode_lr_heating,moveout_mode_lr_heatingtemp,moveout_mode_kitchen_light,moveout_mode_kitchen_blinds,
                    moveout_mode_kitchen_stove,moveout_mode_kitchen_oven,moveout_mode_kitchen_refrigrator,moveout_mode_kitchen_ac,moveout_mode_kitchen_actemp,
                    moveout_mode_kitchen_heating,moveout_mode_kitchen_heatingtemp,moveout_mode_wc_light,moveout_mode_wc_fan, moveout_mode_wc_heating,moveout_mode_wc_heatingtemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide_vidio);

        btnPlay = (Button) findViewById(R.id.btn_Play);
        myVideo = (VideoView) findViewById(R.id.videoPlayer);
        final GlobalVariables globalVariable=(GlobalVariables)getApplication();
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
                //userdisabilityone = documentSnapshot.getString("DisabilityOne");
                //userdisabilitytwo = documentSnapshot.getString("DisabilityTwo");
                //userdisabilitythree = documentSnapshot.getString("DisabilityThree");
            }
        });

        mediaController = new MediaController(this);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(userdisabilityone == "Muteness Disability" || userdisabilityone == "Hearing Impairment"
//                || userdisabilitytwo == "Muteness Disability" || userdisabilitytwo == "Hearing Impairment"
//                || userdisabilitythree == "Muteness Disability" || userdisabilitythree == "Hearing Impairment"){
//                    Toast.makeText(User_Guide_Video.this, "Sign Language Video Display",Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(User_Guide_Video.this, User_Home_Voice_Recognition.class);
//                    startActivity(intent);
//                }
//
//                if(userdisabilityone == "Vision Impairment" || userdisabilityone == "Physical Impairment"
//                || userdisabilitytwo == "Vision Impairment" || userdisabilitytwo == "Physical Impairment"
//                || userdisabilitythree == "Vision Impairment" || userdisabilitythree == "Physical Impairment"){
//                    Toast.makeText(User_Guide_Video.this, "Voice and Screen Video Display",Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(User_Guide_Video.this, User_Home_Colored.class);
//                    startActivity(intent);
//                }
                Intent intent = new Intent(User_Guide_Video.this, User_Home_Colored.class);
                startActivity(intent);
            }

        });
//                String videoPath =
//                        "android.resource://com.example.videotest/"+R.raw.video;
//                Uri uri = Uri.parse(videoPath);
//                myVideo.setVideoURI(uri);
//                myVideo.setMediaController(mediaController);
//                mediaController.setAnchorView(myVideo);
//                myVideo.start();

                //Getting Data for Modes

                final DocumentReference documentReference1 = fStore.collection("USER").document(userid)
                        .collection("Sleep_Mode_Bedroom").document("Bedroom");
                documentReference1.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        Log.d(TAG, "ERROR 1......" +String.valueOf(e));
                        String error = String.valueOf(e);
                        sleep_mode_bedroomlight = documentSnapshot.getString("Bulb");
                        sleep_mode_bedroom_blinds= documentSnapshot.getString("WindowBlinds");
                        sleep_mode_bedroom_chargingpoint = documentSnapshot.getString("ChargingPoints");
                        sleep_mode_bedrroom_nightlamp = documentSnapshot.getString("NightLamp");
                        sleep_mode_bedroom_light_intensity = documentSnapshot.getString("BulbIntensity");
                        globalVariable.setSleep_mode_bedroom_light(sleep_mode_bedroomlight);
                        globalVariable.setSleep_mode_bedroom_windowblind(sleep_mode_bedroom_blinds);
                        globalVariable.setSleep_mode_bedroom_nightlamp(sleep_mode_bedrroom_nightlamp);
                        globalVariable.setSleep_mode_bedroom_charging(sleep_mode_bedroom_chargingpoint);
                        globalVariable.setSleep_mode_bedroom_lightintensity(sleep_mode_bedroom_light_intensity);
                    }
                });


                final DocumentReference documentReference2 = fStore.collection("USER").document(userid)
                        .collection("Sleep_Mode_Kitchen").document("Kitchen");
                documentReference2.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                        sleep_mode_kitchen_light = documentSnapshot.getString("Bulb");
                        sleep_mode_kitchen_blinds= documentSnapshot.getString("WindowBlinds");
                        sleep_mode_kitchen_oven = documentSnapshot.getString("Oven");
                        sleep_mode_kitchen_stove = documentSnapshot.getString("Stove");
                        sleep_mode_kitchen_refrigrator = documentSnapshot.getString("Refrigrator");
                        globalVariable.setSleep_mode_kitchen_light(sleep_mode_kitchen_light);
                        globalVariable.setSleep_mode_kitchen_windowblind(sleep_mode_kitchen_blinds);
                        globalVariable.setSleep_mode_kitchen_oven(sleep_mode_kitchen_oven);
                        globalVariable.setSleep_mode_kitchen_stove(sleep_mode_kitchen_stove);
                        globalVariable.setSleep_mode_kitchen_refrigrator(sleep_mode_kitchen_refrigrator);

                    }
                });


                final DocumentReference documentReference3 = fStore.collection("USER").document(userid)
                        .collection("Sleep_Mode_Livingroom").document("LivingRoom");
                documentReference3.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                        Log.d(TAG, "ERROR......" +String.valueOf(e));
                        sleep_mode_lr_light = documentSnapshot.getString("Bulb");
                        sleep_mode_lr_blinds= documentSnapshot.getString("WindowBlinds");
                        sleep_mode_lr_tablelamp = documentSnapshot.getString("Table Lamp");
                        sleep_mode_lr_television = documentSnapshot.getString("Television");
                        sleep_mode_lr_lightintensity = documentSnapshot.getString("Bulb Intensity");
                        globalVariable.setSleep_mode_livingroom_light(sleep_mode_lr_light);
                        globalVariable.setSleep_mode_livingroom_windowblind(sleep_mode_lr_blinds);
                        globalVariable.setSleep_mode_livingroom_tablelamp(sleep_mode_lr_tablelamp);
                        globalVariable.setSleep_mode_livingroom_television(sleep_mode_lr_television);
                        globalVariable.setSleep_mode_livingroom_lightintensity(sleep_mode_lr_lightintensity);

                    }
                });

                final DocumentReference documentReference4 = fStore.collection("USER").document(userid)
                        .collection("Sleep_Mode_WC").document("WC");
                documentReference4.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                        Log.d(TAG, "ERROR......" +String.valueOf(e));
                        sleep_mode_wc_light = documentSnapshot.getString("Bulb");
                        sleep_mode_wc_fan = documentSnapshot.getString("Extractorfan");
                        globalVariable.setSleep_mode_wc_light(sleep_mode_wc_light);
                        globalVariable.setSleep_mode_wc_fan(sleep_mode_wc_fan);
                    }
                });





        //BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB
        final DocumentReference documentReference5 = fStore.collection("USER").document(userid)
                .collection("Move_Out_Mode_BedRoom").document("Bedroom");
        documentReference5.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                moveout_mode_bedroomlight = documentSnapshot.getString("Bulb");
                moveout_mode_bedroom_blinds= documentSnapshot.getString("WindowBlinds");
                moveout_mode_bedroom_chargingpoint = documentSnapshot.getString("ChargingPoints");
                moveout_mode_bedroom_nightlamp = documentSnapshot.getString("NightLamps");
                moveout_mode_bedroom_light_intensity = documentSnapshot.getString("BulbIntensity");
                moveout_mode_bedroom_ac = documentSnapshot.getString("AC");
                moveout_mode_bedroom_actemp = documentSnapshot.getString("ACtemperature");
                moveout_mode_bedroom_heating = documentSnapshot.getString("Heating");
                moveout_mode_bedroom_heating_temp = documentSnapshot.getString("HeatingTemperature");
                globalVariable.setMoveout_mode_bedroom_light(moveout_mode_bedroomlight);
                globalVariable.setMoveout_mode_bedroom_windowblind(moveout_mode_bedroom_blinds);
                globalVariable.setMoveout_mode_bedroom_nightlamp(moveout_mode_bedroom_chargingpoint);
                globalVariable.setMoveout_mode_bedroom_charging(moveout_mode_bedroom_nightlamp);
                globalVariable.setMoveout_mode_bedroom_lightintensity(moveout_mode_bedroom_light_intensity);
                globalVariable.setMoveout_mode_bedroom_ac(moveout_mode_bedroom_ac);
                globalVariable.setMoveout_mode_bedroom_ac_temperature(moveout_mode_bedroom_actemp);
                globalVariable.setMoveout_mode_bedroom_heating(moveout_mode_bedroom_heating);
                globalVariable.setManual_mode_bedroom_heating_temperature(moveout_mode_bedroom_heating_temp);
            }
        });


        final DocumentReference documentReference6 = fStore.collection("USER").document(userid)
                .collection("Move_Out_Mode_Kitchen").document("Kitchen");
        documentReference6.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                moveout_mode_kitchen_light = documentSnapshot.getString("Bulb");
                moveout_mode_kitchen_blinds= documentSnapshot.getString("WindowBlinds");
                moveout_mode_kitchen_oven = documentSnapshot.getString("Oven");
                moveout_mode_kitchen_stove = documentSnapshot.getString("Stove");
                moveout_mode_kitchen_refrigrator = documentSnapshot.getString("Refrigrator");
                moveout_mode_kitchen_ac = documentSnapshot.getString("AC");
                moveout_mode_kitchen_actemp = documentSnapshot.getString("ACtemperature");
                moveout_mode_kitchen_heating = documentSnapshot.getString("Heating");
                moveout_mode_kitchen_heatingtemp= documentSnapshot.getString("HeatingTemperature");
                globalVariable.setMoveout_mode_kitchen_light(moveout_mode_kitchen_light);
                globalVariable.setMoveout_mode_kitchen_windowblind(moveout_mode_kitchen_blinds);
                globalVariable.setMoveout_mode_kitchen_oven(moveout_mode_kitchen_oven);
                globalVariable.setMoveout_mode_kitchen_stove(moveout_mode_kitchen_stove);
                globalVariable.setMoveout_mode_kitchen_refrigrator(moveout_mode_kitchen_refrigrator);
                globalVariable.setMoveout_mode_kitchen_ac(moveout_mode_kitchen_ac);
                globalVariable.setMoveout_mode_kitchen_ac_temperature(moveout_mode_kitchen_actemp);
                globalVariable.setMoveout_mode_kitchen_heating(moveout_mode_kitchen_heating);
                globalVariable.setMoveout_mode_kitchen_heating_temperature(moveout_mode_kitchen_heatingtemp);
            }
        });


        final DocumentReference documentReference7 = fStore.collection("USER").document(userid)
                .collection("Move_Out_Mode_LivingRoom").document("LivingRoom");
        documentReference7.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                moveout_mode_lr_light = documentSnapshot.getString("Bulb");
                moveout_mode_lr_blinds= documentSnapshot.getString("WindowBlinds");
                moveout_mode_lr_tablelamp = documentSnapshot.getString("TableLamp");
                moveout_mode_lr_television = documentSnapshot.getString("Television");
                moveout_mode_lr_lightintensity = documentSnapshot.getString("BulbIntensity");
                moveout_mode_lr_ac = documentSnapshot.getString("AC");
                moveout_mode_lr_actemp = documentSnapshot.getString("ACtemperature");
                moveout_mode_lr_heating = documentSnapshot.getString("Heating");
                moveout_mode_lr_heatingtemp= documentSnapshot.getString("HeatingTemperature");
                globalVariable.setMoveout_mode_livingroom_light(moveout_mode_lr_light);
                globalVariable.setMoveout_mode_livingroom_windowblind(moveout_mode_lr_blinds);
                globalVariable.setMoveout_mode_livingroom_tablelamp(moveout_mode_lr_tablelamp);
                globalVariable.setMoveout_mode_livingroom_television(moveout_mode_lr_television);
                globalVariable.setMoveout_mode_livingroom_lightintensity(moveout_mode_lr_lightintensity);
                globalVariable.setMoveout_mode_livingroom_ac(moveout_mode_lr_ac);
                globalVariable.setMoveout_mode_livingroom_ac_temperature(moveout_mode_lr_actemp);
                globalVariable.setMoveout_mode_livingroom_heating(moveout_mode_lr_heating);
                globalVariable.setMoveout_mode_livingroom_heating_temperature(moveout_mode_lr_heatingtemp);
            }
        });


        final DocumentReference documentReference8 = fStore.collection("USER").document(userid)
                .collection("Move_Out_Mode_WC").document("WC");
        documentReference8.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                moveout_mode_wc_light = documentSnapshot.getString("Bulb");
                moveout_mode_wc_fan = documentSnapshot.getString("ExtractorFan");
                moveout_mode_wc_heating = documentSnapshot.getString("Heating");
                moveout_mode_wc_heatingtemp = documentSnapshot.getString("HeatingTemperature");
                globalVariable.setMoveout_mode_wc_light(moveout_mode_wc_light);
                globalVariable.setMoveout_mode_wc_fan(moveout_mode_wc_fan);
                globalVariable.setMoveout_mode_wc_heating(moveout_mode_wc_heating);
                globalVariable.setMoveout_mode_wc_heating_temperature(moveout_mode_wc_heatingtemp);
            }
        });

         }
}

