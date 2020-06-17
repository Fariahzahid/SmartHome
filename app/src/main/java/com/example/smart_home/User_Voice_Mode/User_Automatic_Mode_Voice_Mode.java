package com.example.smart_home.User_Voice_Mode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User_Automatic_Mode_Voice_Mode extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    Button  manualmode_wc_plus_button,manualmode_wc_minus_button,
            manualmode_bedroom_plus_button,manualmode_bedroom_minus_button,
            manualmode_lr_plus_button,manualmode_lr_minus_button,
            manualmode_kitchen_plus_button,manualmode_kitchen_minus_button,
            manualmode_wc_bulb_on, manualmode_wc_bulb_off,
            manualmode_wc_fan_on, manualmode_wc_fan_off,
            manualmode_bedroom_bulb_on, manualmode_bedroom_bulb_off,
            manualmode_bedroom_blinds_on, manualmode_bedroom_blinds_off,
            manualmode_bedroom_charging_on, manualmode_bedroom_charging_off,
            manualmode_bedroom_nightlamp_on, manualmode_bedroom_nightlamp_off,
            manualmode_lr_bulb_on, manualmode_lr_bulb_off,
            manualmode_lr_blinds_on, manualmode_lr_blinds_off,
            manualmode_lr_tablelamp_on, manualmode_lr_tablelamp_off,
            manualmode_lr_television_on, manualmode_lr_television_off,
            manualmode_kitchen_bulb_on, manualmode_kitchen_bulb_off,
            manualmode_kitchen_blinds_on, manualmode_kitchen_blinds_off,
            manualmode_kitchen_oven_on, manualmode_kitchen_oven_off,
            manualmode_kitchen_stove_on, manualmode_kitchen_stove_off;
    String currentTime,sunrise,sunset,weather,windspeed;
    int HH,mm,sunsetone,sunsettwo,sunriseone,sunrisetwo,nightlamponhour,nightlamponmin
            ,nightlampoffhour,nightlampoffmin;

    LinearLayout manualmode_wc_layout, manualmode_bedroom_layout, manualmode_lr_layout, manualmode_kitchen_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_automatic_mode_wooden);


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


        manualmode_wc_plus_button = (Button) findViewById(R.id.manualmode_wc_plus_button);
        manualmode_wc_minus_button = (Button) findViewById(R.id.manualmode_wc_minus_button);
        manualmode_bedroom_plus_button = (Button) findViewById(R.id.manualmode_bedroom_plus_button);
        manualmode_bedroom_minus_button = (Button) findViewById(R.id.manualmode_bedroom_minus_button);
        manualmode_lr_plus_button = (Button) findViewById(R.id.manualmode_lr_plus_button);
        manualmode_lr_minus_button = (Button) findViewById(R.id.manualmode_lr_minus_button);
        manualmode_kitchen_plus_button = (Button) findViewById(R.id.manualmode_kitchen_plus_button);
        manualmode_kitchen_minus_button = (Button) findViewById(R.id.manualmode_kitchen_minus_button);

        manualmode_wc_bulb_on = (Button) findViewById(R.id.manualmode_wc_bulb_on);
        manualmode_wc_bulb_off = (Button) findViewById(R.id.manualmode_wc_bulb_off);
        manualmode_wc_fan_on = (Button) findViewById(R.id.manualmode_wc_fan_on);
        manualmode_wc_fan_off = (Button) findViewById(R.id.manualmode_wc_fan_off);
        manualmode_bedroom_bulb_on = (Button) findViewById(R.id.manualmode_bedroom_bulb_on);
        manualmode_bedroom_bulb_off = (Button) findViewById(R.id.manualmode_bedroom_bulb_off);
        manualmode_bedroom_blinds_on = (Button) findViewById(R.id.manualmode_bedroom_blinds_on);
        manualmode_bedroom_blinds_off = (Button) findViewById(R.id.manualmode_bedroom_blinds_off);
        manualmode_bedroom_charging_on = (Button) findViewById(R.id.manualmode_bedroom_charging_on);
        manualmode_bedroom_charging_off = (Button) findViewById(R.id.manualmode_bedroom_charging_off);
        manualmode_bedroom_nightlamp_on = (Button) findViewById(R.id.manualmode_bedroom_nightlamp_on);
        manualmode_bedroom_nightlamp_off = (Button) findViewById(R.id.manualmode_bedroom_nightlamp_off);
        manualmode_lr_bulb_on = (Button) findViewById(R.id.manualmode_lr_bulb_on);
        manualmode_lr_bulb_off = (Button) findViewById(R.id.manualmode_lr_bulb_off);
        manualmode_lr_blinds_on = (Button) findViewById(R.id.manualmode_lr_blinds_on);
        manualmode_lr_blinds_off = (Button) findViewById(R.id.manualmode_lr_blinds_off);
        manualmode_lr_tablelamp_on = (Button) findViewById(R.id.manualmode_lr_tablelamp_on);
        manualmode_lr_tablelamp_off = (Button) findViewById(R.id.manualmode_lr_tablelamp_off);
        manualmode_lr_television_on = (Button) findViewById(R.id.manualmode_lr_television_on);
        manualmode_lr_television_off = (Button) findViewById(R.id.manualmode_lr_television_off);
        manualmode_kitchen_bulb_on = (Button) findViewById(R.id.manualmode_kitchen_bulb_on);
        manualmode_kitchen_bulb_off = (Button) findViewById(R.id.manualmode_kitchen_bulb_off);
        manualmode_kitchen_blinds_on = (Button) findViewById(R.id.manualmode_kitchen_blinds_on);
        manualmode_kitchen_blinds_off = (Button) findViewById(R.id.manualmode_kitchen_blinds_off);
        manualmode_kitchen_oven_on = (Button) findViewById(R.id.manualmode_kitchen_oven_on);
        manualmode_kitchen_oven_off = (Button) findViewById(R.id.manualmode_kitchen_oven_off);
        manualmode_kitchen_stove_on = (Button) findViewById(R.id.manualmode_kitchen_stove_on);
        manualmode_kitchen_stove_off = (Button) findViewById(R.id.manualmode_kitchen_stove_off);

        manualmode_wc_layout = (LinearLayout) findViewById(R.id.manualmode_wc_layout);
        manualmode_bedroom_layout = (LinearLayout) findViewById(R.id.manualmode_bedroom_layout);
        manualmode_lr_layout = (LinearLayout) findViewById(R.id.manualmode_lr_layout);
        manualmode_kitchen_layout = (LinearLayout) findViewById(R.id.manualmode_kitchen_layout);

        //TOILET LAYOUT VISIBLE
        manualmode_wc_plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_wc_plus_button.setVisibility(View.GONE);
                manualmode_wc_layout.setVisibility(View.VISIBLE);
                manualmode_wc_minus_button.setVisibility(View.VISIBLE);
            }
        });
        //TOILET LAYOUT INVISIBLE
        manualmode_wc_minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_wc_plus_button.setVisibility(View.VISIBLE);
                manualmode_wc_layout.setVisibility(View.GONE);
                manualmode_wc_minus_button.setVisibility(View.GONE);

            }
        });
        //BEDROOM LAYOUT VISIBLE
        manualmode_bedroom_plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_plus_button.setVisibility(View.GONE);
                manualmode_bedroom_layout.setVisibility(View.VISIBLE);
                manualmode_bedroom_minus_button.setVisibility(View.VISIBLE);
                if(HH > sunriseone && HH < sunsetone ){
                  if(mm > sunrisetwo && mm < sunsettwo){
                        manualmode_bedroom_blinds_on.setVisibility(View.GONE);
                        manualmode_bedroom_blinds_off.setVisibility(View.VISIBLE);
                      Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Blinds On", Toast.LENGTH_SHORT).show();
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

                if(HH > nightlamponhour && HH < nightlampoffhour){
                    if(mm > nightlamponmin && mm < nightlampoffmin){
                        Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Night Lamp On", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Night Lamp Off", Toast.LENGTH_SHORT).show();

                }

            }
        });
        //BEDROOM LAYOUT INVISIBLE
        manualmode_bedroom_minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_plus_button.setVisibility(View.VISIBLE);
                manualmode_bedroom_layout.setVisibility(View.GONE);
                manualmode_bedroom_minus_button.setVisibility(View.GONE);
            }
        });
        //LIVING ROOM LAYOUT VISIBLE
        manualmode_lr_plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_plus_button.setVisibility(View.GONE);
                manualmode_lr_layout.setVisibility(View.VISIBLE);
                manualmode_lr_minus_button.setVisibility(View.VISIBLE);

                String bulbOn = globalVariable.getAutomatic_mode_livingroom_bulbon();
                String[] bulbOnArray = bulbOn.split(":");
                int bulbonhour = Integer.parseInt(bulbOnArray[0]);
                int bulbonmin = Integer.parseInt(bulbOnArray[1]);

                String bulboff = globalVariable.getAutomatic_mode_livingroom_bulboff();
                String[] bulboffArray = bulboff.split(":");
                int bulboffhour = Integer.parseInt(bulboffArray[0]);
                int bulboffmin = Integer.parseInt(bulboffArray[1]);

                if(HH > bulbonhour && HH < bulboffhour){
                    if(mm >bulbonmin && mm <bulboffmin ){
                        Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Bulb On", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Bulb Off", Toast.LENGTH_SHORT).show();

                }
                String tablelampOn = globalVariable.getAutomatic_mode_livingroom_tablelampon();
                String tablelampoff = globalVariable.getAutomatic_mode_livingroom_tablelampoff();

                String[] tablelampOnArray = tablelampOn.split(":");
                int tablelamponhour = Integer.parseInt(tablelampOnArray[0]);
                int tablelamponmin = Integer.parseInt(tablelampOnArray[1]);
                String[] tablelampOffArray = tablelampoff.split(":");
                int tablelampoffhour = Integer.parseInt(tablelampOffArray[0]);
                int tablelampoffmin = Integer.parseInt(tablelampOffArray[1]);

                if(HH > tablelamponhour && HH < tablelampoffhour){
                    if(mm >tablelamponmin && mm <tablelampoffmin ){
                        Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Table Lamp On", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Table Lamp Off", Toast.LENGTH_SHORT).show();

                }
                String televisionon = globalVariable.getAutomatic_mode_livingroom_televisionon();
                String televisionoff = globalVariable.getAutomatic_mode_livingroom_televisionoff();

                String[] televisiononArray = televisionon.split(":");
                int televisiononhour = Integer.parseInt(televisiononArray[0]);
                int televisiononmin = Integer.parseInt(televisiononArray[1]);
                String[] televisionoffArray = televisionoff.split(":");
                int televisionoffhour = Integer.parseInt(televisionoffArray[0]);
                int televisionoffmin = Integer.parseInt(televisionoffArray[1]);

                if(HH > televisiononhour && HH < televisionoffhour){
                    if(mm >televisiononmin && mm <televisionoffmin ){
                        Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Television On", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(User_Automatic_Mode_Voice_Mode.this, "Television Off", Toast.LENGTH_SHORT).show();

                }
            }
        });
        //LIVING ROOM LAYOUT INVISIBLE
        manualmode_lr_minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_plus_button.setVisibility(View.VISIBLE);
                manualmode_lr_layout.setVisibility(View.GONE);
                manualmode_lr_minus_button.setVisibility(View.GONE);
            }
        });
        //KITCHEN LAYOUT VISIBLE
        manualmode_kitchen_plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_plus_button.setVisibility(View.GONE);
                manualmode_kitchen_layout.setVisibility(View.VISIBLE);
                manualmode_kitchen_minus_button.setVisibility(View.VISIBLE);
            }
        });
        //KITCHEN LAYOUT INVISIBLE

        manualmode_kitchen_minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_plus_button.setVisibility(View.VISIBLE);
                manualmode_kitchen_layout.setVisibility(View.GONE);
                manualmode_kitchen_minus_button.setVisibility(View.GONE);
            }
        });

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
        //TOILET FAN ON/OFF
        manualmode_wc_fan_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_wc_fan_on.setVisibility(View.GONE);
                manualmode_wc_fan_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_wc_fan_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_wc_fan_on.setVisibility(View.VISIBLE);
                manualmode_wc_fan_off.setVisibility(View.GONE);
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

        //BEDROOM CHARGE POINTS ON/OFF
        manualmode_bedroom_charging_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_charging_on.setVisibility(View.GONE);
                manualmode_bedroom_charging_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_bedroom_charging_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_bedroom_charging_on.setVisibility(View.VISIBLE);
                manualmode_bedroom_charging_off.setVisibility(View.GONE);
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
        //LIVING ROOM BLINDS ON/OFF
        manualmode_lr_blinds_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_blinds_on.setVisibility(View.GONE);
                manualmode_lr_blinds_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_lr_blinds_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_lr_blinds_on.setVisibility(View.VISIBLE);
                manualmode_lr_blinds_off.setVisibility(View.GONE);
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
        //KITCHEN BLINDS ON/OFF
        manualmode_kitchen_blinds_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_blinds_on.setVisibility(View.GONE);
                manualmode_kitchen_blinds_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_kitchen_blinds_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_blinds_on.setVisibility(View.VISIBLE);
                manualmode_kitchen_blinds_off.setVisibility(View.GONE);
            }
        });
        //KITCHEN OVEN ON/OFF
        manualmode_kitchen_oven_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_oven_on.setVisibility(View.GONE);
                manualmode_kitchen_oven_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_kitchen_oven_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_oven_on.setVisibility(View.VISIBLE);
                manualmode_kitchen_oven_off.setVisibility(View.GONE);
            }
        });
        //KITCHEN STOVE ON/OFF
        manualmode_kitchen_stove_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_stove_on.setVisibility(View.GONE);
                manualmode_kitchen_stove_off.setVisibility(View.VISIBLE);
            }
        });
        manualmode_kitchen_stove_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualmode_kitchen_stove_on.setVisibility(View.VISIBLE);
                manualmode_kitchen_stove_off.setVisibility(View.GONE);
            }
        });
    }
}
