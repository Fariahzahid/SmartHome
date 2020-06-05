package com.example.smart_home.Contact_Person_Screen;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_MoveOut_Mode;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Automatic_Mode;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Sleep_Mode;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
public class Contact_Person_User_Modes extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    LinearLayout sleep,moveout,automatic,voice;
    String userid,userContactid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_user_modes);


        GlobalVariables globalVariable=(GlobalVariables)getApplication();
        userid  = globalVariable.getUserIDUser();
        userContactid = globalVariable.getUserIDContactPerson();

        sleep = (LinearLayout) findViewById(R.id.contact_person_sleep_mode);
        moveout = (LinearLayout) findViewById(R.id.contact_person_moveout_mode);
        automatic = (LinearLayout) findViewById(R.id.contact_person_automatic_mode);
        voice = (LinearLayout) findViewById(R.id.contact_person_voice_mode);

        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Contact_Person_User_Modes.this, Contact_Person_Sleep_Mode.class);
                intent1.putExtra("UserID",userid);
                intent1.putExtra("ContactUserID",userContactid);
                startActivity(intent1);
            }
        });
        moveout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Contact_Person_User_Modes.this, Contact_Person_MoveOut_Mode.class);
                intent1.putExtra("UserID",userid);
                startActivity(intent1);
            }
        });
        automatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Contact_Person_User_Modes.this, Contact_Person_Automatic_Mode.class);
                intent1.putExtra("UserID",userid);
                startActivity(intent1);
            }
        });
//        voice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Contact_Person_User_Modes.this, Contact_Person_Sleep_Mode_Bedroom.class);
//                intent1.putExtra("UserID",value);
//                startActivity(intent1);
//            }
//        });


    }
}


