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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contact_Person_Sleep_Mode_WC extends Fragment {

    private static final String TAG = "MyActivity";

    private String bedroom;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    TextView temperature;
    Button save;
    String Status;

    private android.widget.Spinner heating, heatingtemp, bulb, fan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        GlobalVariables globalVariables =(GlobalVariables)getActivity().getApplication();
        userID = globalVariables.getUserIDUser();

        View v = inflater.inflate(R.layout.activity_contact_person_sleepmode_wc, container, false);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        bedroom = "Sleep_Mode_WC";

        bulb = v.findViewById(R.id.bulb_sleepmode_wc_spinner);
        fan = v.findViewById(R.id.fan_sleepmode_wc_spinner);
        save = (Button) v.findViewById(R.id.wc_sleepmode_save);

        DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document("WC");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String Fan = documentSnapshot.getString("Extractorfan");
                String Bulb = documentSnapshot.getString("Bulb");


                if(Fan != null){
                    Status = Fan;
                    Fan(Status);
                }else
                {
                    Status = "Select Status";
                    Fan(Status);
                }
                if(Bulb != null){
                    Status = Bulb;
                    Bulb(Status);
                }else
                {
                    Status = "Select Status";
                    Bulb(Status);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = fStore.collection("USER").document(userID).collection(bedroom).document("WC");
                Map<String,Object> user = new HashMap<>();
                user.put("Bulb",bulb.getSelectedItem().toString());
                user.put("Extractorfan",fan.getSelectedItem().toString());

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
    private void Fan(String status){

        ArrayList<String> timearray = new ArrayList<>();
        timearray.add(0, status);
        timearray.add("ON");
        timearray.add("OFF");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, timearray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fan.setAdapter(adapter);

        fan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


