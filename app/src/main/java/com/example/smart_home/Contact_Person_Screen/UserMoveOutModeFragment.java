package com.example.smart_home.Contact_Person_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smart_home.Contact_Person_Modes.Contact_Person_MoveOut_Mode;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Moveout_Mode_Bedroom;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Moveout_Mode_Kitchen;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Moveout_Mode_Livingroom;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Moveout_Mode_WC;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UserMoveOutModeFragment extends Fragment {
    private static final String TAG = "MyActivity";
    Button edit_moveout_mode;
    TextView edit_bedroom,edit_livingroom,edit_kitchen,edit_wc;
    TextView bedroom_light,bedroon_lightintensity,bedroom_blinds,bedroom_lamp,bedroom_charge,
            bedroom_ac,bedroom_ac_temp,bedroom_heating,bedroom_heating_temp,
            lr_light,lr_lightintensity,lr_blinds,lrlamp,lr_tv,lr_ac,lr_ac_temp,lr_heating,lr_heating_temp,
            kitchen_light, kitchen_blinds,kitchen_stove,kitchen_oven,kitchen_refrigrtor,
            kitchen_ac,kitchen_ac_temp,kitchen_heating,kitchen_heating_temp,
            wc_light,wc_fan,wc_heating,wc_heating_temp;

    String userid;
    //FIREBASE STORAGE
    StorageReference storageReference;
    //String value,userid;
    FirebaseAuth fAuth;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        GlobalVariables globalVariables = (GlobalVariables) getActivity().getApplication();
        userid = globalVariables.getUserIDUser();
        View v = inflater.inflate(R.layout.fragment_contact_person_user_moveout_mode, container, false);
        storageReference = FirebaseStorage.getInstance().getReference();

        userdatabedroom(v,userid);
        userdatalivingroom(v,userid);
        userdatakitchen(v,userid);
        userdatawc(v,userid);

        edit_bedroom = (TextView) v.findViewById(R.id.moveout_bedroom_edit);
        edit_kitchen = (TextView) v.findViewById(R.id.moveout_kitchen_edit);
        edit_livingroom = (TextView) v.findViewById(R.id.moveout_livingroom_edit);
        edit_wc = (TextView) v.findViewById(R.id.moveout_wc_edit);
        edit_bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_Moveout_Mode_Bedroom.class);
                startActivity(intent);
            }
        });
        edit_kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_Moveout_Mode_Kitchen.class);
                startActivity(intent);
            }
        });
        edit_livingroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_Moveout_Mode_Livingroom.class);
                startActivity(intent);
            }
        });
        edit_wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_Moveout_Mode_WC.class);
                startActivity(intent);
            }
        });
        edit_moveout_mode = (Button) v.findViewById(R.id.edit_moveout_mode);
        edit_moveout_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_MoveOut_Mode.class);
                startActivity(intent);
            }
        });
        return v;
    }
    private void userdatabedroom(View v,String id){
        bedroom_light = (TextView) v.findViewById(R.id.bedroom_light_status);
        bedroon_lightintensity = (TextView) v.findViewById(R.id.bedroom_light_intensity);
        bedroom_blinds = (TextView) v.findViewById(R.id.bedroom_window_blinds);
        bedroom_lamp = (TextView) v.findViewById(R.id.bedroom_night_lamp);
        bedroom_charge = (TextView) v.findViewById(R.id.bedroom_charging_point);
        bedroom_ac = (TextView) v.findViewById(R.id.bedroom_ac);
        bedroom_ac_temp = (TextView) v.findViewById(R.id.bedroom_ac_temp);
        bedroom_heating = (TextView) v.findViewById(R.id.bedroom_heating);
        bedroom_heating_temp = (TextView) v.findViewById(R.id.bedroom_heating_temp);

        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id)
                .collection("Move_Out_Mode_BedRoom").document("Bedroom");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //User user = documentSnapshot.toObject(User.class);
                bedroom_light.setText(documentSnapshot.getString("Bulb"));
                bedroon_lightintensity.setText(documentSnapshot.getString("BulbIntensity"));
                bedroom_blinds.setText(documentSnapshot.getString("WindowBlinds"));
                bedroom_lamp.setText(documentSnapshot.getString("NightLamp"));
                bedroom_charge.setText(documentSnapshot.getString("ChargingPoints"));
                bedroom_ac.setText(documentSnapshot.getString("AC"));
                bedroom_ac_temp.setText(documentSnapshot.getString("ACtemperature"));
                bedroom_heating.setText(documentSnapshot.getString("Heating"));
                bedroom_heating_temp.setText(documentSnapshot.getString("HeatingTemperature"));
            }
        });

    }
    private void userdatalivingroom(View v,String id){
        lr_light = (TextView) v.findViewById(R.id.livingroom_light_status);
        lr_lightintensity = (TextView) v.findViewById(R.id.livingroom_light_intensity);
        lr_blinds = (TextView) v.findViewById(R.id.livingroom_window_blinds);
        lrlamp = (TextView) v.findViewById(R.id.livingroom_table_lamp);
        lr_tv = (TextView) v.findViewById(R.id.livingroom_television);
        lr_ac = (TextView) v.findViewById(R.id.livingroom_ac);
        lr_ac_temp = (TextView) v.findViewById(R.id.livingroom_ac_temp);
        lr_heating = (TextView) v.findViewById(R.id.livingroom_heating);
        lr_heating_temp = (TextView) v.findViewById(R.id.livingroom_heating_temp);


        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id)
                .collection("Move_Out_Mode_LivingRoom").document("LivingRoom");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //User user = documentSnapshot.toObject(User.class);
                lr_light.setText(documentSnapshot.getString("Bulb"));
                lr_lightintensity.setText(documentSnapshot.getString("BulbIntensity"));
                lr_blinds.setText(documentSnapshot.getString("WindowBlinds"));
                lrlamp.setText(documentSnapshot.getString("NightLamp"));
                lr_tv.setText(documentSnapshot.getString("ChargingPoints"));
                lr_ac.setText(documentSnapshot.getString("AC"));
                lr_ac_temp.setText(documentSnapshot.getString("ACtemperature"));
                lr_heating.setText(documentSnapshot.getString("Heating"));
                lr_heating_temp.setText(documentSnapshot.getString("HeatingTemperature"));
            }
        });
    }
    private void userdatakitchen(View v,String id){
        kitchen_light = (TextView) v.findViewById(R.id.kitchen_light_status);
        kitchen_blinds = (TextView) v.findViewById(R.id.kitchen_window_blinds);
        kitchen_oven = (TextView) v.findViewById(R.id.kitchen_oven);
        kitchen_stove = (TextView) v.findViewById(R.id.kitchen_stove);
        kitchen_refrigrtor = (TextView) v.findViewById(R.id.kitchen_refrigrator);
        kitchen_ac = (TextView) v.findViewById(R.id.kitchen_ac);
        kitchen_ac_temp = (TextView) v.findViewById(R.id.kitchen_ac_temp);
        kitchen_heating = (TextView) v.findViewById(R.id.kitchen_heating);
        kitchen_heating_temp = (TextView) v.findViewById(R.id.kitchen_heating_temp);

        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id)
                .collection("Move_Out_Mode_Kitchen").document("Kitchen");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //User user = documentSnapshot.toObject(User.class);
                kitchen_light.setText(documentSnapshot.getString("Bulb"));
                kitchen_blinds.setText(documentSnapshot.getString("WindowBlinds"));
                kitchen_oven.setText(documentSnapshot.getString("Oven"));
                kitchen_stove.setText(documentSnapshot.getString("Stove"));
                kitchen_refrigrtor.setText(documentSnapshot.getString("Refrigrator"));
                kitchen_ac.setText(documentSnapshot.getString("AC"));
                kitchen_ac_temp.setText(documentSnapshot.getString("ACtemperature"));
                kitchen_heating.setText(documentSnapshot.getString("Heating"));
                kitchen_heating_temp.setText(documentSnapshot.getString("HeatingTemperature"));
            }
        });
    }
    private void userdatawc(View v,String id){
        wc_light = (TextView) v.findViewById(R.id.wc_light_status);
        wc_fan = (TextView) v.findViewById(R.id.wc_extractor_fan);
        wc_heating = (TextView) v.findViewById(R.id.wc_heating);
        wc_heating_temp = (TextView) v.findViewById(R.id.wc_heating_temp);

        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id)
                .collection("Move_Out_Mode_WC").document("WC");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //User user = documentSnapshot.toObject(User.class);
                wc_light.setText(documentSnapshot.getString("Bulb"));
                wc_fan.setText(documentSnapshot.getString("ExtractorFan"));
                wc_heating.setText(documentSnapshot.getString("Heating"));
                wc_heating_temp.setText(documentSnapshot.getString("HeatingTemperature"));

            }
        });
    }
}
