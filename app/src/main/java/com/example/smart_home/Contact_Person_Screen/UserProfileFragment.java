package com.example.smart_home.Contact_Person_Screen;

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

public class UserProfileFragment extends Fragment {

    private static final String TAG = "MyActivity";

    Button edit_profile,mode_settings;
    TextView aname,aemail,aphoneno,aaddress,agender;
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


        GlobalVariables globalVariables = (GlobalVariables) getActivity().getApplication();
        userid = globalVariables.getUserIDUser();
        View v = inflater.inflate(R.layout.fragment_contact_person_user_profile, container, false);

        storageReference = FirebaseStorage.getInstance().getReference();

       userdata(v,userid);
        return v;
    }
    private void userdata(View v,String id){
        aname = (TextView) v.findViewById(R.id.text_cp_user_name_2);
        aemail = (TextView) v.findViewById(R.id.text_cp_user_email_2);
        aphoneno = (TextView) v.findViewById(R.id.text_cp_user_phoneno_2);
        aaddress = (TextView) v.findViewById(R.id.text_cp_user_address_2);
        agender = (TextView) v.findViewById(R.id.text_cp_useraddress4);
        profileimage = (ImageView) v.findViewById(R.id.cp_user_profile_image);
        fStore = FirebaseFirestore.getInstance();

        final DocumentReference documentReference = fStore.collection("USER").document(id);
        StorageReference profileRef = storageReference.child("User_Profile/" +id+".jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileimage);
            }
        });

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //User user = documentSnapshot.toObject(User.class);
                aname.setText(documentSnapshot.getString("Name"));
                aemail.setText(documentSnapshot.getString("Email"));
                aphoneno.setText(documentSnapshot.getString("Phone No"));
                aaddress.setText(documentSnapshot.getString("Address"));
                agender.setText(documentSnapshot.getString("Gender"));
            }
        });

    }
}
