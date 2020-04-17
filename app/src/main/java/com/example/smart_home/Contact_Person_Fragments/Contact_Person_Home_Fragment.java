package com.example.smart_home.Contact_Person_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.smart_home.Contact_Person_Screen.Contact_Person_Add_New_Users;
import com.example.smart_home.Contact_Person_Screen.Contact_Person_Modes;
import com.example.smart_home.Contact_Person_Screen.Contact_Person_Users_List;
import com.example.smart_home.R;

public class Contact_Person_Home_Fragment extends Fragment {

    public Contact_Person_Home_Fragment() {
    }

    public static Contact_Person_Home_Fragment newInstance(String param1, String param2) {
        Contact_Person_Home_Fragment fragment = new Contact_Person_Home_Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_person_home, container, false);
        Button user_list = (Button) view.findViewById(R.id.contact_person_user_list_button);
        Button add_new_user = (Button) view.findViewById(R.id.contact_person_new_user_button);
        //LinearLayout modes = (LinearLayout) view.findViewById(R.id.layout_modes);

        user_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Contact_Person_Users_List.class);
                in.putExtra("some","some data");
                startActivity(in);

            }
        });
        add_new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), Contact_Person_Add_New_Users.class);
                in.putExtra("some","some data");
                startActivity(in);

            }
        });

        return view;
    }
}
