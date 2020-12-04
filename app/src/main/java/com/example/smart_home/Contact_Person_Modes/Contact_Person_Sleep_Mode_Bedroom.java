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
import androidx.fragment.app.Fragment;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Contact_Person_Sleep_Mode_Bedroom extends Fragment {
    private static final String TAG = "MyActivity";

    private String bedroom;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView temperature;
    String userID;
    Button save;
    String Status;


    android.widget.Spinner ac,actemp,heating,heatingtemp,time,winblind,bulb,bulbint,nightlamp,chrgpoint;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        GlobalVariables globalVariables =(GlobalVariables)getActivity().getApplication();
        userID = globalVariables.getUserIDUser();
        View v = inflater.inflate(R.layout.activity_contact_person_sleepmode_bedroom, container, false);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        bedroom = "Sleep_Mode_Bedroom";

        winblind =v.findViewById(R.id.window_blind_sleepmode_bedroom_spinner);
        bulb = v.findViewById(R.id.bulb_sleepmode_bedroom_spinner);
        bulbint = v.findViewById(R.id.bulb_intensity_sleepmode_bedroom_spinner);
        nightlamp = v.findViewById(R.id.night_lamp_sleepmode_bedroom_spinner);
        chrgpoint = v.findViewById(R.id.charging_point_sleepmode_bedroom_spinner);
        save = (Button) v.findViewById(R.id.bedroom_sleepmode_save);

        DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document("Bedroom");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String Blinds = documentSnapshot.getString("WindowBlinds");
                String Bulb = documentSnapshot.getString("Bulb");
                String BulbIntensity = documentSnapshot.getString("BulbIntensity");
                String NightLamp = documentSnapshot.getString("NightLamp");
                String ChargingPoints = documentSnapshot.getString("ChargingPoints");

                if(Blinds != null){
                    Status = Blinds;
                    WindowBlind(Status);
                }else
                {
                    Status = "Select Status";
                    WindowBlind(Status);
                }
                if(Bulb != null){
                    Status = Bulb;
                    Bulb(Status);
                }else
                {
                    Status = "Select Status";
                    Bulb(Status);
                }
                if(BulbIntensity != null){
                    Status = BulbIntensity;
                    BulbIntensity(Status);
                }else
                {
                    Status = "Select Intensity";
                    BulbIntensity(Status);
                }
                if(NightLamp != null){
                    Status = NightLamp;
                    NightLamp(Status);
                }else
                {
                    Status = "Select Status";
                    NightLamp(Status);
                }
                if(ChargingPoints != null){
                    Status = ChargingPoints;
                    ChargingPoint(Status);
                }else
                {
                    Status = "Select Status";
                    ChargingPoint(Status);
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document("Bedroom");
                Map<String,Object> user = new HashMap<>();
                user.put("WindowBlinds",winblind.getSelectedItem().toString());
                user.put("Bulb",bulb.getSelectedItem().toString());
                user.put("BulbIntensity",bulbint.getSelectedItem().toString());
                user.put("NightLamp",nightlamp.getSelectedItem().toString());
                user.put("ChargingPoints",chrgpoint.getSelectedItem().toString());

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

    private void WindowBlind(String status){



        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, status);
        timearray.add("OPEN");
        timearray.add("CLOSE");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        winblind.setAdapter(adapter);

        winblind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Status")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    //Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void Bulb(String status){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, status);
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
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void BulbIntensity(String status){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, status);
        timearray.add("OFF");
        timearray.add("LOW");
        timearray.add("MEDIUM");
        timearray.add("HIGH");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulbint.setAdapter(adapter);

        bulbint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Intensity")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void NightLamp(String status){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, status);
        timearray.add("ON");
        timearray.add("OFF");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        nightlamp.setAdapter(adapter);

        nightlamp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Status")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void ChargingPoint(String status){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, status);
        timearray.add("ON");
        timearray.add("OFF");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        chrgpoint.setAdapter(adapter);

        chrgpoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Status")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
