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

import com.example.smart_home.R;

import java.util.ArrayList;

public class Contact_Person_User_Home extends AppCompatActivity {
    private Spinner mSpinner ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_user_home);

        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"Select User Activities");
        categories.add("User Profile");
        categories.add("Modes Settings");
        mSpinner = findViewById(R.id.spinner);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(aa);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //mText.setText(parent.getItemAtPosition(position).toString());
                if(parent.getItemAtPosition(position).equals("Select User Activities")){

                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(),"Selected"+item,Toast.LENGTH_SHORT).show();
                }
                if(parent.getItemAtPosition(position).equals("User Profile")){
                    Intent intent = new Intent(Contact_Person_User_Home.this, Contact_Person_User_Home.class);
                    startActivity(intent);
                }
                if(parent.getItemAtPosition(position).equals("Modes Settings")){

                    Intent intent = new Intent(Contact_Person_User_Home.this, Contact_Person_User_Modes.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
