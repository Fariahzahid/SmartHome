package com.example.smart_home.Users_Modes;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.smart_home.R;
import com.google.android.material.navigation.NavigationView;

public class User_Manual_Mode_Colored extends AppCompatActivity {
    Button manualmode_wc_plus_button,manualmode_wc_minus_button,
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

    LinearLayout manualmode_wc_layout, manualmode_bedroom_layout, manualmode_lr_layout, manualmode_kitchen_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual_mode_wooden);

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
