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

import com.example.smart_home.Contact_Person_Modes.Contact_Person_Automatic_Mode;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Automatic_Mode_Bedroom;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Automatic_Mode_Kitchen;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Automatic_Mode_Livingroom;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Automatic_Mode_WC;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_MoveOut_Mode;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Sleep_Mode_Bedroom;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Sleep_Mode_Kitchen;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Sleep_Mode_Livingroom;
import com.example.smart_home.Contact_Person_Modes.Contact_Person_Sleep_Mode_WC;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UserAutomaticModeFragment extends Fragment {
    Button edit_auto_mode;
    private static final String TAG = "MyActivity";
    TextView edit_bedroom,edit_livingroom,edit_kitchen,edit_wc;
    TextView bedroom_light_on,bedroom_light_off,bedroon_lightintensity,bedroom_blinds_on,bedroom_blinds_off,
            bedroom_lamp_on,bedroom_lamp_off,lr_light_on,lr_light_off,lr_lightintensity,lr_blinds_on,lr_blinds_off,
            lr_lamp_on,lr_lamp_off,lr_tv_on,lr_tv_off,kitchen_light_on,kitchen_light_off,kitchen_lightintensity,kitchen_blinds_on,kitchen_blinds_off,
            kitchen_stove_on,kitchen_stove_off,kitchen_refrigrtor,
            wc_light_on,wc_light_off,wc_fan_on,wc_fan_off;

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
        View v = inflater.inflate(R.layout.fragment_contact_person_user_automatic_mode, container, false);

        storageReference = FirebaseStorage.getInstance().getReference();

        userdatabedroom(v,userid);
        userdatalivingroom(v,userid);
        userdatakitchen(v,userid);
        userdatawc(v,userid);

        edit_bedroom = (TextView) v.findViewById(R.id.auto_bedroom_edit);
        edit_kitchen = (TextView) v.findViewById(R.id.auto_kitchen_edit);
        edit_livingroom = (TextView) v.findViewById(R.id.auto_livingroom_edit);
        edit_wc = (TextView) v.findViewById(R.id.auto_wc_edit);
        edit_bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_Automatic_Mode_Bedroom.class);
                startActivity(intent);
            }
        });
        edit_kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_Automatic_Mode_Kitchen.class);
                startActivity(intent);
            }
        });
        edit_livingroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_Automatic_Mode_Livingroom.class);
                startActivity(intent);
            }
        });
        edit_wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_Automatic_Mode_WC.class);
                startActivity(intent);
            }
        });
        edit_auto_mode = (Button) v.findViewById(R.id.edit_automatic_mode);
        edit_auto_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Contact_Person_Automatic_Mode.class);
                startActivity(intent);
            }
        });
        return v;
    }
    private void userdatabedroom(View v,String id){
        bedroom_light_on = (TextView) v.findViewById(R.id.bedroom_light_on);
        bedroom_light_off = (TextView) v.findViewById(R.id.bedroom_light_off);
        bedroon_lightintensity = (TextView) v.findViewById(R.id.bedroom_light_intensity);
        bedroom_blinds_on = (TextView) v.findViewById(R.id.bedroom_window_blinds_on);
        bedroom_blinds_off = (TextView) v.findViewById(R.id.bedroom_window_blinds_off);
        bedroom_lamp_on = (TextView) v.findViewById(R.id.bedroom_night_lamp_on);
        bedroom_lamp_off = (TextView) v.findViewById(R.id.bedroom_night_lamp_off);

        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id)
                .collection("Automatic_Mode_BedRoom").document("Bedroom");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //User user = documentSnapshot.toObject(User.class);
                String a= documentSnapshot.getString("BulbOn");
                String b = documentSnapshot.getString("BulbOff");
                String c = documentSnapshot.getString("WindowBlindsOn");

                bedroom_light_on.setText(documentSnapshot.getString("BulbOn")+":00");
                bedroom_light_off.setText(documentSnapshot.getString("BulbOff") +":00");
                bedroon_lightintensity.setText(documentSnapshot.getString("BulbIntensity"));
                bedroom_blinds_on.setText(documentSnapshot.getString("WindowBlindsON") +":00");
                bedroom_blinds_off.setText(documentSnapshot.getString("WindowBlindsOFF") +":00");
                bedroom_lamp_on.setText(documentSnapshot.getString("NightLampOn") +":00");
                bedroom_lamp_off.setText(documentSnapshot.getString("NightLampOff") +":00");
            }
        });

    }
    private void userdatalivingroom(View v,String id){
        lr_light_on = (TextView) v.findViewById(R.id.livingroom_light_on);
        lr_light_off = (TextView) v.findViewById(R.id.livingroom_light_off);
        lr_lightintensity = (TextView) v.findViewById(R.id.livingroom_light_intensity);
        lr_blinds_on = (TextView) v.findViewById(R.id.livingroom_window_blinds_on);
        lr_blinds_off = (TextView) v.findViewById(R.id.livingroom_window_blinds_off);
        lr_lamp_on = (TextView) v.findViewById(R.id.livingroom_table_lamp_on);
        lr_lamp_off = (TextView) v.findViewById(R.id.livingroom_table_lamp_off);
        lr_tv_on  = (TextView) v.findViewById(R.id.livingroom_television_on);
        lr_tv_off = (TextView) v.findViewById(R.id.livingroom_television_off);
        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id)
                .collection("Automatic_Mode_LivingRoom").document("LivingRoom");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                lr_light_on.setText(documentSnapshot.getString("BulbOn")+":00");
                lr_light_off.setText(documentSnapshot.getString("BulbOff") +":00");
                lr_lightintensity.setText(documentSnapshot.getString("BulbIntensity"));
                lr_blinds_on.setText(documentSnapshot.getString("WindowBlindsON") +":00");
                lr_blinds_off.setText(documentSnapshot.getString("WindowBlindsOFF") +":00");
                lr_lamp_on.setText(documentSnapshot.getString("TableLampOn") +":00");
                lr_lamp_off.setText(documentSnapshot.getString("TableLampOFF") +":00");
                lr_tv_on.setText(documentSnapshot.getString("TelevisionOn") +":00");
                lr_tv_off.setText(documentSnapshot.getString("TelevisionOff") +":00");

            }
        });
    }
    private void userdatakitchen(View v,String id){
        kitchen_light_on = (TextView) v.findViewById(R.id.kitchen_light_on);
        kitchen_light_off = (TextView) v.findViewById(R.id.kitchen_light_off);
        kitchen_blinds_on = (TextView) v.findViewById(R.id.kitchen_window_blinds_on);
        kitchen_blinds_off = (TextView) v.findViewById(R.id.kitchen_window_blinds_off);
        kitchen_lightintensity = (TextView) v.findViewById(R.id.kitchen_lightintensity);
        kitchen_stove_on  = (TextView) v.findViewById(R.id.kitchen_stove_on);
        kitchen_stove_off = (TextView) v.findViewById(R.id.kitchen_stove_off);
        kitchen_refrigrtor = (TextView) v.findViewById(R.id.kitchen_refrigrator);

        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id)
                .collection("Automatic_Mode_Kitchen").document("Kitchen");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                kitchen_light_on.setText(documentSnapshot.getString("BulbOn")+":00");
                kitchen_light_off.setText(documentSnapshot.getString("BulbOff") +":00");
                kitchen_blinds_on.setText(documentSnapshot.getString("WindowBlindsON") +":00");
                kitchen_blinds_off.setText(documentSnapshot.getString("WindowBlindsOFF") +":00");
                kitchen_lightintensity.setText(documentSnapshot.getString("BulbIntensity"));
                kitchen_stove_on.setText(documentSnapshot.getString("StoveOn") +":00");
                kitchen_stove_off.setText(documentSnapshot.getString("StoveOff") +":00");
                kitchen_refrigrtor.setText(documentSnapshot.getString("Refrigrator"));
            }
        });
    }
    private void userdatawc(View v,String id){
        wc_light_on = (TextView) v.findViewById(R.id.wc_light_on);
        wc_light_off = (TextView) v.findViewById(R.id.wc_light_off);
        wc_fan_on = (TextView) v.findViewById(R.id.wc_extractor_fan_on);
        wc_fan_off = (TextView) v.findViewById(R.id.wc_extractor_fan_off);

        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id)
                .collection("Automatic_Mode_WC").document("WC");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                wc_light_on.setText(documentSnapshot.getString("BulbOn")+":00");
                wc_light_off.setText(documentSnapshot.getString("BulbOff") +":00");
                wc_fan_on.setText(documentSnapshot.getString("FanOn") +":00");
                wc_fan_off.setText(documentSnapshot.getString("FanOff") +":00");

            }
        });
    }
}
