package com.example.smart_home.User_Voice_Mode;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class User_Home_Wooden_Voice_Mode extends AppCompatActivity {
    ConstraintLayout speechlayout;
    TextToSpeech textToSpeech;
    private TextView textofspeech, speechtotext;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String speaktotext;
    String currentTime;
    ImageView user_layout;

    IntentFilter intentfilter;
    float batteryTemp;
    String currentBatterytemp ="Current Battery temp :",currectTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_home_wooden_voice_mode);

        fStore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        currentTime = df.format(c.getTime());
        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        User_Home_Wooden_Voice_Mode.this.registerReceiver(broadcastreceiver,intentfilter);

        textofspeech = (TextView) findViewById(R.id.textofspeech);
        speechtotext = (TextView) findViewById(R.id.speechthetext);
        user_layout = (ImageView) findViewById(R.id.user_layout);
        textofspeech.setText("To activate sleep mode speak one   ," +
                "  To activate move out speak two  ,  " +
                " To activate automatic mode speak three  ," +
                " To contact with codinator speak four");

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
                        speechlayout.setEnabled(true);
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
                    speechtotext.setText(matches.get(0));
                speaktotext = speechtotext.getText().toString();
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

        speechlayout = (ConstraintLayout) findViewById(R.id.speechlayout);

        speechlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                textToSpeech.stop();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        speechtotext.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        speechtotext.setText("");
                        speechtotext.setHint("Listening...");

                        break;
                }
                return false;
            }
        });
        user_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                textToSpeech.stop();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        speechtotext.setHint("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        speechtotext.setText("");
                        speechtotext.setHint("Listening...");

                        break;
                }
                return false;
            }
        });


    }

    private void speaktext(String text) {
        String test = text.toString();
        String numberone = "1";
        String numbertwo = "2";
        String numberthree = "3";
        String numberfour = "4";


        if (test.equals(numberone)) {
//            textofspeech.setText("Sleep Mode Activated");
//            textspeak();
            Intent intent = new Intent(User_Home_Wooden_Voice_Mode.this, User_Sleep_Mode_Voice_Mode.class);
            startActivity(intent);
        } else if (test.equals(numbertwo)) {
//            textofspeech.setText("Move Out Mode Activated");
//            textspeak();
            Intent intent = new Intent(User_Home_Wooden_Voice_Mode.this, User_Move_Out_Mode_Voice_Mode.class);
            startActivity(intent);
        } else if (test.equals(numberthree)) {
//            textofspeech.setText("Automatic Mode Activated");
//            textspeak();
            Intent intent = new Intent(User_Home_Wooden_Voice_Mode.this, User_Automatic_Mode_Voice_Mode.class);
            startActivity(intent);
        } else if (test.equals(numberfour)) {
//            textofspeech.setText("Manual Mode Activated");
//            textspeak();
            Intent intent = new Intent(User_Home_Wooden_Voice_Mode.this, User_Sleep_Mode_Voice_Mode.class);
            startActivity(intent);
        } else {
            textofspeech.setText("Sorry to understand your voice ,      Please Speck Again !");
            textspeak();
        }
    }

    private void textspeak() {
                String text = textofspeech.getText().toString();
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

    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            batteryTemp = (float) (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)) / 10;

            currectTemperature = currentBatterytemp + " " + batteryTemp + " " + (char) 0x00B0 + "C";

            GlobalVariables globalVariable = (GlobalVariables) getApplication();
            globalVariable.setCurrentTemperature(batteryTemp);
            if (batteryTemp <= 25) {
                globalVariable.setSleep_mode_bedroom_ac("OFF");
                globalVariable.setSleep_mode_bedroom_ac_temperature("0" + (char) 0x00B0 + "C");
                globalVariable.setSleep_mode_bedroom_heating("ON");
                globalVariable.setSleep_mode_bedroom_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setManual_mode_bedroom_ac("OFF");
                globalVariable.setManual_mode_bedroom_ac_temperature("0" + (char) 0x00B0 + "C");
                globalVariable.setManual_mode_bedroom_heating("ON");
                globalVariable.setManual_mode_bedroom_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setAutomatic_mode_bedroom_ac("OFF");
                globalVariable.setAutomatic_mode_bedroom_ac_temperature("0" + (char) 0x00B0 + "C");
                globalVariable.setAutomatic_mode_bedroom_heating("ON");
                globalVariable.setAutomatic_mode_bedroom_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setSleep_mode_livingroom_ac("OFF");
                globalVariable.setSleep_mode_livingroom_ac_temperature("0" + (char) 0x00B0 + "C");
                globalVariable.setSleep_mode_livingroom_heating("ON");
                globalVariable.setSleep_mode_livingroom_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setManual_mode_livingroom_ac("OFF");
                globalVariable.setManual_mode_livingroom_ac_temperature("0" + (char) 0x00B0 + "C");
                globalVariable.setManual_mode_livingroom_heating("ON");
                globalVariable.setManual_mode_livingroom_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setAutomatic_mode_livingroom_ac("OFF");
                globalVariable.setAutomatic_mode_livingroom_ac_temperature("0" + (char) 0x00B0 + "C");
                globalVariable.setAutomatic_mode_livingroom_heating("ON");
                globalVariable.setAutomatic_mode_livingroom_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setSleep_mode_kitchen_ac("OFF");
                globalVariable.setSleep_mode_kitchen_ac_temperature("0" + (char) 0x00B0 + "C");
                globalVariable.setSleep_mode_kitchen_heating("ON");
                globalVariable.setSleep_mode_kitchen_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setManual_mode_kitchen_ac("OFF");
                globalVariable.setManual_mode_kitchen_ac_temperature("0" + (char) 0x00B0 + "C");
                globalVariable.setManual_mode_kitchen_heating("ON");
                globalVariable.setManual_mode_kitchen_ac_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setAutomatic_mode_kitchen_ac("OFF");
                globalVariable.setAutomatic_mode_kitchen_ac_temperature("0" + (char) 0x00B0 + "C");
                globalVariable.setAutomatic_mode_kitchen_heating("ON");
                globalVariable.setAutomatic_mode_kitchen_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setSleep_mode_wc_heating("ON");
                globalVariable.setSleep_mode_wc_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setManual_mode_wc_heating("ON");
                globalVariable.setManual_mode_wc_heating_temperature("30 " + (char) 0x00B0 + "C");

                globalVariable.setAutomatic_mode_wc_heating("ON");
                globalVariable.setAutomatic_mode_wc_heating_temperature("30 " + (char) 0x00B0 + "C");
            }
            if (batteryTemp >= 25) {
                globalVariable.setSleep_mode_bedroom_ac("ON");
                globalVariable.setSleep_mode_bedroom_ac_temperature("20" + (char) 0x00B0 + "C");
                globalVariable.setSleep_mode_bedroom_heating("OFF");
                globalVariable.setSleep_mode_bedroom_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setManual_mode_bedroom_ac("ON");
                globalVariable.setManual_mode_bedroom_ac_temperature("20" + (char) 0x00B0 + "C");
                globalVariable.setManual_mode_bedroom_heating("OFF");
                globalVariable.setManual_mode_bedroom_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setAutomatic_mode_bedroom_ac("ON");
                globalVariable.setAutomatic_mode_bedroom_ac_temperature("20" + (char) 0x00B0 + "C");
                globalVariable.setAutomatic_mode_bedroom_heating("OFF");
                globalVariable.setAutomatic_mode_bedroom_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setSleep_mode_livingroom_ac("ON");
                globalVariable.setSleep_mode_livingroom_ac_temperature("20" + (char) 0x00B0 + "C");
                globalVariable.setSleep_mode_livingroom_heating("OFF");
                globalVariable.setSleep_mode_livingroom_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setManual_mode_livingroom_ac("ON");
                globalVariable.setManual_mode_livingroom_ac_temperature("20" + (char) 0x00B0 + "C");
                globalVariable.setManual_mode_livingroom_heating("OFF");
                globalVariable.setManual_mode_livingroom_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setAutomatic_mode_livingroom_ac("ON");
                globalVariable.setAutomatic_mode_livingroom_ac_temperature("20" + (char) 0x00B0 + "C");
                globalVariable.setAutomatic_mode_livingroom_heating("OFF");
                globalVariable.setAutomatic_mode_livingroom_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setSleep_mode_kitchen_ac("ON");
                globalVariable.setSleep_mode_kitchen_ac_temperature("20" + (char) 0x00B0 + "C");
                globalVariable.setSleep_mode_kitchen_heating("OFF");
                globalVariable.setSleep_mode_kitchen_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setManual_mode_kitchen_ac("ON");
                globalVariable.setManual_mode_kitchen_ac_temperature("20" + (char) 0x00B0 + "C");
                globalVariable.setManual_mode_kitchen_heating("OFF");
                globalVariable.setManual_mode_kitchen_ac_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setAutomatic_mode_kitchen_ac("ON");
                globalVariable.setAutomatic_mode_kitchen_ac_temperature("20" + (char) 0x00B0 + "C");
                globalVariable.setAutomatic_mode_kitchen_heating("OFF");
                globalVariable.setAutomatic_mode_kitchen_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setSleep_mode_wc_heating("OFF");
                globalVariable.setSleep_mode_wc_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setManual_mode_wc_heating("OFF");
                globalVariable.setManual_mode_wc_heating_temperature("0 " + (char) 0x00B0 + "C");

                globalVariable.setAutomatic_mode_wc_heating("OFF");
                globalVariable.setAutomatic_mode_wc_heating_temperature("0 " + (char) 0x00B0 + "C");
            }
        }
    };
}

