package com.example.smart_home.Contact_Person_Modes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
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

public class Contact_Person_Moveout_Mode_Bedroom extends Fragment {
    private static final String TAG = "MyActivity";

    private String bedroom;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    TextView temperature;
    Button save;

    IntentFilter intentfilter;
    float batteryTemp;
    String currentBatterytemp="Current Battery temp :";

    android.widget.Spinner  ac, actemp, heating, heatingtemp, time, winblind, bulb, bulbint, nightlamp, chrgpoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GlobalVariables globalVariables =(GlobalVariables)getActivity().getApplication();
        userID = globalVariables.getUserIDUser();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_contact_person_moveoutmode_bedroom, container, false);
        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        bedroom = "Move_Out_Mode_BedRoom";

        //temperature.setText("20");

        ac = v.findViewById(R.id.ac_moveout_bedroom_spinner);
        actemp = v.findViewById(R.id.ac_temperature_moveout_bedroom_spinner);
        heating = v.findViewById(R.id.heating_moveout_bedroom_spinner);
        heatingtemp = v.findViewById(R.id.heating_temperature_moveout_bedroom_spinner);
        winblind = v.findViewById(R.id.window_blind_moveout_bedroom_spinner);
        bulb = v.findViewById(R.id.bulb_moveout_bedroom_spinner);
        bulbint = v.findViewById(R.id.bulb_intensity_moveout_bedroom_spinner);
        nightlamp = v.findViewById(R.id.night_lamp_moveout_bedroom_spinner);
        chrgpoint = v.findViewById(R.id.charging_point_moveout_bedroom_spinner);
        save = (Button) v.findViewById(R.id.bedroom_moveoutmode_save);


        Temperature();
        AcStatus();
        AcTemperature();
        HeatingStatus();
        HeatingTemperature();
        //Time();
        WindowBlind();
        Bulb();
        BulbIntensity();
        NightLamp();
        ChargingPoint();

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document("Bedroom");
                Map<String, Object> user = new HashMap<>();
                //user.put("UserID",userID);
                //user.put("Temperature", temperature.toString());
                user.put("AC", ac.getSelectedItem().toString());
                user.put("ACtemperature", actemp.getSelectedItem().toString());
                user.put("Heating", heating.getSelectedItem().toString());
                user.put("HeatingTemperature", heatingtemp.getSelectedItem().toString());
                //user.put("Time", time.getSelectedItem().toString());
                user.put("WindowBlinds", winblind.getSelectedItem().toString());
                user.put("Bulb", bulb.getSelectedItem().toString());
                user.put("BulbIntensity", bulbint.getSelectedItem().toString());
                user.put("NightLamp", nightlamp.getSelectedItem().toString());
                user.put("ChargingPoints", chrgpoint.getSelectedItem().toString());

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d(TAG, "OnSuccess: User Details Added " + userID);
                        Toast.makeText(getActivity(), "OnSuccess: User Details Added!", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "OnFailire: User Details Note added " + e.toString());
                        Toast.makeText(getActivity(), "OnFailire: User Details Not Added !", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });

        return v;

    }

    private void Temperature(){
        new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                batteryTemp = (float) (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)) / 10;

                temperature.setText(currentBatterytemp + " " + batteryTemp + " " + (char) 0x00B0 + "C");

            }
        };
    }
    private void AcStatus(){
        ArrayList<String> acarray = new ArrayList<>();
        acarray.add(0, "Select Status");
        acarray.add("ON");
        acarray.add("OFF");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, acarray);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ac.setAdapter(adapter2);

        ac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void AcTemperature(){
        ArrayList<String> acarray = new ArrayList<>();
        acarray.add(0, "Select Temperature");
        acarray.add("0");
        acarray.add("5");
        acarray.add("10");
        acarray.add("15");
        acarray.add("20");
        acarray.add("21");
        acarray.add("22");
        acarray.add("23");
        acarray.add("24");
        acarray.add("25");
        acarray.add("26");
        acarray.add("27");
        acarray.add("28");
        acarray.add("29");
        acarray.add("30");


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, acarray);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        actemp.setAdapter(adapter2);

        actemp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void HeatingStatus(){
        ArrayList<String> acarray = new ArrayList<>();
        acarray.add(0, "Select Status");
        acarray.add("ON");
        acarray.add("OFF");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, acarray);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        heating.setAdapter(adapter2);

        heatingtemp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void HeatingTemperature(){
        ArrayList<String> acarray = new ArrayList<>();
        acarray.add(0, "Select Temperature");
        acarray.add("0");
        acarray.add("25");
        acarray.add("26");
        acarray.add("27");
        acarray.add("28");
        acarray.add("29");
        acarray.add("30");
        acarray.add("31");
        acarray.add("32");
        acarray.add("33");
        acarray.add("34");
        acarray.add("35");


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, acarray);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        heatingtemp.setAdapter(adapter2);

        heatingtemp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        winblind.setAdapter(adapter);

        winblind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void BulbIntensity(){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, "Select Intensity");
        timearray.add("OFF");
        timearray.add("HIGH");
        timearray.add("LOW");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulbint.setAdapter(adapter);

        bulbint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void NightLamp(){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, "Select Status");
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
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void ChargingPoint(){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, "Select Status");
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
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}