package com.example.smart_home.Users_Modes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User_Sleep_Mode_Colored extends AppCompatActivity {
    String UserId;
    private static final String TAG = "MyActivity";

    Button bedroom_light_on,bedroom_light_off,wc_light_on,wc_light_off,heating_on,heating_off,
    cooling_on,cooling_off,nightlamp_on,nightlamp_off;

    String on ="ON",off="OFF",open="OPEN",close="CLOSE";

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;
    LinearLayout one,two,three,four,five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sleep_mode_wooden);
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

        one = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_one);
        two = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_two);
        three = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_three);
        four = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_four);
        five = (LinearLayout) findViewById(R.id.layout_sleep_mode_card_five);

        String a= globalVariable.getSleep_mode_bedroom_light();
        String b = globalVariable.getSleep_mode_wc_light();
        String c = globalVariable.getSleep_mode_bedroom_heating();
        String d = globalVariable.getSleep_mode_bedroom_ac();
        String e = globalVariable.getSleep_mode_bedroom_nightlamp();

        if(a.equals(on)){
            one.setBackgroundColor(getColor(R.color.greentwo));
            bedroom_light_on.setVisibility(View.GONE);
            bedroom_light_off.setVisibility(View.VISIBLE);
        }else{
            bedroom_light_on.setVisibility(View.VISIBLE);
            bedroom_light_off.setVisibility(View.GONE);
        }
        if(b.equals(on)){
            two.setBackgroundColor(getColor(R.color.greentwo));

            wc_light_on.setVisibility(View.GONE);
            wc_light_off.setVisibility(View.VISIBLE);
        }else{
            wc_light_on.setVisibility(View.VISIBLE);
            wc_light_off.setVisibility(View.GONE);
        }
        if(c.equals(on)){
            three.setBackgroundColor(getColor(R.color.greentwo));

            heating_on.setVisibility(View.GONE);
            heating_off.setVisibility(View.VISIBLE);
        }
        else
        {
            heating_on.setVisibility(View.VISIBLE);
            heating_off.setVisibility(View.GONE);
        }
        if(d.equals(on)){
            four.setBackgroundColor(getColor(R.color.greentwo));

            cooling_on.setVisibility(View.GONE);
            cooling_off.setVisibility(View.VISIBLE);
        }
        else {
            cooling_on.setVisibility(View.VISIBLE);
            cooling_off.setVisibility(View.GONE);
        }
        if(e.equals(on)){
            five.setBackgroundColor(getColor(R.color.greentwo));

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
                one.setBackgroundColor(getColor(R.color.greentwo));

                bedroom_light_on.setVisibility(View.GONE);
                bedroom_light_off.setVisibility(View.VISIBLE);
            }
        });
        bedroom_light_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                one.setBackgroundColor(getColor(android.R.color.transparent));

                bedroom_light_on.setVisibility(View.VISIBLE);
                bedroom_light_off.setVisibility(View.GONE);
            }
        });
        wc_light_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setBackgroundColor(getColor(R.color.greentwo));

                wc_light_on.setVisibility(View.GONE);
                wc_light_off.setVisibility(View.VISIBLE);
            }
        });
        wc_light_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                two.setBackgroundColor(getColor(android.R.color.transparent));

                wc_light_on.setVisibility(View.VISIBLE);
                wc_light_off.setVisibility(View.GONE);
            }
        });
        heating_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundColor(getColor(R.color.greentwo));

                heating_on.setVisibility(View.GONE);
                heating_off.setVisibility(View.VISIBLE);
            }
        });
        heating_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundColor(getColor(android.R.color.transparent));

                heating_on.setVisibility(View.VISIBLE);
                heating_off.setVisibility(View.GONE);
            }
        });
        cooling_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                four.setBackgroundColor(getColor(R.color.greentwo));

                cooling_on.setVisibility(View.GONE);
                cooling_off.setVisibility(View.VISIBLE);
            }
        });
        cooling_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                four.setBackgroundColor(getColor(android.R.color.transparent));

                cooling_on.setVisibility(View.VISIBLE);
                cooling_off.setVisibility(View.GONE);
            }
        });
        nightlamp_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                five.setBackgroundColor(getColor(R.color.greentwo));
                nightlamp_on.setVisibility(View.GONE);
                nightlamp_off.setVisibility(View.VISIBLE);
            }
        });
        nightlamp_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                five.setBackgroundColor(getColor(android.R.color.transparent));
                nightlamp_on.setVisibility(View.VISIBLE);
                nightlamp_off.setVisibility(View.GONE);
            }
        });
    }

}
