package com.example.smart_home.Users_Modes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.R;
import com.example.smart_home.Users_SignIn.User_Login;

public class User_Home  extends AppCompatActivity {
    private String value;
    private Button sleep_mode,moveout_mode,automatic_mode,manual_mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_home);

        Intent intent = getIntent();
        value = intent.getStringExtra("UserId");
        System.out.println(value +"USER ID ==");

        sleep_mode = (Button) findViewById(R.id.user_home_sleep_mode_button);
        moveout_mode = (Button) findViewById(R.id.user_home_moveoutmode_button);
        automatic_mode = (Button) findViewById(R.id.user_home_automatic_mode_button);
        manual_mode = (Button) findViewById(R.id.user_home_manual_mode_button);

        sleep_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SleepMode(v);
            }
        });
        moveout_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Move_Out_Mode.class);
                intent.putExtra("UserID",value);
                startActivity(intent);
            }
        });
        automatic_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Automatic_Mode_Bedroom.class);
                intent.putExtra("UserID",value);
                startActivity(intent);
            }
        });
        manual_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Home.this, User_Manual_Mode_Bedroom.class);
                intent.putExtra("UserID",value);
                startActivity(intent);
            }
        });
    }

    private void SleepMode(View v){
        Intent intent = new Intent(User_Home.this, User_Sleep_Mode.class);
        //Toast.makeText(User_Home.this, "User Logged In.",Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(User_Home.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_box, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        intent.putExtra("UserID",value);
        startActivity(intent);
    }

    private void MoveOutMode(View v){
        Intent intent = new Intent(User_Home.this, User_Move_Out_Mode.class);
        Toast.makeText(User_Home.this, "User Logged In.",Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(User_Home.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_box, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        intent.putExtra("UserID",value);
        startActivity(intent);
    }
    private void AutomaticMode(View v){
        Intent intent = new Intent(User_Home.this, User_Automatic_Mode_Bedroom.class);
        Toast.makeText(User_Home.this, "User Logged In.",Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(User_Home.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_box, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        intent.putExtra("UserID",value);
        startActivity(intent);
    }
    private void ManualMode(View v){
        Intent intent = new Intent(User_Home.this, User_Manual_Mode_Bedroom.class);
        Toast.makeText(User_Home.this, "User Logged In.",Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(User_Home.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_box, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        intent.putExtra("UserID",value);
        startActivity(intent);
    }
}
