package com.example.smart_home.Users_Modes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.R;

public class User_Home  extends AppCompatActivity {
    private Button sleep_mode,moveout_mode,automatic_mode,manual_mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_home);

        sleep_mode = (Button) findViewById(R.id.user_home_sleep_mode_button);
        moveout_mode = (Button) findViewById(R.id.user_home_moveoutmode_button);
        automatic_mode = (Button) findViewById(R.id.user_home_automatic_mode_button);
        manual_mode = (Button) findViewById(R.id.user_home_manual_mode_button);

        sleep_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Sleep_Mode.class);
                startActivity(intent);
            }
        });
        moveout_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Move_Out_Mode.class);
                startActivity(intent);
            }
        });
        automatic_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Automatic_Mode_Bedroom.class);
                startActivity(intent);
            }
        });
        manual_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Manual_Mode_Bedroom.class);
                startActivity(intent);
            }
        });
    }
}
