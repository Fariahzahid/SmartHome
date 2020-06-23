package com.example.smart_home.Contact_Person_Screen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class Contact_Person_Notification extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_notification);
        bottomNavigation = findViewById(R.id.bottomNavView_Bar);
        buttomNavigationBar();
    }
    private void buttomNavigationBar(){

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        Intent a = new Intent(Contact_Person_Notification.this,Contact_Person_Profile.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_notifications:
                        Intent b = new Intent(Contact_Person_Notification.this,Contact_Person_Notification.class);
                        startActivity(b);
                        break;

                }
                return false;
            }
        });
    }

}
