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

public class Contact_Person_Automatic_Mode_Bedroom extends Fragment {

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

    private android.widget.Spinner bulbonmin,bulboffmin,bulbon,bulboff,bulbintensity,
            nightlampon,nightlampoff,nightlamponmin,nightlampoffmin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GlobalVariables globalVariables =(GlobalVariables)getActivity().getApplication();
        userID = globalVariables.getUserIDUser();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_contact_person_automaticmode_bedroom, container, false);
        bedroom = "Automatic_Mode_BedRoom";

        bulbon = v.findViewById(R.id.bulb_on_automaticmode_bedroom_spinner);
        bulbonmin = v.findViewById(R.id.bulb_onmin_automaticmode_bedroom_spinner);
        bulboff = v.findViewById(R.id.bulb_off_automaticmode_bedroom_spinner);
        bulboffmin = v.findViewById(R.id.bulb_offmin_automaticmode_bedroom_spinner);
        bulbintensity = v.findViewById(R.id.bulb_intensity_automaticmode_bedroom_spinner);
        nightlampon = v.findViewById(R.id.nl_on_automaticmode_bedroom_spinner);
        nightlamponmin = v.findViewById(R.id.nl_onmin_automaticmode_bedroom_spinner);
        nightlampoff = v.findViewById(R.id.nl_off_automaticmode_bedroom_spinner);
        nightlampoffmin = v.findViewById(R.id.nl_offmin_automaticmode_bedroom_spinner);

        save = (Button) v.findViewById(R.id.bedroom_automaticmode_save);


        BulbOn();
        BulbOnMin();
        BulbOff();
        BulbOffMin();
        BulbIntensity();
        NightLampOn();
        NightLampOnMin();
        NightLampOff();
        NIghtLampOffMin();


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document("Bedroom");
                Map<String, Object> user = new HashMap<>();
                user.put("BulbOn", bulbon.getSelectedItem()+":"+bulbonmin.getSelectedItem());
                user.put("BulbOff", bulboff.getSelectedItem() +":" +bulboffmin.getSelectedItem());
                user.put("BulbIntensity", bulbintensity.getSelectedItem());
                user.put("NightLampOn", nightlampon.getSelectedItem()+":" +nightlamponmin.getSelectedItem());
                user.put("NightLampOff", nightlampoff.getSelectedItem()+":"+nightlampoffmin.getSelectedItem());

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
                if (parent.getItemAtPosition(position).equals("1")) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void BulbOnMin() {
        ArrayList<String> bulb = new ArrayList<>();
        bulb.add(0, "Min");
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
        bulb.add("25");
        bulb.add("26");
        bulb.add("27");
        bulb.add("28");
        bulb.add("29");
        bulb.add("30");
        bulb.add("31");
        bulb.add("32");
        bulb.add("33");
        bulb.add("34");
        bulb.add("35");
        bulb.add("36");
        bulb.add("37");
        bulb.add("38");
        bulb.add("39");
        bulb.add("40");
        bulb.add("41");
        bulb.add("42");
        bulb.add("43");
        bulb.add("44");
        bulb.add("45");
        bulb.add("46");
        bulb.add("47");
        bulb.add("48");
        bulb.add("49");
        bulb.add("50");
        bulb.add("51");
        bulb.add("52");
        bulb.add("53");
        bulb.add("54");
        bulb.add("55");
        bulb.add("56");
        bulb.add("57");
        bulb.add("58");
        bulb.add("59");
        bulb.add("00");





        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulbonmin.setAdapter(adapter2);

        bulbonmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Min")) {

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
    private void BulbOffMin(){
        ArrayList<String> bulb = new ArrayList<>();
        bulb.add(0, "Min");
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
        bulb.add("25");
        bulb.add("26");
        bulb.add("27");
        bulb.add("28");
        bulb.add("29");
        bulb.add("30");
        bulb.add("31");
        bulb.add("32");
        bulb.add("33");
        bulb.add("34");
        bulb.add("35");
        bulb.add("36");
        bulb.add("37");
        bulb.add("38");
        bulb.add("39");
        bulb.add("40");
        bulb.add("41");
        bulb.add("42");
        bulb.add("43");
        bulb.add("44");
        bulb.add("45");
        bulb.add("46");
        bulb.add("47");
        bulb.add("48");
        bulb.add("49");
        bulb.add("50");
        bulb.add("51");
        bulb.add("52");
        bulb.add("53");
        bulb.add("54");
        bulb.add("55");
        bulb.add("56");
        bulb.add("57");
        bulb.add("58");
        bulb.add("59");
        bulb.add("00");





        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulboffmin.setAdapter(adapter2);

        bulboffmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Min")) {

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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void NightLampOn() {
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
        nightlampon.setAdapter(adapter2);

        nightlampon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void NightLampOnMin(){
        ArrayList<String> bulb = new ArrayList<>();
        bulb.add(0, "Min");
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
        bulb.add("25");
        bulb.add("26");
        bulb.add("27");
        bulb.add("28");
        bulb.add("29");
        bulb.add("30");
        bulb.add("31");
        bulb.add("32");
        bulb.add("33");
        bulb.add("34");
        bulb.add("35");
        bulb.add("36");
        bulb.add("37");
        bulb.add("38");
        bulb.add("39");
        bulb.add("40");
        bulb.add("41");
        bulb.add("42");
        bulb.add("43");
        bulb.add("44");
        bulb.add("45");
        bulb.add("46");
        bulb.add("47");
        bulb.add("48");
        bulb.add("49");
        bulb.add("50");
        bulb.add("51");
        bulb.add("52");
        bulb.add("53");
        bulb.add("54");
        bulb.add("55");
        bulb.add("56");
        bulb.add("57");
        bulb.add("58");
        bulb.add("59");
        bulb.add("00");





        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        nightlamponmin.setAdapter(adapter2);

        nightlamponmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Min")) {

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
    private void NightLampOff() {
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
        nightlampoff.setAdapter(adapter2);

        nightlampoff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void NIghtLampOffMin(){
        ArrayList<String> bulb = new ArrayList<>();
        bulb.add(0, "Min");
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
        bulb.add("25");
        bulb.add("26");
        bulb.add("27");
        bulb.add("28");
        bulb.add("29");
        bulb.add("30");
        bulb.add("31");
        bulb.add("32");
        bulb.add("33");
        bulb.add("34");
        bulb.add("35");
        bulb.add("36");
        bulb.add("37");
        bulb.add("38");
        bulb.add("39");
        bulb.add("40");
        bulb.add("41");
        bulb.add("42");
        bulb.add("43");
        bulb.add("44");
        bulb.add("45");
        bulb.add("46");
        bulb.add("47");
        bulb.add("48");
        bulb.add("49");
        bulb.add("50");
        bulb.add("51");
        bulb.add("52");
        bulb.add("53");
        bulb.add("54");
        bulb.add("55");
        bulb.add("56");
        bulb.add("57");
        bulb.add("58");
        bulb.add("59");
        bulb.add("00");





        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        nightlampoffmin.setAdapter(adapter2);

        nightlampoffmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Min")) {

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
