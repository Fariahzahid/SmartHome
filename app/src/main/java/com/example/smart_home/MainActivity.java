package com.example.smart_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smart_home.Contact_Person_Modes.Contact_Person_Sleep_Mode;
import com.example.smart_home.Contact_Person_SignIn.Login;
import com.example.smart_home.Users_Modes.User_Home;
import com.example.smart_home.Users_SignIn.User_Login;

public class MainActivity extends AppCompatActivity {

    private Button contact_person,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contact_person = (Button) findViewById(R.id.mainpage_contactperson);
        user = (Button) findViewById(R.id.mainpage_user);
        contact_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, User_Login.class);
                startActivity(intent);
            }
        });
}
}
