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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class User_Automatic_Mode_Voice_Mode extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    TextView progress;

    TextView texttovoice,voicetotext;
    TextToSpeech textToSpeech;
    LinearLayout automaticLayout;
    String speaktotext;


    String Stormy = "Storm",Rainy = "Rain",Clouds ="Clouds";
    Button  manualmode_wc_bulb_on, manualmode_wc_bulb_off,
            manualmode_bedroom_bulb_on, manualmode_bedroom_bulb_off,
            manualmode_bedroom_blinds_on, manualmode_bedroom_blinds_off,
            manualmode_bedroom_heating_on,manualmode_bedroom_heating_off,
            manualmode_bedroom_cooling_on,manualmode_bedroom_cooling_off;
    String currentTime,sunrise,sunset,weather,windspeed;
    int HH,mm,sunsetone,sunsettwo,sunriseone,sunrisetwo,nightlamponhour,nightlamponmin
            ,nightlampoffhour,nightlampoffmin;
    String on ="ON",off="OFF",open="OPEN",close="CLOSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_automatic_mode_wooden_voice);


        texttovoice = (TextView) findViewById(R.id.texttovoice);
        voicetotext = (TextView) findViewById(R.id.voicetotext);
        automaticLayout = findViewById(R.id.automaticLayout);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        currentTime = df.format(c.getTime());

        final GlobalVariables globalVariable=(GlobalVariables)getApplication();
        sunrise = globalVariable.getSunrise();
        sunset = globalVariable.getSunset();
        weather = globalVariable.getWeather();
        windspeed = globalVariable.getWindspeed();

        String[] currenttimeArray = currentTime.split(":");
        HH = Integer.parseInt(currenttimeArray[0]);
        mm = Integer.parseInt(currenttimeArray[1]);

        String[] sunsettimearray = sunset.split(":");
        sunsetone = Integer.parseInt(sunsettimearray[0]);
        sunsettwo = Integer.parseInt(sunsettimearray[1]);

        String[] sunrisetimearray = sunrise.split(":");
        sunriseone = Integer.parseInt(sunrisetimearray[0]);
        sunrisetwo = Integer.parseInt(sunrisetimearray[1]);

        progress = (TextView) findViewById(R.id.progress);

        manualmode_wc_bulb_on = (Button) findViewById(R.id.manualmode_wc_bulb_on);
        manualmode_wc_bulb_off = (Button) findViewById(R.id.manualmode_wc_bulb_off);
        manualmode_bedroom_bulb_on = (Button) findViewById(R.id.manualmode_bedroom_bulb_on);
        manualmode_bedroom_bulb_off = (Button) findViewById(R.id.manualmode_bedroom_bulb_off);
        manualmode_bedroom_blinds_on = (Button) findViewById(R.id.manualmode_bedroom_blinds_on);
        manualmode_bedroom_blinds_off = (Button) findViewById(R.id.manualmode_bedroom_blinds_off);
        manualmode_bedroom_heating_on = (Button) findViewById(R.id.manualmode_bedroom_heating_on);
        manualmode_bedroom_heating_off = (Button) findViewById(R.id.manualmode_bedroom_heating_off);
        manualmode_bedroom_cooling_on = (Button) findViewById(R.id.manualmode_bedroom_cooling_on);
        manualmode_bedroom_cooling_off = (Button) findViewById(R.id.manualmode_bedroom_cooling_off);


        if(HH >= sunriseone && HH <= sunsetone ){
            if(mm >= sunrisetwo && mm <= sunsettwo){
                if(weather!=Stormy && weather != Clouds && weather != Rainy){
                    manualmode_bedroom_blinds_on.setVisibility(View.GONE);
                    manualmode_bedroom_blinds_off.setVisibility(View.VISIBLE);
                    Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Blinds On", Toast.LENGTH_SHORT).show();
                }

            }
        }else
        {
            manualmode_bedroom_blinds_on.setVisibility(View.VISIBLE);
            manualmode_bedroom_blinds_off.setVisibility(View.GONE);
            Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Blinds Off", Toast.LENGTH_SHORT).show();
        }

        String nightlampOn = globalVariable.getAutomatic_mode_bedroom_nightlampon();
        String[] nightlampOnArray = nightlampOn.split(":");
        nightlamponhour = Integer.parseInt(nightlampOnArray[0]);
        nightlamponmin = Integer.parseInt(nightlampOnArray[1]);

        String nightLampOff = globalVariable.getAutomatic_mode_bedroom_nightlampoff();
        String[] nightLampOffArray = nightLampOff.split(":");
        nightlampoffhour = Integer.parseInt(nightLampOffArray[0]);
        nightlampoffmin = Integer.parseInt(nightLampOffArray[1]);

        String bulbOn = globalVariable.getAutomatic_mode_bedroom_bulbon();
        String[] bulbOnArray = bulbOn.split(":");
        int bulbonhour = Integer.parseInt(bulbOnArray[0]);
        int bulbonmin = Integer.parseInt(bulbOnArray[1]);

        String bulboff = globalVariable.getAutomatic_mode_bedroom_bulboff();
        String[] bulboffArray = bulboff.split(":");
        int bulboffhour = Integer.parseInt(bulboffArray[0]);
        int bulboffmin = Integer.parseInt(bulboffArray[1]);


        if (HH > bulbonhour && HH < bulboffhour){
            if(mm >bulbonmin && mm <bulboffmin ){

                manualmode_bedroom_bulb_on.setVisibility(View.GONE);
                manualmode_bedroom_bulb_off.setVisibility(View.VISIBLE);
                Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Bulb On", Toast.LENGTH_SHORT).show();
            }
        }

        String a = globalVariable.getSleep_mode_bedroom_heating();
        String d = globalVariable.getSleep_mode_bedroom_ac();

        if(a.equals(on)){
            manualmode_bedroom_heating_on.setVisibility(View.GONE);
            manualmode_bedroom_heating_off.setVisibility(View.VISIBLE);
        }
        else
        {
            manualmode_bedroom_heating_on.setVisibility(View.VISIBLE);
            manualmode_bedroom_heating_off.setVisibility(View.GONE);
        }
        if(d.equals(on)){
            manualmode_bedroom_cooling_on.setVisibility(View.GONE);
            manualmode_bedroom_cooling_off.setVisibility(View.VISIBLE);
        }
        else {
            manualmode_bedroom_cooling_on.setVisibility(View.VISIBLE);
            manualmode_bedroom_cooling_off.setVisibility(View.GONE);
        }

        //TOILET BULB ON/OFF
        manualmode_wc_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_wc_bulb_on.setVisibility(View.GONE);
                manualmode_wc_bulb_off.setVisibility(View.VISIBLE);

            }
        });
        manualmode_wc_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_wc_bulb_on.setVisibility(View.VISIBLE);
                manualmode_wc_bulb_off.setVisibility(View.GONE);
            }
        });

        //BEDROOM LIGHTS ON/OFF
        manualmode_bedroom_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_bulb_on.setVisibility(View.GONE);
                manualmode_bedroom_bulb_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_bedroom_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_bulb_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_bulb_off.setVisibility(View.GONE);
            }
        });

        //BEDROOM BLINDS ON/OFF
        manualmode_bedroom_blinds_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_blinds_on.setVisibility(View.GONE);
                manualmode_bedroom_blinds_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_bedroom_blinds_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_blinds_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_blinds_off.setVisibility(View.GONE);
            }
        });

        texttovoice.setText("Speak 1 On or 1 Off for Bedroom Bulb Status, " +
                "Speak 2 On or 2 Off for Toilet Bulb Status,");
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

        manualmode_wc_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        manualmode_wc_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //BEDROOM LIGHTS ON/OFF
        manualmode_bedroom_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        manualmode_bedroom_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (test.equals(numberoneON) || text.equals(numberoneoneON)) {
            manualmode_wc_bulb_on.setVisibility(View.GONE);
            manualmode_wc_bulb_off.setVisibility(View.VISIBLE);
        } else if (test.equals(numberoneOff) || test.equals(numberoneoneOff) || test.equals(numberoneOf) || test.equals(numberoneoneOf)) {
            manualmode_wc_bulb_on.setVisibility(View.VISIBLE);
            manualmode_wc_bulb_off.setVisibility(View.GONE);
        } else if  (test.equals(numbertwoON) || text.equals(numbertwotwoON)) {
            manualmode_bedroom_bulb_on.setVisibility(View.GONE);
            manualmode_bedroom_bulb_off.setVisibility(View.VISIBLE);
        } else if (test.equals(numbertwoOff) || test.equals(numbertwotwoOff) || test.equals(numbertwoOf) || test.equals(numbertwotwoOf)) {
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
}

