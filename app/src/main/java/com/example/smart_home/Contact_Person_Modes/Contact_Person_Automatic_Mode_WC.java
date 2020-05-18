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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.smart_home.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Automatic_Mode_WC extends Fragment {

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

    private android.widget.Spinner bulbon,bulboff,bulbonampm,bulboffampm,
            fanon,fanoff,fanonampm,fanoffampm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            userID = getArguments().getString("UserID");
            System.out.println(userID + "userid");
            Log.d(TAG, "UserID   " + userID);

        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_contact_person_automaticmode_wc, container, false);
        bedroom = "Automatic_Mode_WC";

        bulbon = v.findViewById(R.id.bulb_on_automaticmode_wc_spinner);
        bulbonampm = v.findViewById(R.id.bulb_on_ampm_automaticmode_wc_spinner);
        bulboff = v.findViewById(R.id.bulb_off_automaticmode_wc_spinner);
        bulboffampm = v.findViewById(R.id.bulb_off_ampm_automaticmode_wc_spinner);
        fanon = v.findViewById(R.id.extfan_on_automaticmode_wc_spinner);
        fanonampm = v.findViewById(R.id.extfan_onap_automaticmode_wc_spinner);
        fanoff = v.findViewById(R.id.extfan_off_automaticmode_wc_spinner);
        fanoffampm = v.findViewById(R.id.extfan_off_ap_automaticmode_wc_spinner);
        save = (Button) v.findViewById(R.id.wc_automaticmode_save);


        BulbOn();
        BulbOnAmpm();
        BulbOff();
        BulbOffAmPm();

        FanOn();
        FanOnAmPm();
        FanOff();
        FanOffAmPm();


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document();
                Map<String, Object> user = new HashMap<>();
                //user.put("UserID",userID);
                user.put("BulbOn", bulbon.getSelectedItem().toString()+"   " +bulbonampm.getSelectedItem().toString());
                user.put("BulbOff", bulboff.getSelectedItem().toString() +"   " +bulboffampm.getSelectedItem().toString());
                user.put("FanOn", fanon.getSelectedItem().toString() +"  " +fanonampm.getSelectedItem().toString());
                user.put("FanOff", fanoff.getSelectedItem().toString() +"  " +fanoffampm.getSelectedItem().toString());

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
                if (parent.getItemAtPosition(position).equals("2")) {
                }
                if (parent.getItemAtPosition(position).equals("3")) {
                }
                if (parent.getItemAtPosition(position).equals("4")) {
                }
                if (parent.getItemAtPosition(position).equals("5")) {
                }
                if (parent.getItemAtPosition(position).equals("6")) {
                }
                if (parent.getItemAtPosition(position).equals("7")) {
                }
                if (parent.getItemAtPosition(position).equals("8")) {
                }
                if (parent.getItemAtPosition(position).equals("9")) {
                }
                if (parent.getItemAtPosition(position).equals("10")) {
                }
                if (parent.getItemAtPosition(position).equals("11")) {
                }
                if (parent.getItemAtPosition(position).equals("12")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void BulbOnAmpm(){
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Hour");
        windowblind.add("AM");
        windowblind.add("PM");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulbonampm.setAdapter(adapter2);

        bulbonampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Hour")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
                if (parent.getItemAtPosition(position).equals("AM")) {

                }
                if (parent.getItemAtPosition(position).equals("PM")) {
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
                if (parent.getItemAtPosition(position).equals("1")) {

                }
                if (parent.getItemAtPosition(position).equals("2")) {
                }
                if (parent.getItemAtPosition(position).equals("3")) {
                }
                if (parent.getItemAtPosition(position).equals("4")) {
                }
                if (parent.getItemAtPosition(position).equals("5")) {
                }
                if (parent.getItemAtPosition(position).equals("6")) {
                }
                if (parent.getItemAtPosition(position).equals("7")) {
                }
                if (parent.getItemAtPosition(position).equals("8")) {
                }
                if (parent.getItemAtPosition(position).equals("9")) {
                }
                if (parent.getItemAtPosition(position).equals("10")) {
                }
                if (parent.getItemAtPosition(position).equals("11")) {
                }
                if (parent.getItemAtPosition(position).equals("12")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void BulbOffAmPm(){
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Hour");
        windowblind.add("AM");
        windowblind.add("PM");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        bulboffampm.setAdapter(adapter2);

        bulboffampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Hour")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
                if (parent.getItemAtPosition(position).equals("AM")) {

                }
                if (parent.getItemAtPosition(position).equals("PM")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void FanOn() {
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

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fanon.setAdapter(adapter2);

        fanon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Time")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
                if (parent.getItemAtPosition(position).equals("1")) {

                }
                if (parent.getItemAtPosition(position).equals("2")) {
                }
                if (parent.getItemAtPosition(position).equals("3")) {
                }
                if (parent.getItemAtPosition(position).equals("4")) {
                }
                if (parent.getItemAtPosition(position).equals("5")) {
                }
                if (parent.getItemAtPosition(position).equals("6")) {
                }
                if (parent.getItemAtPosition(position).equals("7")) {
                }
                if (parent.getItemAtPosition(position).equals("8")) {
                }
                if (parent.getItemAtPosition(position).equals("9")) {
                }
                if (parent.getItemAtPosition(position).equals("10")) {
                }
                if (parent.getItemAtPosition(position).equals("11")) {
                }
                if (parent.getItemAtPosition(position).equals("12")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void FanOnAmPm(){
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Hour");
        windowblind.add("AM");
        windowblind.add("PM");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fanonampm.setAdapter(adapter2);

        fanonampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Hour")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
                if (parent.getItemAtPosition(position).equals("AM")) {

                }
                if (parent.getItemAtPosition(position).equals("PM")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void FanOff() {
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

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bulb);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fanoff.setAdapter(adapter2);

        fanoffampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Time")) {

                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
                if (parent.getItemAtPosition(position).equals("1")) {

                }
                if (parent.getItemAtPosition(position).equals("2")) {
                }
                if (parent.getItemAtPosition(position).equals("3")) {
                }
                if (parent.getItemAtPosition(position).equals("4")) {
                }
                if (parent.getItemAtPosition(position).equals("5")) {
                }
                if (parent.getItemAtPosition(position).equals("6")) {
                }
                if (parent.getItemAtPosition(position).equals("7")) {
                }
                if (parent.getItemAtPosition(position).equals("8")) {
                }
                if (parent.getItemAtPosition(position).equals("9")) {
                }
                if (parent.getItemAtPosition(position).equals("10")) {
                }
                if (parent.getItemAtPosition(position).equals("11")) {
                }
                if (parent.getItemAtPosition(position).equals("12")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void FanOffAmPm(){
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Hour");
        windowblind.add("AM");
        windowblind.add("PM");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fanoffampm.setAdapter(adapter2);

        fanoffampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Hour")) {
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item, Toast.LENGTH_SHORT).show();
                }
                if (parent.getItemAtPosition(position).equals("AM")) {

                }
                if (parent.getItemAtPosition(position).equals("PM")) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}

