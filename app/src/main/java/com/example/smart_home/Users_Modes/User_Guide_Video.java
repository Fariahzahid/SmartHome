package com.example.smart_home.Users_Modes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.example.smart_home.User_Modes_Black_White.User_Home;
import com.example.smart_home.User_Voice_Mode.User_Home_Weather;
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
    String username,useremail,userphoneno,useraddress,usergender,colorblind,visionimpairment,hearingimpairment,
    physicalimpairement, dylexiadisorder, mutenessdisorder, yes = "YES", no = "NO";
    //FIREBASE STORAGE
    StorageReference storageReference;
    //String value,userid;
    FirebaseAuth fAuth;
    LinearLayout layout;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;
    private static final String TAG = "MyActivity";
    TextView identify;
    Button skip,skipcolorblind;

    String sleep_mode_bedroomlight,sleep_mode_bedroom_blinds,sleep_mode_bedroom_light_intensity,sleep_mode_bedrroom_nightlamp,sleep_mode_bedroom_chargingpoint,
            sleep_mode_lr_light,sleep_mode_lr_lightintensity,sleep_mode_lr_blinds,sleep_mode_lr_tablelamp,sleep_mode_lr_television,
            sleep_mode_kitchen_light,sleep_mode_kitchen_blinds,sleep_mode_kitchen_stove,sleep_mode_kitchen_oven,sleep_mode_kitchen_refrigrator,
            sleep_mode_wc_light,sleep_mode_wc_fan,
            moveout_mode_bedroomlight,moveout_mode_bedroom_blinds,moveout_mode_bedroom_light_intensity,
            moveout_mode_bedroom_nightlamp,moveout_mode_bedroom_chargingpoint,moveout_mode_bedroom_ac,moveout_mode_bedroom_actemp,moveout_mode_bedroom_heating,
            moveout_mode_bedroom_heating_temp, moveout_mode_lr_light,moveout_mode_lr_lightintensity,moveout_mode_lr_blinds,moveout_mode_lr_tablelamp,moveout_mode_lr_television,
            moveout_mode_lr_ac,moveout_mode_lr_actemp,moveout_mode_lr_heating,moveout_mode_lr_heatingtemp,moveout_mode_kitchen_light,moveout_mode_kitchen_blinds,
                    moveout_mode_kitchen_stove,moveout_mode_kitchen_oven,moveout_mode_kitchen_refrigrator,moveout_mode_kitchen_ac,moveout_mode_kitchen_actemp,
                    moveout_mode_kitchen_heating,moveout_mode_kitchen_heatingtemp,moveout_mode_wc_light,moveout_mode_wc_fan, moveout_mode_wc_heating,moveout_mode_wc_heatingtemp,

            automatic_mode_bedroom_bulbon,automatic_mode_bedroom_bulboff,automatic_mode_bedroom_nightlampon,automatic_mode_bedroom_nightlampoff,automatic_mode_bedroom_bulbIntensity,
            automatic_mode_lr_bulbintensity,automatic_mode_lr_bulbon,automatic_mode_lr_bulboff,automatic_mode_lr_tablelampon,automatic_mode_lr_tablelampoff,
            automatic_mode_lr_televisionon,automatic_mode_lr_televisionoff,automatic_mode_kitchen_bulbintensity,automatic_mode_kitchen_bulbon, automatic_mode_kitchen_bulboff,
            automatic_mode_kitchen_stoveon,automatic_mode_kitchen_stoveoff,automatic_mode_kitchen_refrigrator,automatic_mode_wc_bulbon,automatic_mode_wc_bulboff,
            automatic_mode_wc_fanon,automatic_mode_wc_fanoff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide_vidio);

        //btnPlay = (Button) findViewById(R.id.btn_Play);
        myVideo = (VideoView) findViewById(R.id.videoPlayer);
        skip = (Button) findViewById(R.id.skipbutton);
        skipcolorblind = (Button) findViewById(R.id.skipbuttoncolour);
        final GlobalVariables globalVariable=(GlobalVariables)getApplication();
        userid  = globalVariable.getUserIDUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();
        layout = (LinearLayout) findViewById(R.id.layout);
        identify = (TextView) findViewById(R.id.identify);
        mediaController = new MediaController(this);



        final DocumentReference documentReference = fStore.collection("USER").document(userid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                username = documentSnapshot.getString("Name");
                useremail = documentSnapshot.getString("Email");
                userphoneno =documentSnapshot.getString("Phone No");
                useraddress = documentSnapshot.getString("Address");
                usergender = documentSnapshot.getString("Gender");
                colorblind = documentSnapshot.getString("Color Blindness");
                hearingimpairment = documentSnapshot.getString("Hearing Impairment");
                visionimpairment = documentSnapshot.getString("Vision Impairment");
                physicalimpairement = documentSnapshot.getString("Physical Impairment");
                dylexiadisorder = documentSnapshot.getString("Dylexia Disorder");
                mutenessdisorder = documentSnapshot.getString("Muteness Disability");

                if(mutenessdisorder.equals(yes) || hearingimpairment.equals(yes)){
                    Toast.makeText(User_Guide_Video.this, "Sign Language Video Display",Toast.LENGTH_LONG).show();
                    String videoPath =
                            "android.resource://com.example.smart_home/"+R.raw.mutedeaf2;
                    Uri uri = Uri.parse(videoPath);
                    myVideo.setVideoURI(uri);
                    myVideo.setMediaController(mediaController);
                    mediaController.setAnchorView(myVideo);
                    myVideo.start();
//                    myVideo.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            Intent intent = new Intent(User_Guide_Video.this, User_Home_Colored.class);
//                            startActivity(intent);
//                        }
//
//                    });
                    layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(User_Guide_Video.this, User_Home_Colored.class);
                            startActivity(intent);
                        }

                    });
                    skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(User_Guide_Video.this, User_Home_Colored.class);
                            startActivity(intent);
                        }
                    });
                }
                if(colorblind.equals(yes)){
                    String videoPath =
                            "android.resource://com.example.smart_home/"+R.raw.colourblind;
                    Uri uri = Uri.parse(videoPath);
                    myVideo.setVideoURI(uri);
                    myVideo.setMediaController(mediaController);
                    mediaController.setAnchorView(myVideo);
                    myVideo.start();
                    myVideo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(User_Guide_Video.this, User_Home.class);
                            startActivity(intent);
                        }

                    });
                    layout.setBackgroundResource(R.drawable.grey_bakground);
                    skip.setVisibility(View.GONE);
                    skipcolorblind.setVisibility(View.VISIBLE);

                    skipcolorblind.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(User_Guide_Video.this, User_Home.class);
                            startActivity(intent);
                        }
                    });
                    Toast.makeText(User_Guide_Video.this, "Black And White Video Display",Toast.LENGTH_LONG).show();




                }
                if(visionimpairment.equals(yes)){
                    String videoPath =
                            "android.resource://com.example.smart_home/"+R.raw.voicemode;
                    Uri uri = Uri.parse(videoPath);
                    myVideo.setVideoURI(uri);
                    myVideo.setMediaController(mediaController);
                    mediaController.setAnchorView(myVideo);
                    myVideo.start();

                    myVideo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(User_Guide_Video.this, User_Home_Weather.class);
                            startActivity(intent);
                        }

                    });
                    layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(User_Guide_Video.this, User_Home_Weather.class);
                                startActivity(intent);
                            }

                        });
                    skip.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(User_Guide_Video.this, User_Home_Weather.class);
                                startActivity(intent);
                            }
                        });
                }
                if(dylexiadisorder.equals(yes) || physicalimpairement.equals(yes)){
                    String videoPath =
                            "android.resource://com.example.smart_home/"+R.raw.physical;
                    Uri uri = Uri.parse(videoPath);
                    myVideo.setVideoURI(uri);
                    myVideo.setMediaController(mediaController);
                    mediaController.setAnchorView(myVideo);
                    myVideo.start();
                    skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(User_Guide_Video.this, User_Home_Colored.class);
                            startActivity(intent);
                        }
                    });

                }
            }
        });



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

        ///ayuuuuuuuuuttttttttttttttttttttttttooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
        final DocumentReference documentReference9 = fStore.collection("USER").document(userid)
                .collection("Automatic_Mode_BedRoom").document("Bedroom");
        documentReference9.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                automatic_mode_bedroom_bulbon = documentSnapshot.getString("BulbOn");
                automatic_mode_bedroom_bulboff = documentSnapshot.getString("BulbOff");
                automatic_mode_bedroom_bulbIntensity = documentSnapshot.getString("BulbIntensity");
                automatic_mode_bedroom_nightlampon = documentSnapshot.getString("NightLampOn");
                automatic_mode_bedroom_nightlampoff = documentSnapshot.getString("NightLampOff");


                globalVariable.setAutomatic_mode_bedroom_bulbon(automatic_mode_bedroom_bulbon);
                globalVariable.setAutomatic_mode_bedroom_bulboff(automatic_mode_bedroom_bulboff);
                globalVariable.setAutomatic_mode_bedroom_lightintensity(automatic_mode_bedroom_bulbIntensity);
                globalVariable.setAutomatic_mode_bedroom_nightlampon(automatic_mode_bedroom_nightlampon);
                globalVariable.setAutomatic_mode_bedroom_nightlampoff(automatic_mode_bedroom_nightlampoff);
            }
        });
        final DocumentReference documentReference10 = fStore.collection("USER").document(userid)
                .collection("Automatic_Mode_LivingRoom").document("LivingRoom");
        documentReference10.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                automatic_mode_lr_bulbon = documentSnapshot.getString("BulbOn");
                automatic_mode_lr_bulboff = documentSnapshot.getString("BulbOff");
                automatic_mode_lr_bulbintensity = documentSnapshot.getString("BulbIntensity");
                automatic_mode_lr_tablelampon = documentSnapshot.getString("TableLampOn");
                automatic_mode_lr_tablelampoff = documentSnapshot.getString("TableLampOFF");
                automatic_mode_lr_televisionon = documentSnapshot.getString("TelevisionOn");
                automatic_mode_lr_televisionoff = documentSnapshot.getString("TelevisionOff");


                globalVariable.setAutomatic_mode_livingroom_bulbon(automatic_mode_lr_bulbon);
                globalVariable.setAutomatic_mode_livingroom_bulboff(automatic_mode_lr_bulboff);
                globalVariable.setAutomatic_mode_livingroom_lightintensity(automatic_mode_lr_bulbintensity);
                globalVariable.setAutomatic_mode_livingroom_tablelampon(automatic_mode_lr_tablelampon);
                globalVariable.setAutomatic_mode_livingroom_tablelampoff(automatic_mode_lr_tablelampoff);
                globalVariable.setAutomatic_mode_livingroom_televisionon(automatic_mode_lr_televisionon);
                globalVariable.setAutomatic_mode_livingroom_televisionoff(automatic_mode_lr_televisionoff);

            }
        });
        final DocumentReference documentReference11 = fStore.collection("USER").document(userid)
                .collection("Automatic_Mode_Kitchen").document("Kitchen");
        documentReference11.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                automatic_mode_kitchen_bulbon = documentSnapshot.getString("BulbOn");
                automatic_mode_kitchen_bulboff = documentSnapshot.getString("BulbOff");
                automatic_mode_kitchen_bulbintensity = documentSnapshot.getString("BulbIntensity");
                automatic_mode_kitchen_stoveon= documentSnapshot.getString("StoveOn");
                automatic_mode_kitchen_stoveoff = documentSnapshot.getString("StoveOFF");
                automatic_mode_kitchen_refrigrator = documentSnapshot.getString("Refrigrator");


                globalVariable.setAutomatic_mode_kitchen_bulbon(automatic_mode_kitchen_bulbon);
                globalVariable.setAutomatic_mode_kitchen_bulboff(automatic_mode_kitchen_bulboff);
                globalVariable.setAutomatic_mode_kitchen_lightintensity(automatic_mode_kitchen_bulbintensity);
                globalVariable.setAutomatic_mode_kitchen_stoveon(automatic_mode_kitchen_stoveon);
                globalVariable.setAutomatic_mode_kitchen_stoveoff(automatic_mode_kitchen_stoveoff);
                globalVariable.setAutomatic_mode_kitchen_refrigrator(automatic_mode_kitchen_refrigrator);

            }
        });
        final DocumentReference documentReference12 = fStore.collection("USER").document(userid)
                .collection("Automatic_Mode_WC").document("WC");
        documentReference12.addSnapshotListener(User_Guide_Video.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                automatic_mode_wc_bulbon = documentSnapshot.getString("BulbOn");
                automatic_mode_wc_bulboff = documentSnapshot.getString("BulbOff");
                automatic_mode_wc_fanon= documentSnapshot.getString("FanOn");
                automatic_mode_wc_fanoff = documentSnapshot.getString("FanOff");


                globalVariable.setAutomatic_mode_wc_bulbon(automatic_mode_wc_bulbon);
                globalVariable.setAutomatic_mode_wc_bulboff(automatic_mode_wc_bulboff);
                globalVariable.setAutomatic_mode_wc_fan_on(automatic_mode_wc_fanon);
                globalVariable.setAutomatic_mode_wc_fan_off(automatic_mode_wc_fanoff);

            }
        });
    }
}

