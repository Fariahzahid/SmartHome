package com.example.smart_home.Contact_Person_Modes;

import android.content.IntentFilter;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Automatic_Mode_Kitchen extends Fragment {

    private static final String TAG = "MyActivity";

    private String bedroom;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    TextView temperature;
    Button save;

    IntentFilter intentfilter;
    float batteryTemp;
    String currentBatterytemp = "Current Battery temp :";

    private android.widget.Spinner windowblindOn,windowblindOff,bulbon,bulboff,bulbintensity, stoveon,stoveoff,refrigrator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        GlobalVariables globalVariables =(GlobalVariables)getActivity().getApplication();
        userID = globalVariables.getUserIDUser();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_contact_person_automaticmode_kitchen, container, false);
        bedroom = "Automatic_Mode_Kitchen";

        windowblindOn = v.findViewById(R.id.windowblind_on_automaticmode_kitchen_spinner);
        windowblindOff = v.findViewById(R.id.windowblind_off_automaticmode_kitchen_spinner);
        bulbon = v.findViewById(R.id.bulb_on_automaticmode_kitchen_spinner);
        bulboff = v.findViewById(R.id.bulb_off_automaticmode_kitchen_spinner);
        bulbintensity = v.findViewById(R.id.bulb_intensity_automaticmode_kitchen_spinner);
        stoveon = v.findViewById(R.id.stove_on_automaticmode_kitchen_spinner);
        stoveoff = v.findViewById(R.id.stove_off_automaticmode_kitchen_spinner);
        refrigrator = v.findViewById(R.id.refrigrator_automaticmode_kitchen_spinner);
        save = (Button) v.findViewById(R.id.kitchen_automaticmode_save);

        WindowBlindOn();
        WindowBlindOff();
        BulbOn();
        BulbOff();
        BulbIntensity();
        StoveOn();
        StoveOff();
        Refrigrator();

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document("Kitchen");
                Map<String, Object> user = new HashMap<>();
                //user.put("UserID",userID);
                user.put("WindowBlindsON", windowblindOn.getSelectedItem().toString());
                user.put("WindowBlindsOFF", windowblindOff.getSelectedItem().toString());
                user.put("BulbOn", bulbon.getSelectedItem().toString());
                user.put("BulbOff", bulboff.getSelectedItem().toString());
                user.put("BulbIntensity", bulbintensity.getSelectedItem().toString());
                user.put("StoveOn", stoveon.getSelectedItem().toString());
                user.put("StoveOFF", stoveoff.getSelectedItem().toString());
                user.put("Refrigrator", refrigrator.getSelectedItem().toString());
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

    private void WindowBlindOn() {
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Time");
        windowblind.add("1");
        windowblind.add("2");
        windowblind.add("3");
        windowblind.add("4");
        windowblind.add("5");
        windowblind.add("6");
        windowblind.add("7");
        windowblind.add("8");
        windowblind.add("9");
        windowblind.add("10");
        windowblind.add("11");
        windowblind.add("12");
        windowblind.add("12");
        windowblind.add("13");
        windowblind.add("14");
        windowblind.add("15");
        windowblind.add("16");
        windowblind.add("17");
        windowblind.add("18");
        windowblind.add("19");
        windowblind.add("20");
        windowblind.add("21");
        windowblind.add("22");
        windowblind.add("23");
        windowblind.add("24");


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        windowblindOn.setAdapter(adapter2);

        windowblindOn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Time")) {

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
    private void WindowBlindOff() {
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Time");
        windowblind.add("1");
        windowblind.add("2");
        windowblind.add("3");
        windowblind.add("4");
        windowblind.add("5");
        windowblind.add("6");
        windowblind.add("7");
        windowblind.add("8");
        windowblind.add("9");
        windowblind.add("10");
        windowblind.add("11");
        windowblind.add("12");
        windowblind.add("13");
        windowblind.add("14");
        windowblind.add("15");
        windowblind.add("16");
        windowblind.add("17");
        windowblind.add("18");
        windowblind.add("19");
        windowblind.add("20");
        windowblind.add("21");
        windowblind.add("22");
        windowblind.add("23");
        windowblind.add("24");


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        windowblindOff.setAdapter(adapter2);

        windowblindOff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Time")) {

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
    private void BulbOn() {
        ArrayList<String> bulb = new ArrayList<>();
        bulb.add(0, "Time");
        bulb.add("1");
        bulb.add("2");
        bulb.add("3");
        bulb.add("4");
        bulb.add("5");
        bulb.add("6");
        bulb.add("7");
        bulb.add("8");
        bulb.add("9");
        bulb.add("10");
        bulb.add("11");
        bulb.add("12");
        bulb.add("13");
        bulb.add("14");
        bulb.add("15");
        bulb.add("16");
        bulb.add("17");
        bulb.add("18");
        bulb.add("19");
        bulb.add("20");
        bulb.add("21");
        bulb.add("22");
        bulb.add("23");
        bulb.add("24");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulbon.setAdapter(adapter2);

        bulbon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Time")) {

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
    private void BulbOff() {
        ArrayList<String> bulb = new ArrayList<>();
        bulb.add(0, "Time");
        bulb.add("1");
        bulb.add("2");
        bulb.add("3");
        bulb.add("4");
        bulb.add("5");
        bulb.add("6");
        bulb.add("7");
        bulb.add("8");
        bulb.add("9");
        bulb.add("10");
        bulb.add("11");
        bulb.add("12");
        bulb.add("13");
        bulb.add("14");
        bulb.add("15");
        bulb.add("16");
        bulb.add("17");
        bulb.add("18");
        bulb.add("19");
        bulb.add("20");
        bulb.add("21");
        bulb.add("22");
        bulb.add("23");
        bulb.add("24");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulboff.setAdapter(adapter2);

        bulboff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Time")) {

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
    private void BulbIntensity() {
        ArrayList<String> bulb = new ArrayList<>();
        bulb.add(0, "Status");
        bulb.add("Off");
        bulb.add("Low");
        bulb.add("Medium");
        bulb.add("High");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulbintensity.setAdapter(adapter2);

        bulbintensity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Status")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
                if (parent.getItemAtPosition(position).equals("Off")) {

                }
                if (parent.getItemAtPosition(position).equals("Low")) {
                }
                if (parent.getItemAtPosition(position).equals("Medium")) {
                }
                if (parent.getItemAtPosition(position).equals("High")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void StoveOn() {
        ArrayList<String> bulb = new ArrayList<>();
        bulb.add(0, "Time");
        bulb.add("1");
        bulb.add("2");
        bulb.add("3");
        bulb.add("4");
        bulb.add("5");
        bulb.add("6");
        bulb.add("7");
        bulb.add("8");
        bulb.add("9");
        bulb.add("10");
        bulb.add("11");
        bulb.add("12");
        bulb.add("13");
        bulb.add("14");
        bulb.add("15");
        bulb.add("16");
        bulb.add("17");
        bulb.add("18");
        bulb.add("19");
        bulb.add("20");
        bulb.add("21");
        bulb.add("22");
        bulb.add("23");
        bulb.add("24");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        stoveon.setAdapter(adapter2);

        stoveon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Time")) {

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
    private void StoveOff() {
        ArrayList<String> bulb = new ArrayList<>();
        bulb.add(0, "Time");
        bulb.add("1");
        bulb.add("2");
        bulb.add("3");
        bulb.add("4");
        bulb.add("5");
        bulb.add("6");
        bulb.add("7");
        bulb.add("8");
        bulb.add("9");
        bulb.add("10");
        bulb.add("11");
        bulb.add("12");
        bulb.add("13");
        bulb.add("14");
        bulb.add("15");
        bulb.add("16");
        bulb.add("17");
        bulb.add("18");
        bulb.add("19");
        bulb.add("20");
        bulb.add("21");
        bulb.add("22");
        bulb.add("23");
        bulb.add("24");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        stoveoff.setAdapter(adapter2);

        stoveoff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Time")) {

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

    private void Refrigrator(){
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Status");
        windowblind.add("ON");
        windowblind.add("OFF");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        refrigrator.setAdapter(adapter2);

        refrigrator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Status")) {
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
                if (parent.getItemAtPosition(position).equals("ON")) {

                }
                if (parent.getItemAtPosition(position).equals("OFF")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



}
