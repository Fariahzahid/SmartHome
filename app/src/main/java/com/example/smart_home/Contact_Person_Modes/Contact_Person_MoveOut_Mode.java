package com.example.smart_home.Contact_Person_Modes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Contact_Person_MoveOut_Mode extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_moveout_mode);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Contact_Person_Moveout_Mode_Bedroom()).commit();
            navigationView.setCheckedItem(R.id.nav_moveoutmode_bedroom);
        }

    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_moveoutmode_bedroom:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Contact_Person_Moveout_Mode_Bedroom()).commit();
                break;
            case R.id.nav_moveoutmode_kitchen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Contact_Person_Moveout_Mode_Kitchen()).commit();
                break;
            case R.id.nav_moveoutmode_livingroom:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Contact_Person_Moveout_Mode_Livingroom()).commit();
                break;
            case R.id.nav_moveoutmode_wc:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Contact_Person_Moveout_Mode_WC()).commit();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
