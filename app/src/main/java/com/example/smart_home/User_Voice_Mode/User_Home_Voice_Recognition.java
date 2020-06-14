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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.smart_home.R;
import com.example.smart_home.Users_Modes.User_Automatic_Mode_Colored;
import com.example.smart_home.Users_Modes.User_Manual_Mode_Colored;
import com.example.smart_home.Users_Modes.User_Move_Out_Mode_Colored;
import com.example.smart_home.Users_Modes.User_Sleep_Mode_Colored;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Locale;

public class User_Home_Voice_Recognition  extends AppCompatActivity {
    ScrollView speechlayout;
    LinearLayout one,two,three,four,five,parentlayout;
    TextToSpeech textToSpeech;
    private TextView textofspeech,speechtotext;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String speaktotext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_home_voice_mode);

        fStore = FirebaseFirestore.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();


        textofspeech = (TextView) findViewById(R.id.textofspeech);
        speechtotext = (TextView) findViewById(R.id.speechthetext);
        textofspeech.setText("To activate sleep mode press or speak one   ,  To activate move out press or speak two  ,   To activate automatic mode press or speak three  , To activate manual mode press or speak four ,   To contact with codinator press or speak five");

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

        speechlayout = (ScrollView) findViewById(R.id.speechlayout);
        one = (LinearLayout) findViewById(R.id.layoutone);
        two = (LinearLayout) findViewById(R.id.layouttwo);
        three = (LinearLayout) findViewById(R.id.layoutthree);
        four = (LinearLayout) findViewById(R.id.layoutfour);
        five = (LinearLayout) findViewById(R.id.layoutfive);
        parentlayout = (LinearLayout) findViewById(R.id.linearLayout14);

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
        parentlayout.setOnTouchListener(new View.OnTouchListener() {
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
        String numberthree ="3";
        String numberfour = "4";
        String numberfive = "5";

        if(test.equals(numberone)) {
            Intent intent = new Intent(User_Home_Voice_Recognition.this, User_Sleep_Mode_Colored.class);
            startActivity(intent);
        }else if(test.equals(numbertwo)){
            Intent intent = new Intent(User_Home_Voice_Recognition.this, User_Move_Out_Mode_Colored.class);
            startActivity(intent);
        }else if(test.equals(numberthree)){
            Intent intent = new Intent(User_Home_Voice_Recognition.this, User_Automatic_Mode_Colored.class);
            startActivity(intent);
        }else if(test.equals(numberfour)){
            Intent intent = new Intent(User_Home_Voice_Recognition.this, User_Manual_Mode_Colored.class);
            startActivity(intent);
        }else if(test.equals(numberfive)){
            Intent intent = new Intent(User_Home_Voice_Recognition.this, User_Sleep_Mode_Colored.class);
            startActivity(intent);
        }else {
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
}
