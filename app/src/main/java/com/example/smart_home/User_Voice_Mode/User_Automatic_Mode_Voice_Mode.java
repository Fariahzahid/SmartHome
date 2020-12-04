package com.example.smart_home.User_Voice_Mode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class User_Automatic_Mode_Voice_Mode extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    TextView progress;

    TextView texttovoice,voicetotext;
    TextToSpeech textToSpeech;
    LinearLayout automaticLayout,manualmodelayout;
    String speaktotext;

    Button  manualmode_wc_bulb_on, manualmode_wc_bulb_off,
            manualmode_bedroom_bulb_on, manualmode_bedroom_bulb_off,
            manualmode_bedroom_blinds_on, manualmode_bedroom_blinds_off,
            manualmode_bedroom_nightlamp_on, manualmode_bedroom_nightlamp_off,
            manualmode_lr_bulb_on, manualmode_lr_bulb_off,
            manualmode_lr_tablelamp_on, manualmode_lr_tablelamp_off,
            manualmode_lr_television_on, manualmode_lr_television_off,
            manualmode_kitchen_bulb_on, manualmode_kitchen_bulb_off,
            manualmode_bedroom_heating_on,manualmode_bedroom_heating_off,
            manualmode_bedroom_cooling_on,manualmode_bedroom_cooling_off;
    String currentTime,sunrise,sunset,weather,windspeed;

    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;

    String on ="ON",off="OFF",open="OPEN",close="CLOSE";
    public static final String inputFormat = "HH:mm";
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.GERMANY);

    LinearLayout bedroombulblayout, livingroombulblayout, kitchenbulblayout, bedroomnightlamplayout,
            livingroomtablelamplayout,livingroomtelevisionlayout,one,two,three,four,five,six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_automatic_mode_wooden_voice);


        texttovoice = (TextView) findViewById(R.id.texttovoice);
        voicetotext = (TextView) findViewById(R.id.voicetotext);
        automaticLayout = findViewById(R.id.automaticLayout);
        manualmodelayout = findViewById(R.id.manualmode_layout);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        currentTime = df.format(c.getTime());

        final GlobalVariables globalVariable=(GlobalVariables)getApplication();


        manualmode_wc_bulb_on = (Button) findViewById(R.id.manualmode_wc_bulb_on);
        manualmode_wc_bulb_off = (Button) findViewById(R.id.manualmode_wc_bulb_off);

        manualmode_bedroom_bulb_on = (Button) findViewById(R.id.manualmode_bedroom_bulb_on);
        manualmode_bedroom_bulb_off = (Button) findViewById(R.id.manualmode_bedroom_bulb_off);

        manualmode_bedroom_blinds_on = (Button) findViewById(R.id.manualmode_bedroom_blinds_on);
        manualmode_bedroom_blinds_off = (Button) findViewById(R.id.manualmode_bedroom_blinds_off);

        manualmode_bedroom_nightlamp_on = (Button) findViewById(R.id.manualmode_bedroom_nightlamp_on);
        manualmode_bedroom_nightlamp_off = (Button) findViewById(R.id.manualmode_bedroom_nightlamp_off);

        manualmode_lr_bulb_on = (Button) findViewById(R.id.manualmode_lr_bulb_on);
        manualmode_lr_bulb_off = (Button) findViewById(R.id.manualmode_lr_bulb_off);

        manualmode_lr_tablelamp_on = (Button) findViewById(R.id.manualmode_lr_tablelamp_on);
        manualmode_lr_tablelamp_off = (Button) findViewById(R.id.manualmode_lr_tablelamp_off);

        manualmode_lr_television_on = (Button) findViewById(R.id.manualmode_lr_television_on);
        manualmode_lr_television_off = (Button) findViewById(R.id.manualmode_lr_television_off);

        manualmode_kitchen_bulb_on = (Button) findViewById(R.id.manualmode_kitchen_bulb_on);
        manualmode_kitchen_bulb_off = (Button) findViewById(R.id.manualmode_kitchen_bulb_off);

        manualmode_bedroom_heating_on = (Button) findViewById(R.id.manualmode_bedroom_heating_on);
        manualmode_bedroom_heating_off = (Button) findViewById(R.id.manualmode_bedroom_heating_off);
        manualmode_bedroom_cooling_on = (Button) findViewById(R.id.manualmode_bedroom_cooling_on);
        manualmode_bedroom_cooling_off = (Button) findViewById(R.id.manualmode_bedroom_cooling_off);


        bedroombulblayout = (LinearLayout) findViewById(R.id.bedroombulblayout);
        livingroombulblayout = (LinearLayout) findViewById(R.id.livingroombulblayout);
        kitchenbulblayout = (LinearLayout) findViewById(R.id.kitchenbulblayout);
        bedroomnightlamplayout = (LinearLayout) findViewById(R.id.bedroomnightlamplayout);
        livingroomtablelamplayout = (LinearLayout) findViewById(R.id.livingroomtablelamplayout);
        livingroomtelevisionlayout = (LinearLayout) findViewById(R.id.livingroomtelevisionlayout);

        one = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_one);
        two = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_two);
        three = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_three);
        four = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_four);
        five = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_five);
        six = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_six);

        getTemperature();

        progress = (TextView) findViewById(R.id.progress);
        texttovoice.setText("Speak 1 ON and OFF for the WC Light , " +
                "Speak 2 ON and OFF for Room Light ,");
        // Init TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "This language is not supported!",
                                Toast.LENGTH_SHORT);
                    } else {
                        automaticLayout.setEnabled(true);
                        textToSpeech.setPitch(0.6f);
                        textToSpeech.setSpeechRate(1.0f);

                        textspeak();
                    }
                }
            }
        });

        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null)
                    voicetotext.setText(matches.get(0));
                speaktotext = voicetotext.getText().toString();
                speaktext(speaktotext);
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });


        checkPermission();

        automaticLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                textToSpeech.stop();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        voicetotext.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        voicetotext.setText("");
                        voicetotext.setHint("Listening...");

                        break;
                }
                return false;
            }
        });
        manualmodelayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                textToSpeech.stop();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        voicetotext.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        voicetotext.setText("");
                        voicetotext.setHint("Listening...");

                        break;
                }
                return false;
            }
        });

    }
    private void speaktext(String text) {
        String test = text.toString();
        String numberoneON = "one on";
        String numberoneoneON = "1 on";
        String numbertwoON = "two on";
        String numbertwotwoON = "2 on";

        String numberoneOff = "one of";
        String numberoneoneOff = "1 of";
        String numbertwoOff = "two of";
        String numbertwotwoOff = "2 of";

        String numberoneOf = "one off";
        String numberoneoneOf = "1 off";
        String numbertwoOf = "two off";
        String numbertwotwoOf = "2 off";


        if (test.equals(numberoneON) || text.equals(numberoneoneON)) {
            one.setBackgroundColor(getColor(R.color.greentwo));

            manualmode_wc_bulb_on.setVisibility(View.GONE);
            manualmode_wc_bulb_off.setVisibility(View.VISIBLE);
        } else if (test.equals(numberoneOff) || test.equals(numberoneoneOff) || test.equals(numberoneOf) || test.equals(numberoneoneOf)) {
            one.setBackgroundColor(getColor(android.R.color.transparent));

            manualmode_wc_bulb_on.setVisibility(View.VISIBLE);
            manualmode_wc_bulb_off.setVisibility(View.GONE);
        } else if  (test.equals(numbertwoON) || text.equals(numbertwotwoON)) {
            two.setBackgroundColor(getColor(R.color.greentwo));
            manualmode_bedroom_bulb_on.setVisibility(View.GONE);
            manualmode_bedroom_bulb_off.setVisibility(View.VISIBLE);
        } else if (test.equals(numbertwoOff) || test.equals(numbertwotwoOff) || test.equals(numbertwoOf) || test.equals(numbertwotwoOf)) {
            two.setBackgroundColor(getColor(android.R.color.transparent));

            manualmode_bedroom_bulb_on.setVisibility(View.VISIBLE);
            manualmode_bedroom_bulb_off.setVisibility(View.GONE);
        } else {
            texttovoice.setText("Sorry to understand your voice ,      Please Speck Again !");
            textspeak();
        }

    }

    private void textspeak() {
        String text = texttovoice.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }

    private void getValues(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        currentTime = df.format(c.getTime());

        final GlobalVariables globalVariable = (GlobalVariables) getApplication();
//        sunrise = globalVariable.getSunrise();
//        sunset = globalVariable.getSunset();
//        weather = globalVariable.getWeather();
//        windspeed = globalVariable.getWindspeed();
//
//

        getWeather(currentTime);

        //ROOMS BULBS STATUS
        String bulbOn = globalVariable.getAutomatic_mode_livingroom_bulbon();
        String bulboff = globalVariable.getAutomatic_mode_livingroom_bulboff();
        String bedroombulbOn = globalVariable.getAutomatic_mode_bedroom_bulbon();
        String bedroombulboff = globalVariable.getAutomatic_mode_bedroom_bulboff();
        try {
            Date date1 = df.parse(bulbOn);
            Date date2 = df.parse(bulboff);
            Date date3 = df.parse(bedroombulbOn);
            Date date4 = df.parse(bedroombulboff);

            Date current = df.parse(currentTime);
            if(current.after(date1)&& current.before(date2)){
                two.setBackgroundColor(getColor(R.color.greentwo));

                bedroombulblayout.setVisibility(View.GONE);
                livingroombulblayout.setVisibility(View.VISIBLE);
                manualmode_lr_bulb_on.setVisibility(View.GONE);
                manualmode_lr_bulb_off.setVisibility(View.VISIBLE);
            }
            else if(current.after(date3)&& current.before(date4)){
                two.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_bedroom_bulb_on.setVisibility(View.GONE);
                manualmode_bedroom_bulb_off.setVisibility(View.VISIBLE);

            }
            else{
                two.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_bedroom_bulb_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_bulb_off.setVisibility(View.GONE);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        String nightlampOn = globalVariable.getAutomatic_mode_bedroom_nightlampon();
        String nightLampOff = globalVariable.getAutomatic_mode_bedroom_nightlampoff();
        String tablelampOn = globalVariable.getAutomatic_mode_livingroom_tablelampon();
        String tablelampoff = globalVariable.getAutomatic_mode_livingroom_tablelampoff();
        String televisionon = globalVariable.getAutomatic_mode_livingroom_televisionon();
        String televisionoff = globalVariable.getAutomatic_mode_livingroom_televisionoff();

        //NIGHT LAMP, TABLE LAMP, TELEVISION STATUS
        try {
            Date date1 = df.parse(nightlampOn);
            Date date2 = df.parse(nightLampOff);
            Date date3 = df.parse(tablelampOn);
            Date date4 = df.parse(tablelampoff);
            Date date5 = df.parse(televisionon);
            Date date6 = df.parse(televisionoff);
            Date current = df.parse(currentTime);

            if (current.after(date1) && current.before(date2)) {
                three.setBackgroundColor(getColor(R.color.greentwo));
                manualmode_bedroom_nightlamp_on.setVisibility(View.GONE);
                manualmode_bedroom_nightlamp_off.setVisibility(View.VISIBLE);

            } else if(current.after(date3) && current.before(date4)){
                three.setBackgroundColor(getColor(R.color.greentwo));
//
                bedroomnightlamplayout.setVisibility(View.GONE);
                livingroomtelevisionlayout.setVisibility(View.GONE);
                livingroomtablelamplayout.setVisibility(View.VISIBLE);

                manualmode_lr_tablelamp_on.setVisibility(View.GONE);
                manualmode_lr_tablelamp_off.setVisibility(View.VISIBLE);
            }else if(current.after(date5) && current.before(date6)){
                three.setBackgroundColor(getColor(R.color.greentwo));

                bedroomnightlamplayout.setVisibility(View.GONE);
                livingroomtablelamplayout.setVisibility(View.GONE);
                livingroomtelevisionlayout.setVisibility(View.VISIBLE);
                manualmode_lr_television_on.setVisibility(View.GONE);
                manualmode_lr_television_off.setVisibility(View.VISIBLE);
            }
            else {
                three.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_bedroom_nightlamp_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_nightlamp_off.setVisibility(View.GONE);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        String a = globalVariable.getSleep_mode_bedroom_heating();
        String d = globalVariable.getSleep_mode_bedroom_ac();



        if(a.equals(on)){
            five.setBackgroundColor(getColor(R.color.greentwo));

            manualmode_bedroom_heating_on.setVisibility(View.GONE);
            manualmode_bedroom_heating_off.setVisibility(View.VISIBLE);
        }
        else
        {
            five.setBackgroundColor(getColor(android.R.color.transparent));

            manualmode_bedroom_heating_on.setVisibility(View.VISIBLE);
            manualmode_bedroom_heating_off.setVisibility(View.GONE);
        }
        if(d.equals(on)){
            six.setBackgroundColor(getColor(R.color.greentwo));

            manualmode_bedroom_cooling_on.setVisibility(View.GONE);
            manualmode_bedroom_cooling_off.setVisibility(View.VISIBLE);
        }
        else {
            six.setBackgroundColor(getColor(android.R.color.transparent));

            manualmode_bedroom_cooling_on.setVisibility(View.VISIBLE);
            manualmode_bedroom_cooling_off.setVisibility(View.GONE);
        }



        //TOILET BULB ON/OFF
        manualmode_wc_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_wc_bulb_on.setVisibility(View.GONE);
                manualmode_wc_bulb_off.setVisibility(View.VISIBLE);

            }
        });
        manualmode_wc_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_wc_bulb_on.setVisibility(View.VISIBLE);
                manualmode_wc_bulb_off.setVisibility(View.GONE);
            }
        });

        //BEDROOM LIGHTS ON/OFF
        manualmode_bedroom_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_bedroom_bulb_on.setVisibility(View.GONE);
                manualmode_bedroom_bulb_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_bedroom_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_bedroom_bulb_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_bulb_off.setVisibility(View.GONE);
            }
        });

        //BEDROOM BLINDS ON/OFF
        manualmode_bedroom_blinds_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                four.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_bedroom_blinds_on.setVisibility(View.GONE);
                manualmode_bedroom_blinds_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_bedroom_blinds_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                four.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_bedroom_blinds_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_blinds_off.setVisibility(View.GONE);
            }
        });
        //BEDROOM NIGHT LAMP ON/OFF
        manualmode_bedroom_nightlamp_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_bedroom_nightlamp_on.setVisibility(View.GONE);
                manualmode_bedroom_nightlamp_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_bedroom_nightlamp_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_bedroom_nightlamp_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_nightlamp_off.setVisibility(View.GONE);
            }
        });



        //LIVING ROOM LIGHT ON/OFF
        manualmode_lr_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_lr_bulb_on.setVisibility(View.GONE);
                manualmode_lr_bulb_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_lr_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_lr_bulb_on.setVisibility(View.VISIBLE);
                manualmode_lr_bulb_off.setVisibility(View.GONE);
            }
        });

        //LIVING ROOM TABLE LAMP ON/OFF
        manualmode_lr_tablelamp_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_lr_tablelamp_on.setVisibility(View.GONE);
                manualmode_lr_tablelamp_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_lr_tablelamp_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_lr_tablelamp_on.setVisibility(View.VISIBLE);
                manualmode_lr_tablelamp_off.setVisibility(View.GONE);
            }
        });

        //LIVING ROOM TELEVISION ON/OFF
        manualmode_lr_television_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_lr_television_on.setVisibility(View.GONE);
                manualmode_lr_television_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_lr_television_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_lr_television_on.setVisibility(View.VISIBLE);
                manualmode_lr_television_off.setVisibility(View.GONE);
            }
        });

        //KITCHEN LIGHTS ON/OFF
        manualmode_kitchen_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_kitchen_bulb_on.setVisibility(View.GONE);
                manualmode_kitchen_bulb_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_kitchen_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_kitchen_bulb_on.setVisibility(View.VISIBLE);
                manualmode_kitchen_bulb_off.setVisibility(View.GONE);
            }
        });

        manualmode_bedroom_heating_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                five.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_bedroom_heating_on.setVisibility(View.GONE);
                manualmode_bedroom_heating_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_bedroom_heating_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                five.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_bedroom_heating_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_heating_off.setVisibility(View.GONE);
            }
        });
        manualmode_bedroom_cooling_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                six.setBackgroundColor(getColor(R.color.greentwo));

                manualmode_bedroom_cooling_on.setVisibility(View.GONE);
                manualmode_bedroom_cooling_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_bedroom_cooling_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                six.setBackgroundColor(getColor(android.R.color.transparent));

                manualmode_bedroom_cooling_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_cooling_off.setVisibility(View.GONE);
            }
        });
    }
    private void getTemperature(){

        fStore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        String userID = mFirebaseAuth.getCurrentUser().getUid();
        final GlobalVariables globalVariable=(GlobalVariables)getApplication();

        DocumentReference documentReference = fStore.collection("USER").document(userID).collection("Temperature").document("Temperature");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String currentTemperature = documentSnapshot.getString("Temperature");
                String[] currenttimeArray = currentTemperature.split("\\.");

                int one = Integer.parseInt(currenttimeArray[0]);
                int two = Integer.parseInt(currenttimeArray[1]);

                if(one <=25){
                    globalVariable.setSleep_mode_bedroom_ac("OFF");
                    globalVariable.setSleep_mode_bedroom_ac_temperature("0" + (char) 0x00B0 +"C");
                    globalVariable.setSleep_mode_bedroom_heating("ON");
                    globalVariable.setSleep_mode_bedroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setManual_mode_bedroom_ac("OFF");
                    globalVariable.setManual_mode_bedroom_ac_temperature("0" + (char) 0x00B0 +"C");
                    globalVariable.setManual_mode_bedroom_heating("ON");
                    globalVariable.setManual_mode_bedroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setAutomatic_mode_bedroom_ac("OFF");
                    globalVariable.setAutomatic_mode_bedroom_ac_temperature("0" + (char) 0x00B0 +"C");
                    globalVariable.setAutomatic_mode_bedroom_heating("ON");
                    globalVariable.setAutomatic_mode_bedroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setSleep_mode_livingroom_ac("OFF");
                    globalVariable.setSleep_mode_livingroom_ac_temperature("0" + (char) 0x00B0 +"C");
                    globalVariable.setSleep_mode_livingroom_heating("ON");
                    globalVariable.setSleep_mode_livingroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setManual_mode_livingroom_ac("OFF");
                    globalVariable.setManual_mode_livingroom_ac_temperature("0" + (char) 0x00B0 +"C");
                    globalVariable.setManual_mode_livingroom_heating("ON");
                    globalVariable.setManual_mode_livingroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setAutomatic_mode_livingroom_ac("OFF");
                    globalVariable.setAutomatic_mode_livingroom_ac_temperature("0" + (char) 0x00B0 +"C");
                    globalVariable.setAutomatic_mode_livingroom_heating("ON");
                    globalVariable.setAutomatic_mode_livingroom_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setSleep_mode_kitchen_ac("OFF");
                    globalVariable.setSleep_mode_kitchen_ac_temperature("0" + (char) 0x00B0 +"C");
                    globalVariable.setSleep_mode_kitchen_heating("ON");
                    globalVariable.setSleep_mode_kitchen_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setManual_mode_kitchen_ac("OFF");
                    globalVariable.setManual_mode_kitchen_ac_temperature("0" + (char) 0x00B0 +"C");
                    globalVariable.setManual_mode_kitchen_heating("ON");
                    globalVariable.setManual_mode_kitchen_ac_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setAutomatic_mode_kitchen_ac("OFF");
                    globalVariable.setAutomatic_mode_kitchen_ac_temperature("0" + (char) 0x00B0 +"C");
                    globalVariable.setAutomatic_mode_kitchen_heating("ON");
                    globalVariable.setAutomatic_mode_kitchen_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setSleep_mode_wc_heating("ON");
                    globalVariable.setSleep_mode_wc_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setManual_mode_wc_heating("ON");
                    globalVariable.setManual_mode_wc_heating_temperature("30 "+ (char) 0x00B0 +"C" );

                    globalVariable.setAutomatic_mode_wc_heating("ON");
                    globalVariable.setAutomatic_mode_wc_heating_temperature("30 "+ (char) 0x00B0 +"C" );
                }
                if(one >= 25){
                    globalVariable.setSleep_mode_bedroom_ac("ON");
                    globalVariable.setSleep_mode_bedroom_ac_temperature("20" + (char) 0x00B0 +"C");
                    globalVariable.setSleep_mode_bedroom_heating("OFF");
                    globalVariable.setSleep_mode_bedroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setManual_mode_bedroom_ac("ON");
                    globalVariable.setManual_mode_bedroom_ac_temperature("20" + (char) 0x00B0 +"C");
                    globalVariable.setManual_mode_bedroom_heating("OFF");
                    globalVariable.setManual_mode_bedroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setAutomatic_mode_bedroom_ac("ON");
                    globalVariable.setAutomatic_mode_bedroom_ac_temperature("20" + (char) 0x00B0 +"C");
                    globalVariable.setAutomatic_mode_bedroom_heating("OFF");
                    globalVariable.setAutomatic_mode_bedroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setSleep_mode_livingroom_ac("ON");
                    globalVariable.setSleep_mode_livingroom_ac_temperature("20" + (char) 0x00B0 +"C");
                    globalVariable.setSleep_mode_livingroom_heating("OFF");
                    globalVariable.setSleep_mode_livingroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setManual_mode_livingroom_ac("ON");
                    globalVariable.setManual_mode_livingroom_ac_temperature("20" + (char) 0x00B0 +"C");
                    globalVariable.setManual_mode_livingroom_heating("OFF");
                    globalVariable.setManual_mode_livingroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setAutomatic_mode_livingroom_ac("ON");
                    globalVariable.setAutomatic_mode_livingroom_ac_temperature("20" + (char) 0x00B0 +"C");
                    globalVariable.setAutomatic_mode_livingroom_heating("OFF");
                    globalVariable.setAutomatic_mode_livingroom_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setSleep_mode_kitchen_ac("ON");
                    globalVariable.setSleep_mode_kitchen_ac_temperature("20" + (char) 0x00B0 +"C");
                    globalVariable.setSleep_mode_kitchen_heating("OFF");
                    globalVariable.setSleep_mode_kitchen_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setManual_mode_kitchen_ac("ON");
                    globalVariable.setManual_mode_kitchen_ac_temperature("20" + (char) 0x00B0 +"C");
                    globalVariable.setManual_mode_kitchen_heating("OFF");
                    globalVariable.setManual_mode_kitchen_ac_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setAutomatic_mode_kitchen_ac("ON");
                    globalVariable.setAutomatic_mode_kitchen_ac_temperature("20" + (char) 0x00B0 +"C");
                    globalVariable.setAutomatic_mode_kitchen_heating("OFF");
                    globalVariable.setAutomatic_mode_kitchen_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setSleep_mode_wc_heating("OFF");
                    globalVariable.setSleep_mode_wc_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setManual_mode_wc_heating("OFF");
                    globalVariable.setManual_mode_wc_heating_temperature("0 "+ (char) 0x00B0 +"C" );

                    globalVariable.setAutomatic_mode_wc_heating("OFF");
                    globalVariable.setAutomatic_mode_wc_heating_temperature("0 "+ (char) 0x00B0 +"C" );
                }

                getValues();

            }
        });
    }
    private void getWeather(final String time){

        Calendar c = Calendar.getInstance();
        final SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        fStore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        String userID = mFirebaseAuth.getCurrentUser().getUid();
        final GlobalVariables globalVariable=(GlobalVariables)getApplication();

        DocumentReference documentReference = fStore.collection("USER").document(userID).collection("Weather").document("Weather");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String City = documentSnapshot.getString("City");
                String WindSpeed = documentSnapshot.getString("WindSpeed");
                String LastUpdate = documentSnapshot.getString("LastUpdate");
                String Description = documentSnapshot.getString("Description");
                String Main = documentSnapshot.getString("Main");
                String Humidity = documentSnapshot.getString("Humidity");
                String Temperature = documentSnapshot.getString("Temperature");
                String TimeSunset = documentSnapshot.getString("TimeSunset");
                String TimeSunrise = documentSnapshot.getString("TimeSunrise");
                String Celcius = documentSnapshot.getString("Celcius");


                String[] currenttimeArray = Temperature.split(":");
                String one = currenttimeArray[0];
                String two = currenttimeArray[1];


                //WINDOW BLINDS ON and OFF STATUS
                try {
                    Date date1 = df.parse(TimeSunrise);
                    Date date2 = df.parse(TimeSunset);
                    Date current = df.parse(time);

                    if (current.after(date1) && current.before(date2)) {
                        if(Main.equals("Thunderstorm") || Main.equals("Rain") || Main.equals("Snow") ||
                                Main.equals("Tornado")){
                            four.setBackgroundColor(getColor(android.R.color.transparent));
                            manualmode_bedroom_blinds_on.setVisibility(View.VISIBLE);
                            manualmode_bedroom_blinds_off.setVisibility(View.GONE);
                        }
                        else{
                            four.setBackgroundColor(getColor(R.color.greentwo));
                            manualmode_bedroom_blinds_on.setVisibility(View.GONE);
                            manualmode_bedroom_blinds_off.setVisibility(View.VISIBLE);
                        }

                    } else {
                        four.setBackgroundColor(getColor(android.R.color.transparent));
                        manualmode_bedroom_blinds_on.setVisibility(View.VISIBLE);
                        manualmode_bedroom_blinds_off.setVisibility(View.GONE);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}

