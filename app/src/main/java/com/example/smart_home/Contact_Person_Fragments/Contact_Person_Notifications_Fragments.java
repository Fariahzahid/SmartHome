package com.example.smart_home.Contact_Person_Fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.example.smart_home.R;

public class Contact_Person_Notifications_Fragments extends Fragment {

    public Contact_Person_Notifications_Fragments() {

    }

    public static Contact_Person_Notifications_Fragments newInstance(String param1, String param2) {
        Contact_Person_Notifications_Fragments fragment = new Contact_Person_Notifications_Fragments();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_person_notifications, container, false);
        return view;
    }
}
