package com.example.smart_home.User_Modes_Black_White;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.example.smart_home.Users_Modes.User_Automatic_Mode_Colored;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User_Automatic_Mode extends AppCompatActivity {
    private static final String TAG = "MyActivity";

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
    int HH,mm,sunsetone,sunsettwo,sunriseone,sunrisetwo,nightlamponhour,nightlamponmin
            ,nightlampoffhour,nightlampoffmin;
    String on ="ON",off="OFF",open="OPEN",close="CLOSE";

    LinearLayout bedroombulblayout, livingroombulblayout, kitchenbulblayout, bedroomnightlamplayout,
            livingroomtablelamplayout,livingroomtelevisionlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_automatic_mode);


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


        if(HH > sunriseone && HH < sunsetone ){
            if(mm > sunrisetwo && mm < sunsettwo){
                manualmode_bedroom_blinds_on.setVisibility(View.GONE);
                manualmode_bedroom_blinds_off.setVisibility(View.VISIBLE);
                Toast.makeText(User_Automatic_Mode.this, "Blinds On", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            manualmode_bedroom_blinds_on.setVisibility(View.VISIBLE);
            manualmode_bedroom_blinds_off.setVisibility(View.GONE);
            Toast.makeText(User_Automatic_Mode.this, "Blinds Off", Toast.LENGTH_SHORT).show();
        }


//        String nightlampOn = globalVariable.getAutomatic_mode_bedroom_nightlampon();
//        String[] nightlampOnArray = nightlampOn.split(":");
//        nightlamponhour = Integer.parseInt(nightlampOnArray[0]);
//        nightlamponmin = Integer.parseInt(nightlampOnArray[1]);
//
//        String nightLampOff = globalVariable.getAutomatic_mode_bedroom_nightlampoff();
//        String[] nightLampOffArray = nightLampOff.split(":");
//        nightlampoffhour = Integer.parseInt(nightLampOffArray[0]);
//        nightlampoffmin = Integer.parseInt(nightLampOffArray[1]);

        String bulbOn = globalVariable.getAutomatic_mode_livingroom_bulbon();
        String[] bulbOnArray = bulbOn.split(":");
        int bulbonhour = Integer.parseInt(bulbOnArray[0]);
        int bulbonmin = Integer.parseInt(bulbOnArray[1]);

        String bulboff = globalVariable.getAutomatic_mode_livingroom_bulboff();
        String[] bulboffArray = bulboff.split(":");
        int bulboffhour = Integer.parseInt(bulboffArray[0]);
        int bulboffmin = Integer.parseInt(bulboffArray[1]);


        String tablelampOn = globalVariable.getAutomatic_mode_livingroom_tablelampon();
        String tablelampoff = globalVariable.getAutomatic_mode_livingroom_tablelampoff();

        String[] tablelampOnArray = tablelampOn.split(":");
        int tablelamponhour = Integer.parseInt(tablelampOnArray[0]);
        int tablelamponmin = Integer.parseInt(tablelampOnArray[1]);

        String[] tablelampOffArray = tablelampoff.split(":");
        int tablelampoffhour = Integer.parseInt(tablelampOffArray[0]);
        int tablelampoffmin = Integer.parseInt(tablelampOffArray[1]);

        String televisionon = globalVariable.getAutomatic_mode_livingroom_televisionon();
        String televisionoff = globalVariable.getAutomatic_mode_livingroom_televisionoff();

        String[] televisiononArray = televisionon.split(":");
        int televisiononhour = Integer.parseInt(televisiononArray[0]);
        int televisiononmin = Integer.parseInt(televisiononArray[1]);

        String[] televisionoffArray = televisionoff.split(":");
        int televisionoffhour = Integer.parseInt(televisionoffArray[0]);
        int televisionoffmin = Integer.parseInt(televisionoffArray[1]);


        if(HH > nightlamponhour && HH < nightlampoffhour){
            if(mm > nightlamponmin && mm < nightlampoffmin){
                Toast.makeText(User_Automatic_Mode.this, "Night Lamp On", Toast.LENGTH_SHORT).show();
                manualmode_bedroom_nightlamp_on.setVisibility(View.GONE);
                manualmode_bedroom_nightlamp_off.setVisibility(View.VISIBLE);
            }

        }
        else if(HH >= tablelamponhour && HH <= tablelampoffhour  ){
            if(mm >tablelamponmin && mm <tablelampoffmin ){
                bedroomnightlamplayout.setVisibility(View.GONE);
                livingroomtablelamplayout.setVisibility(View.VISIBLE);

                manualmode_lr_tablelamp_on.setVisibility(View.GONE);
                manualmode_lr_tablelamp_off.setVisibility(View.VISIBLE);
                Toast.makeText(User_Automatic_Mode.this, "Table Lamp On", Toast.LENGTH_SHORT).show();
            }
        }else if(HH > televisiononhour && HH < televisionoffhour){
            if(mm >televisiononmin && mm <televisionoffmin ){

                bedroomnightlamplayout.setVisibility(View.GONE);
                livingroomtablelamplayout.setVisibility(View.GONE);
                livingroomtelevisionlayout.setVisibility(View.VISIBLE);
                manualmode_lr_television_on.setVisibility(View.GONE);
                manualmode_lr_tablelamp_off.setVisibility(View.VISIBLE);
                Toast.makeText(User_Automatic_Mode.this, "Television On", Toast.LENGTH_SHORT).show();
            }
        }

        if (HH > bulbonhour && HH < bulboffhour){
            if(mm >bulbonmin && mm <bulboffmin ){

                bedroombulblayout.setVisibility(View.GONE);
                livingroombulblayout.setVisibility(View.VISIBLE);
                manualmode_lr_bulb_on.setVisibility(View.GONE);
                manualmode_lr_bulb_off.setVisibility(View.VISIBLE);
                Toast.makeText(User_Automatic_Mode.this, "Bulb On", Toast.LENGTH_SHORT).show();
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
        //BEDROOM NIGHT LAMP ON/OFF
        manualmode_bedroom_nightlamp_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_nightlamp_on.setVisibility(View.GONE);
                manualmode_bedroom_nightlamp_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_bedroom_nightlamp_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_nightlamp_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_nightlamp_off.setVisibility(View.GONE);
            }
        });



        //LIVING ROOM LIGHT ON/OFF
        manualmode_lr_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_bulb_on.setVisibility(View.GONE);
                manualmode_lr_bulb_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_lr_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_bulb_on.setVisibility(View.VISIBLE);
                manualmode_lr_bulb_off.setVisibility(View.GONE);
            }
        });

        //LIVING ROOM TABLE LAMP ON/OFF
        manualmode_lr_tablelamp_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_tablelamp_on.setVisibility(View.GONE);
                manualmode_lr_tablelamp_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_lr_tablelamp_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_tablelamp_on.setVisibility(View.VISIBLE);
                manualmode_lr_tablelamp_off.setVisibility(View.GONE);
            }
        });

        //LIVING ROOM TELEVISION ON/OFF
        manualmode_lr_television_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_television_on.setVisibility(View.GONE);
                manualmode_lr_television_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_lr_television_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_television_on.setVisibility(View.VISIBLE);
                manualmode_lr_television_off.setVisibility(View.GONE);
            }
        });

        //KITCHEN LIGHTS ON/OFF
        manualmode_kitchen_bulb_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_bulb_on.setVisibility(View.GONE);
                manualmode_kitchen_bulb_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_kitchen_bulb_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_bulb_on.setVisibility(View.VISIBLE);
                manualmode_kitchen_bulb_off.setVisibility(View.GONE);
            }
        });
    }
}
