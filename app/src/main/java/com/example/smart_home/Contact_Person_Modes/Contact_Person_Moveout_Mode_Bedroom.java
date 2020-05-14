package com.example.smart_home.Contact_Person_Modes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Moveout_Mode_Bedroom extends Fragment {
    private static final String TAG = "MyActivity";

    private String bedroom;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    Button save;

    android.widget.Spinner weather, ac, actemp, heating, heatingtemp, time, winblind, bulb, bulbint, nightlamp, chrgpoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            userID = getArguments().getString("UserID");
            System.out.println(userID + "userid");
            Log.d(TAG, "UserID   " + userID);

        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_contact_person_moveoutmode_bedroom, container, false);


        bedroom = "Move_Out_Mode_BedRoom";

        weather = v.findViewById(R.id.weather_spinner);
        ac = v.findViewById(R.id.ac_spinner);
        actemp = v.findViewById(R.id.ac_temperature_spinner);
        heating = v.findViewById(R.id.heating_spinner);
        heatingtemp = v.findViewById(R.id.heating_temperature_spinner);
        time = v.findViewById(R.id.time_spinner);
        winblind = v.findViewById(R.id.window_blind_spinner);
        bulb = v.findViewById(R.id.bulb_spinner);
        bulbint = v.findViewById(R.id.bulb_intensity_spinner);
        nightlamp = v.findViewById(R.id.night_lamp_spinner);
        chrgpoint = v.findViewById(R.id.charging_point_spinner);
        save = (Button) v.findViewById(R.id.bedroom_save);

//
//        Weather();
//        AcStatus();
//        AcTemperature();
//        HeatingStatus();
//        HeatingTemperature();
//        Time();
//        WindowBlind();
//        Bulb();
//        BulbIntensity();
//        NightLamp();
//        ChargingPoint();

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document();
                Map<String, Object> user = new HashMap<>();
                //user.put("UserID",userID);
                user.put("Weather", weather.getSelectedItem().toString());
                user.put("AC", ac.getSelectedItem().toString());
                user.put("ACtemperature", actemp.getSelectedItem().toString());
                user.put("Heating", heating.getSelectedItem().toString());
                user.put("HeatingTemperature", heatingtemp.getSelectedItem().toString());
                user.put("Time", time.getSelectedItem().toString());
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
}