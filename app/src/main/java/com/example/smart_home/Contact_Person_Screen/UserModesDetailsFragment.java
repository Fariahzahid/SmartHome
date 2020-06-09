package com.example.smart_home.Contact_Person_Screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executor;

public class UserModesDetailsFragment extends Fragment {
    private static final String TAG = "MyActivity";

    Button edit_profile, mode_settings;
    TextView aname, aemail, aphoneno, aaddress, agender;
    ImageView profileimage;

    String userid;
    //FIREBASE STORAGE
    StorageReference storageReference;
    //String value,userid;
    FirebaseAuth fAuth;

    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_contact_person_user_modes_details, container, false);

        return v;
    }
}