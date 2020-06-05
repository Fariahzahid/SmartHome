package com.example.smart_home.Contact_Person_Modes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;

import java.util.ArrayList;

public class Contact_Person_Automatic_Mode extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private Spinner mSpinner ;
    private Contact_Person_Automatic_Mode_Bedroom bedroom;
    private Contact_Person_Automatic_Mode_Livingroom livingroom;
    private Contact_Person_Automatic_Mode_WC wc;
    private Contact_Person_Automatic_Mode_Kitchen kitchen;
    private ImageView room_image;
    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_automatic_mode);

        GlobalVariables globalVariables =(GlobalVariables)getApplication();
        userid = globalVariables.getUserIDUser();

        bedroom = new Contact_Person_Automatic_Mode_Bedroom();
        livingroom = new Contact_Person_Automatic_Mode_Livingroom();
        wc = new Contact_Person_Automatic_Mode_WC();
        kitchen = new Contact_Person_Automatic_Mode_Kitchen();


        ArrayList<String> categories = new ArrayList<>();
        // categories.add(0,"Select Room");
        categories.add(0,"Bed Room");
        categories.add("Living Room");
        categories.add("Kitchen");
        categories.add("WC");
        mSpinner = findViewById(R.id.rooms_auto_spinner);
        room_image = (ImageView) findViewById(R.id.user_main_mode_image);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(aa);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Bed Room")) {
                    room_image.setImageResource(R.drawable.bedroom1);
                    setFragment(bedroom);
                }

                if (parent.getItemAtPosition(position).equals("Living Room")) {

                    setFragment(livingroom);
                    room_image.setImageResource(R.drawable.livingroom);

                }
                if (parent.getItemAtPosition(position).equals("Kitchen")) {

                    setFragment(kitchen);
                    room_image.setImageResource(R.drawable.kitchen);

                }
                if (parent.getItemAtPosition(position).equals("WC")) {

                    setFragment(wc);
                    room_image.setImageResource(R.drawable.toilet);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_automatic_mode,fragment);
        fragmentTransaction.commit();
    }
}
