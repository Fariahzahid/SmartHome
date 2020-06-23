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
import com.example.smart_home.Users_Modes.User_Automatic_Mode_Colored;
import com.example.smart_home.Users_Modes.User_Manual_Mode_Colored;
import com.example.smart_home.Users_Modes.User_Move_Out_Mode_Colored;
import com.example.smart_home.Users_Modes.User_Sleep_Mode_Colored;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Locale;

public class User_Sleep_Mode_Voice_Mode extends AppCompatActivity {
    String UserId;
    private static final String TAG = "MyActivity";

    TextView bedroom_light_on,bedroom_light_off,wc_light_on,wc_light_off,heating_on,heating_off,
    cooling_on,cooling_off,nightlamp_on,nightlamp_off;

    String on ="ON",off="OFF",open="OPEN",close="CLOSE";
    TextView texttovoice,voicetotext;

    ScrollView user_sleep_mode_layout;
    TextToSpeech textToSpeech;
    String speaktotext;
    ImageView user_layout;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sleepmode_voice_mode_wooden);

        user_sleep_mode_layout = (ScrollView) findViewById(R.id.user_sleep_mode_layout);

        texttovoice = (TextView) findViewById(R.id.texttovoice);
        voicetotext = (TextView) findViewById(R.id.voicetotext);
        bedroom_light_on = (TextView) findViewById(R.id.user_sleepmode_bedroom_bulb_on_button);
        bedroom_light_off = (TextView) findViewById(R.id.user_sleepmode_bedroom_bulb_off_button);
        wc_light_on = (TextView) findViewById(R.id.user_sleepmode_wc_bulb_on_button);
        wc_light_off = (TextView) findViewById(R.id.user_sleepmode_wc_bulb_off_button);
        heating_on = (TextView) findViewById(R.id.user_sleep_mode_heating_on_button);
        heating_off = (TextView) findViewById(R.id.user_sleep_mode_heating_off_button);
        cooling_on = (TextView) findViewById(R.id.user_sleep_mode_ac_on_button);
        cooling_off = (TextView) findViewById(R.id.user_sleep_mode_ac_off_button);
        nightlamp_on = (TextView) findViewById(R.id.user_sleep_mode_nl_on_button);
        nightlamp_off = (TextView) findViewById(R.id.user_sleep_mode_nl_off_button);

        texttovoice.setText("Speak 1 for Bedroom Bulb On, Speak 2 for Toilet Bulb On,Speak 3 for heating On," +
                "Speak 4 for cooling On, Speak 5 for night lamp on");
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
                        user_sleep_mode_layout.setEnabled(true);
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

        getValues();

        checkPermission();

        user_sleep_mode_layout.setOnTouchListener(new View.OnTouchListener() {
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
        String numberthreeON = "three on";
        String numberthreethreeON = "3 on";
        String numberfourON = "four on";
        String numberfourfourON = "4 on";
        String numberfiveON = "five on";
        String numberfivefiveON = "5 on";

        String numberoneOff = "one of";
        String numberoneoneOff = "1 of";
        String numbertwoOff = "two of";
        String numbertwotwoOff = "2 of";
        String numberthreeOff = "three of";
        String numberthreethreeOff = "3 of";
        String numberfourOff = "four of";
        String numberfourfourOff = "4 of";
        String numberfiveOff = "five of";
        String numberfivefiveOff = "5 of";

        String numberoneOf = "one of";
        String numberoneoneOf = "1 of";
        String numbertwoOf = "two of";
        String numbertwotwoOf = "2 of";
        String numberthreeOf = "three of";
        String numberthreethreeOf = "3 of";
        String numberfourOf = "four of";
        String numberfourfourOf = "4 of";
        String numberfiveOf = "five of";
        String numberfivefiveOf = "5 of";

        if (test.equals(numberoneON) || text.equals(numberoneoneON)) {
                    bedroom_light_on.setVisibility(View.GONE);
                    bedroom_light_off.setVisibility(View.VISIBLE);
        } else if (test.equals(numberoneOff) || test.equals(numberoneoneOff) || test.equals(numberoneOf) || test.equals(numberoneoneOf)) {
            bedroom_light_on.setVisibility(View.VISIBLE);
            bedroom_light_off.setVisibility(View.GONE);
        } else if  (test.equals(numbertwoON) || text.equals(numbertwotwoON)) {
            wc_light_on.setVisibility(View.GONE);
            wc_light_off.setVisibility(View.VISIBLE);
        } else if (test.equals(numbertwoOff) || test.equals(numbertwotwoOff) || test.equals(numbertwoOf) || test.equals(numbertwotwoOf)) {
            wc_light_on.setVisibility(View.VISIBLE);
            wc_light_off.setVisibility(View.GONE);
        } else if  (test.equals(numberthreeON) || text.equals(numberthreethreeON)) {
            heating_on.setVisibility(View.GONE);
            heating_off.setVisibility(View.VISIBLE);
        } else if (test.equals(numberthreeOff) || test.equals(numberthreethreeOff) || test.equals(numberthreeOf) || test.equals(numberthreethreeOf)) {
            heating_on.setVisibility(View.VISIBLE);
            heating_off.setVisibility(View.GONE);
        } else if  (test.equals(numberfourON) || text.equals(numberfourfourON)) {
            cooling_on.setVisibility(View.GONE);
            cooling_off.setVisibility(View.VISIBLE);
        } else if (test.equals(numberfourOff) || test.equals(numberfourfourOff) || test.equals(numberfourOf) || test.equals(numberfourfourOf)) {
            cooling_on.setVisibility(View.VISIBLE);
            cooling_off.setVisibility(View.GONE);
        } else if  (test.equals(numberfiveON) || text.equals(numberfivefiveON)) {
            nightlamp_on.setVisibility(View.GONE);
            nightlamp_off.setVisibility(View.VISIBLE);
        } else if (test.equals(numberfiveOff) || test.equals(numberfivefiveOff) || test.equals(numberfiveOf) || test.equals(numberfivefiveOf)) {
            nightlamp_on.setVisibility(View.VISIBLE);
            nightlamp_off.setVisibility(View.GONE);
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
    public void getValues(){
        GlobalVariables globalVariable=(GlobalVariables)getApplication();

        String a= globalVariable.getSleep_mode_bedroom_light();
        String b = globalVariable.getSleep_mode_wc_light();
        String c = globalVariable.getSleep_mode_bedroom_heating();
        String d = globalVariable.getSleep_mode_bedroom_ac();
        String e = globalVariable.getSleep_mode_bedroom_nightlamp();

        if(a.equals(on)){
            bedroom_light_on.setVisibility(View.GONE);
            bedroom_light_off.setVisibility(View.VISIBLE);
        }else{
            bedroom_light_on.setVisibility(View.VISIBLE);
            bedroom_light_off.setVisibility(View.GONE);
        }
        if(b.equals(on)){
            wc_light_on.setVisibility(View.GONE);
            wc_light_off.setVisibility(View.VISIBLE);
        }else{
            wc_light_on.setVisibility(View.VISIBLE);
            wc_light_off.setVisibility(View.GONE);
        }
        if(c.equals(on)){
            heating_on.setVisibility(View.GONE);
            heating_off.setVisibility(View.VISIBLE);
        }
        else
        {
            heating_on.setVisibility(View.VISIBLE);
            heating_off.setVisibility(View.GONE);
        }
        if(d.equals(on)){
            cooling_on.setVisibility(View.GONE);
            cooling_off.setVisibility(View.VISIBLE);
        }
        else {
            cooling_on.setVisibility(View.VISIBLE);
            cooling_off.setVisibility(View.GONE);
        }
        if(e.equals(on)){
            nightlamp_on.setVisibility(View.GONE);
            nightlamp_off.setVisibility(View.VISIBLE);
        }
        else{
            nightlamp_on.setVisibility(View.VISIBLE);
            nightlamp_off.setVisibility(View.GONE);
        }

//        bedroom_light_on.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bedroom_light_on.setVisibility(View.GONE);
//                bedroom_light_off.setVisibility(View.VISIBLE);
//            }
//        });
//        bedroom_light_off.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bedroom_light_on.setVisibility(View.VISIBLE);
//                bedroom_light_off.setVisibility(View.GONE);
//            }
//        });
//        wc_light_on.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wc_light_on.setVisibility(View.GONE);
//                wc_light_off.setVisibility(View.VISIBLE);
//            }
//        });
//        wc_light_off.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wc_light_on.setVisibility(View.VISIBLE);
//                wc_light_off.setVisibility(View.GONE);
//            }
//        });
//        heating_on.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                heating_on.setVisibility(View.GONE);
//                heating_off.setVisibility(View.VISIBLE);
//            }
//        });
//        heating_off.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                heating_on.setVisibility(View.VISIBLE);
//                heating_off.setVisibility(View.GONE);
//            }
//        });
//        cooling_on.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cooling_on.setVisibility(View.GONE);
//                cooling_off.setVisibility(View.VISIBLE);
//            }
//        });
//        cooling_off.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cooling_on.setVisibility(View.VISIBLE);
//                cooling_off.setVisibility(View.GONE);
//            }
//        });
//        nightlamp_on.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                nightlamp_on.setVisibility(View.GONE);
//                nightlamp_off.setVisibility(View.VISIBLE);
//            }
//        });
//        nightlamp_off.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                nightlamp_on.setVisibility(View.VISIBLE);
//                nightlamp_off.setVisibility(View.GONE);
//            }
//        });
    }

}
