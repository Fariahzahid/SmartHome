package com.example.smart_home.Contact_Person_Screen;


import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.R;


public class Contact_Person_Users_List extends AppCompatActivity {

    Toolbar mToolbar;
    ListView mListView;
    String[] countryNames = {"1","2","3","4","5"};
    int[] countryFlags = {R.drawable.flag1,
            R.drawable.flag2,
            R.drawable.flag3,
            R.drawable.flag4,
            R.drawable.flag5
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_user_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mListView = (ListView) findViewById(R.id.listview);

    }
}