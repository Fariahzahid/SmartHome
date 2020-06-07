package com.example.smart_home.Users_Modes;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class User_Sleep_Mode extends AppCompatActivity {
    String UserId;
    private static final String TAG = "MyActivity";

    String username,useremail,userphonno,useraddress,usergender,
            bedroomlight,bedroomblind,bedroomnightlamp,bedroomchargpoint,
            lrlight,lrblind,lrtablelamp,lrtv,
            kitchenlight,kitchenblind,kitchenstove,kitchenoven,kitchenref,
            wclight,wcfan,
            bedroomac,bedroomactemp,lrac,lractemp,kitchenac,kitchenactemp,wcac,acactemp,
            bedroomheating,bedroomheatingtemp,lrheat,lrheattemp,kitchenheat,kitchenheattemp,wcheat,wcheattemp;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sleep_mode);

        GlobalVariables globalVariable=(GlobalVariables)getApplication();
        UserId = globalVariable.getUserIDUser();

        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(UserId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                username = documentSnapshot.getString("Name");
                useremail= documentSnapshot.getString("Email");
                userphonno = documentSnapshot.getString("PhoneNo");
                useraddress = documentSnapshot.getString("Address");
                usergender = documentSnapshot.getString("Gender");


            }
        });
        final DocumentReference documentReference1 = fStore.collection("USER").document(UserId)
                .collection("Sleep_Mode_Bedroom").document("Bedroom");
        documentReference1.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Log.d(TAG, "ERROR 1......" +String.valueOf(e));
                String error = String.valueOf(e);

                bedroomlight = documentSnapshot.getString("Bulb");
                bedroomblind= documentSnapshot.getString("WindowBlinds");
                bedroomchargpoint = documentSnapshot.getString("ChargingPOints");
                bedroomnightlamp = documentSnapshot.getString("NightLamps");
                //usergender = documentSnapshot.getString("Gender");


            }
        });
        final DocumentReference documentReference2 = fStore.collection("USER").document(UserId)
                .collection("Sleep_Mode_Kitchen").document("Kitchen");
        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                Log.d(TAG, "ERROR......" +String.valueOf(e));
                kitchenlight = documentSnapshot.getString("Bulb");
                kitchenblind= documentSnapshot.getString("WindowBlinds");
                kitchenoven = documentSnapshot.getString("Oven");
                kitchenstove = documentSnapshot.getString("Stove");


            }
        });
        final DocumentReference documentReference3 = fStore.collection("USER").document(UserId)
                .collection("Sleep_Mode_Livingroom").document("LivingRoom");
        documentReference3.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                Log.d(TAG, "ERROR......" +String.valueOf(e));
                lrlight = documentSnapshot.getString("Bulb");
                lrblind= documentSnapshot.getString("WindowBlinds");
                lrtablelamp = documentSnapshot.getString("Table Lamp");
                lrtv = documentSnapshot.getString("Television");


            }
        });
        final DocumentReference documentReference4 = fStore.collection("USER").document(UserId)
                .collection("Sleep_Mode_WC").document("WC");
        documentReference4.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                Log.d(TAG, "ERROR......" +String.valueOf(e));
                wclight = documentSnapshot.getString("Bulb");
                wcfan = documentSnapshot.getString("Extractorfan");


            }
        });

    }
}
