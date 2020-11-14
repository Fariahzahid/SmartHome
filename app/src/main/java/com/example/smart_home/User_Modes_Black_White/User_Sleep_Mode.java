package com.example.smart_home.User_Modes_Black_White;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;

import com.google.firebase.firestore.FirebaseFirestore;


public class User_Sleep_Mode extends AppCompatActivity {
    String UserId;
    private static final String TAG = "MyActivity";

    Button bedroom_light_on,bedroom_light_off,wc_light_on,wc_light_off,heating_on,heating_off,
            cooling_on,cooling_off,nightlamp_on,nightlamp_off;

    String on ="ON",off="OFF",open="OPEN",close="CLOSE";

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sleep_mode);
        getValues();
    }

    public void getValues(){
        GlobalVariables globalVariable=(GlobalVariables)getApplication();
        bedroom_light_on = (Button) findViewById(R.id.user_sleepmode_bedroom_bulb_on_button);
        bedroom_light_off = (Button) findViewById(R.id.user_sleepmode_bedroom_bulb_off_button);
        wc_light_on = (Button) findViewById(R.id.user_sleepmode_wc_bulb_on_button);
        wc_light_off = (Button) findViewById(R.id.user_sleepmode_wc_bulb_off_button);
        heating_on = (Button) findViewById(R.id.user_sleep_mode_heating_on_button);
        heating_off = (Button) findViewById(R.id.user_sleep_mode_heating_off_button);
        cooling_on = (Button) findViewById(R.id.user_sleep_mode_ac_on_button);
        cooling_off = (Button) findViewById(R.id.user_sleep_mode_ac_off_button);
        nightlamp_on = (Button) findViewById(R.id.user_sleep_mode_nl_on_button);
        nightlamp_off = (Button) findViewById(R.id.user_sleep_mode_nl_off_button);

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

        bedroom_light_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bedroom_light_on.setVisibility(View.GONE);
                bedroom_light_off.setVisibility(View.VISIBLE);
            }
        });
        bedroom_light_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bedroom_light_on.setVisibility(View.VISIBLE);
                bedroom_light_off.setVisibility(View.GONE);
            }
        });
        wc_light_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wc_light_on.setVisibility(View.GONE);
                wc_light_off.setVisibility(View.VISIBLE);
            }
        });
        wc_light_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wc_light_on.setVisibility(View.VISIBLE);
                wc_light_off.setVisibility(View.GONE);
            }
        });
        heating_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heating_on.setVisibility(View.GONE);
                heating_off.setVisibility(View.VISIBLE);
            }
        });
        heating_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heating_on.setVisibility(View.VISIBLE);
                heating_off.setVisibility(View.GONE);
            }
        });
        cooling_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cooling_on.setVisibility(View.GONE);
                cooling_off.setVisibility(View.VISIBLE);
            }
        });
        cooling_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cooling_on.setVisibility(View.VISIBLE);
                cooling_off.setVisibility(View.GONE);
            }
        });
        nightlamp_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nightlamp_on.setVisibility(View.GONE);
                nightlamp_off.setVisibility(View.VISIBLE);
            }
        });
        nightlamp_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nightlamp_on.setVisibility(View.VISIBLE);
                nightlamp_off.setVisibility(View.GONE);
            }
        });
    }

}