package com.example.smart_home.Contact_Person_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.smart_home.R;

import java.util.ArrayList;

public class Contact_Person_User_Home extends AppCompatActivity {
    private Spinner mSpinner ;
    Fragment_Contact_Person_User_Profile fragmentone;
    Fragment_Contact_Person_User_Mode_Settings fragmenttwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_user_home);

        fragmentone = new Fragment_Contact_Person_User_Profile();
        fragmenttwo = new Fragment_Contact_Person_User_Mode_Settings();

        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"User Profile");
        categories.add("Modes Settings");
        mSpinner = findViewById(R.id.user_activiities_selection);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(aa);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("User Profile")) {

                    setFragment(fragmentone);
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }

                if (parent.getItemAtPosition(position).equals("Modes Settings")) {

                    setFragment(fragmenttwo);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contact_person_user_home_framelayout,fragment);
        fragmentTransaction.commit();
    }
}
