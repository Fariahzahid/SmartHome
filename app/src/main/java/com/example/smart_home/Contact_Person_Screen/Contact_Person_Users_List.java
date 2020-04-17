package com.example.smart_home.Contact_Person_Screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.smart_home.R;

public class Contact_Person_Users_List extends Fragment {

    public Contact_Person_Users_List() {
    }

    public static Contact_Person_Users_List newInstance(String param1, String param2) {
        Contact_Person_Users_List fragment = new Contact_Person_Users_List();
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
        return view;
    }
}