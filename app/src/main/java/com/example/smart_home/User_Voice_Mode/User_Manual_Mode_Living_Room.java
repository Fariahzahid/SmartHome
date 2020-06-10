package com.example.smart_home.User_Voice_Mode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.R;

import java.util.ArrayList;

public class User_Manual_Mode_Living_Room extends AppCompatActivity {
    private Spinner user_room_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manual_mode_living_mode);

        ArrayList<String> categories = new ArrayList<>();
        categories.add(0,"BED ROOM");
        categories.add("LIVING ROOM");
        categories.add("KITCHEN");
        categories.add("WC");
        user_room_spinner = findViewById(R.id.user_rooms_spinner_bedroom);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_room_spinner.setAdapter(aa);
        user_room_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("BED ROOM")){

                }else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(),"Selected"+item,Toast.LENGTH_SHORT).show();
                }
                if(parent.getItemAtPosition(position).equals("LIVING ROOM")){
                    Intent intent = new Intent(User_Manual_Mode_Living_Room.this, User_Manual_Mode_Living_Room.class);
                    startActivity(intent);
                }
                if(parent.getItemAtPosition(position).equals("KITCHEN")){

                    Intent intent = new Intent(User_Manual_Mode_Living_Room.this, User_Manual_Mode_Kitchen.class);
                    startActivity(intent);
                }
                if(parent.getItemAtPosition(position).equals("WC")){
                    Intent intent = new Intent(User_Manual_Mode_Living_Room.this, User_Manual_Mode_WC.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
