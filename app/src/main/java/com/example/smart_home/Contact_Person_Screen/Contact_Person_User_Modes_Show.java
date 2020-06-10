package com.example.smart_home.Contact_Person_Screen;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.smart_home.R;
import com.google.android.material.navigation.NavigationView;

public class Contact_Person_User_Modes_Show extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_user_modes_show);

        Toolbar toolbar = findViewById(R.id.toolbarshow);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        drawer = findViewById(R.id.drawer_layout_show);
        NavigationView navigationView = findViewById(R.id.nav_view_show);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new UserProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
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
            case R.id.nav_sleepmode_bedroom:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserSleepModeFragment()).commit();
                break;
            case R.id.nav_sleepmode_livingroom:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserSleepModeFragment()).commit();
                break;
            case R.id.nav_sleepmode_kitchen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserMoveOutModeFragment()).commit();
                break;
            case R.id.nav_sleepmode_wc:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserAutomaticModeFragment()).commit();
                break;
            case R.id.nav_moveoutmode_bedroom:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserSleepModeFragment()).commit();
                break;
            case R.id.nav_moveoutmode_livingroom:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserAutomaticModeFragment()).commit();
                break;
            case R.id.nav_moveoutmode_kitchen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserSleepModeFragment()).commit();
                break;
            case R.id.nav_moveoutmode_wc:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserAutomaticModeFragment()).commit();
                break;
            case R.id.nav_automaticmode_bedroom:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserSleepModeFragment()).commit();
                break;
            case R.id.nav_automaticmode_livingroom:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserAutomaticModeFragment()).commit();
                break;
            case R.id.nav_automaticmode_kitchen:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserSleepModeFragment()).commit();
                break;
            case R.id.nav_automaticmode_wc:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserAutomaticModeFragment()).commit();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
