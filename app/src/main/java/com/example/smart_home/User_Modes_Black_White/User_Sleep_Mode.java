package com.example.smart_home.User_Modes_Black_White;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class User_Sleep_Mode extends AppCompatActivity {
    String UserId;
    private static final String TAG = "MyActivity";

    Button bedroom_light_on,bedroom_light_off,wc_light_on,wc_light_off,heating_on,heating_off,
            cooling_on,cooling_off,nightlamp_on,nightlamp_off;

    String on ="ON",off="OFF",open="OPEN",close="CLOSE";

    LinearLayout one,two,three,four,five;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sleep_mode);
        getTemperature();

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
            one.setBackgroundColor(getColor(R.color.greyshade2));
            bedroom_light_on.setVisibility(View.GONE);
            bedroom_light_off.setVisibility(View.VISIBLE);
        }else{
            bedroom_light_on.setVisibility(View.VISIBLE);
            bedroom_light_off.setVisibility(View.GONE);
        }
        if(b.equals(on)){
            two.setBackgroundColor(getColor(R.color.greyshade2));

            wc_light_on.setVisibility(View.GONE);
            wc_light_off.setVisibility(View.VISIBLE);
        }else{
            wc_light_on.setVisibility(View.VISIBLE);
            wc_light_off.setVisibility(View.GONE);
        }
        if(c.equals(on)){
            three.setBackgroundColor(getColor(R.color.greyshade2));

            heating_on.setVisibility(View.GONE);
            heating_off.setVisibility(View.VISIBLE);
        }
        else
        {
            heating_on.setVisibility(View.VISIBLE);
            heating_off.setVisibility(View.GONE);
        }
        if(d.equals(on)){
            four.setBackgroundColor(getColor(R.color.greyshade2));

            cooling_on.setVisibility(View.GONE);
            cooling_off.setVisibility(View.VISIBLE);
        }
        else {
            cooling_on.setVisibility(View.VISIBLE);
            cooling_off.setVisibility(View.GONE);
        }
        if(e.equals(on)){
            five.setBackgroundColor(getColor(R.color.greyshade2));

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
                one.setBackgroundColor(getColor(R.color.greyshade2));

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
                two.setBackgroundColor(getColor(R.color.greyshade2));

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
                three.setBackgroundColor(getColor(R.color.greyshade2));

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
                four.setBackgroundColor(getColor(R.color.greyshade2));

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
                five.setBackgroundColor(getColor(R.color.greyshade2));
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
}
