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

    private android.widget.Spinner windowblindOn,windowblindOff,windowblindOnampm,windowblindOffampm,
            bulbon,bulboff,bulbonampm,bulboffampm, bulbintensity,
            nightlampon,nightlampoff,nightlamponampm,nightlampoffampm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GlobalVariables globalVariables =(GlobalVariables)getActivity().getApplication();
        userID = globalVariables.getUserIDUser();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_contact_person_automaticmode_bedroom, container, false);
        bedroom = "Automatic_Mode_BedRoom";

        windowblindOn = v.findViewById(R.id.windowblind_on_automaticmode_bedroom_spinner);
        windowblindOnampm = v.findViewById(R.id.windowblind_onampm_automaticmode_bedroom_spinner);
        windowblindOff = v.findViewById(R.id.windowblind_off_automaticmode_bedroom_spinner);
        windowblindOffampm = v.findViewById(R.id.windowblind_offampm_automaticmode_bedroom_spinner);
        bulbon = v.findViewById(R.id.bulb_on_automaticmode_bedroom_spinner);
        bulbonampm = v.findViewById(R.id.bulb_onampm_automaticmode_bedroom_spinner);
        bulboff = v.findViewById(R.id.bulb_off_automaticmode_bedroom_spinner);
        bulboffampm = v.findViewById(R.id.bulb_off_ampm_automaticmode_bedroom_spinner);
        bulbintensity = v.findViewById(R.id.bulb_intensity_automaticmode_bedroom_spinner);
        nightlampon = v.findViewById(R.id.nl_on_automaticmode_bedroom_spinner);
        nightlamponampm = v.findViewById(R.id.nl_onampm_automaticmode_bedroom_spinner);
        nightlampoff = v.findViewById(R.id.nl_off_automaticmode_bedroom_spinner);
        nightlampoffampm = v.findViewById(R.id.nl_off_ampm_automaticmode_bedroom_spinner);
        save = (Button) v.findViewById(R.id.bedroom_automaticmode_save);

        WindowBlindOn();
        WindowBlindOnAmPm();
        WindowBlindOff();
        WindowBlindOffAmPm();
        BulbOn();
        BulbOnAmpm();
        BulbOff();
        BulbOffAmPm();
        BulbIntensity();
        NightLampOn();
        NightLampOnAmPm();
        NightLampOff();
        NightLampOffAmPm();


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document();
                Map<String, Object> user = new HashMap<>();
                //user.put("UserID",userID);
                user.put("WindowBlindsON", windowblindOn.getSelectedItem().toString() +"   " +windowblindOnampm.getSelectedItem().toString());
                user.put("WindowBlindsOFF", windowblindOff.getSelectedItem().toString() +"   " +windowblindOffampm.getSelectedItem().toString());
                user.put("BulbOn", bulbon.getSelectedItem().toString()+"   " +bulbonampm.getSelectedItem().toString());
                user.put("BulbOff", bulboff.getSelectedItem().toString() +"   " +bulboffampm.getSelectedItem().toString());
                user.put("BulbIntensity", bulbintensity.getSelectedItem().toString());
                user.put("NightLampOn", nightlampon.getSelectedItem().toString() +"  " +nightlamponampm.getSelectedItem().toString());
                user.put("NightLampOff", nightlampoff.getSelectedItem().toString() +"  " +nightlampoffampm.getSelectedItem().toString());

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
    private void WindowBlindOnAmPm(){
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Hour");
        windowblind.add("AM");
        windowblind.add("PM");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        windowblindOnampm.setAdapter(adapter2);

        windowblindOnampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("")) {

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
    private void WindowBlindOffAmPm(){
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Hour");
        windowblind.add("AM");
        windowblind.add("PM");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        windowblindOffampm.setAdapter(adapter2);

        windowblindOffampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void NightLampOnAmPm(){
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Hour");
        windowblind.add("AM");
        windowblind.add("PM");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        nightlamponampm.setAdapter(adapter2);

        nightlamponampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void NightLampOffAmPm(){
        ArrayList<String> windowblind = new ArrayList<>();
        windowblind.add(0, "Hour");
        windowblind.add("AM");
        windowblind.add("PM");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, windowblind);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        nightlampoffampm.setAdapter(adapter2);

        nightlampoffampm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Hour")) {
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
