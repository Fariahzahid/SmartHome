package com.example.smart_home.Contact_Person_Modes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Sleep_Mode_Livingroom extends Fragment {

    private static final String TAG = "MyActivity";

    private String bedroom;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Button save;

    private android.widget.Spinner bulbintensity,windowblind, bulb, tablelamp,television;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        if(getArguments() != null){
//            userID= getArguments().getString("UserID");
//            System.out.println(userID +"userid");
//            Log.d(TAG,"UserID   "+userID);
//
//        }
        GlobalVariables globalVariables =(GlobalVariables)getActivity().getApplication();
        userID = globalVariables.getUserIDUser();
        View v = inflater.inflate(R.layout.activity_contact_person_sleepmode_livingroom, container, false);

        bedroom = "Sleep_Mode_Livingroom";
        windowblind =v.findViewById(R.id.window_blind_sleepmode_livingroom_spinner);
        bulbintensity = v.findViewById(R.id.bulb_intensity_sleepmode_livingroom_spinner);
        bulb = v.findViewById(R.id.bulb_sleepmode_livingroom_spinner);
        tablelamp = v.findViewById(R.id.tablelamp_sleepmode_livingroom_spinner);
        television = v.findViewById(R.id.tv_sleepmode_livingroom_spinner);
        save = (Button) v.findViewById(R.id.livingroom_sleepmode_save);

        WindowBlind();
        Bulb();
        BulbIntensity();
        TableLamp();
        Television();

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document("LivingRoom");
                Map<String,Object> user = new HashMap<>();
                user.put("WindowBlinds",windowblind.getSelectedItem().toString());
                user.put("Bulb Intensity", bulbintensity.getSelectedItem().toString());
                user.put("Bulb",bulb.getSelectedItem().toString());
                user.put("Table Lamp",tablelamp.getSelectedItem().toString());
                user.put("Television",television.getSelectedItem().toString());
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "OnSuccess: User Details Added " +userID);
                        Toast.makeText(getActivity(),"OnSuccess: User Details Added!",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "OnFailire: User Details Note added " +e.toString());
                        Toast.makeText(getActivity(),"OnFailire: User Details Not Added !",Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });

        return v;

    }

    private void BulbIntensity(){
        ArrayList<String> acarray = new ArrayList<>();
        acarray.add(0, "Select Intensity");
        acarray.add("OFF");
        acarray.add("LOW 1");
        acarray.add("MEDIUM 2");
        acarray.add("HIGHT 3");


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, acarray);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulbintensity.setAdapter(adapter2);

        bulbintensity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Temperature")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void WindowBlind(){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, "Select Status");
        timearray.add("OPEN");
        timearray.add("CLOSE");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        windowblind.setAdapter(adapter);

        windowblind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Status")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void Bulb(){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, "Select Status");
        timearray.add("ON");
        timearray.add("OFF");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulb.setAdapter(adapter);

        bulb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Status")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void TableLamp(){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, "Select Intensity");
        timearray.add("ON");
        timearray.add("OFF");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        tablelamp.setAdapter(adapter);

        tablelamp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Intensity")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void Television(){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, "Select Status");
        timearray.add("ON");
        timearray.add("OFF");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        television.setAdapter(adapter);

        television.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Status")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}



