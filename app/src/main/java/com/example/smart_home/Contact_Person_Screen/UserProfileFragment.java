package com.example.smart_home.Contact_Person_Screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    Button edit_profile;
    TextView aname,aemail,aphoneno,aaddress,agender,
            disabilityone,disabilitytwo,disabilitythree,disabilityfour,disabilityfive,disabilitysix;
    ImageView profileimage;
    String userid, no ="NO";
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

        edit_profile = (Button) v.findViewById(R.id.edit_user_profile);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ContactPersonMessageActivity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });
       userdata(v,userid);
        return v;
    }
    private void userdata(View v,String id){
        aname = (TextView) v.findViewById(R.id.text_cp_user_name_2);
        aemail = (TextView) v.findViewById(R.id.text_cp_user_email_2);
        aphoneno = (TextView) v.findViewById(R.id.text_cp_user_phoneno_2);
        aaddress = (TextView) v.findViewById(R.id.text_cp_user_address_2);
        agender = (TextView) v.findViewById(R.id.text_cp_useraddress4);
        disabilityone = (TextView) v.findViewById(R.id.text_disabilityone);
        disabilitytwo = (TextView) v.findViewById(R.id.text_disabilitytwo);
        disabilitythree = (TextView) v.findViewById(R.id.text_disabilitythree);
        disabilityfour = (TextView) v.findViewById(R.id.text_disabilityfour);
        disabilityfive = (TextView) v.findViewById(R.id.text_disabilityfive);
        disabilitysix = (TextView) v.findViewById(R.id.text_disabilitysix);
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
                aname.setText(documentSnapshot.getString("Name"));
                aemail.setText(documentSnapshot.getString("Email"));
                aphoneno.setText(documentSnapshot.getString("Phone No"));
                aaddress.setText(documentSnapshot.getString("Address"));
                agender.setText(documentSnapshot.getString("Gender"));
                String colorblind = documentSnapshot.getString("Color Blindness");
                String blind = documentSnapshot.getString("Vision Impairment");
                String mute = documentSnapshot.getString("Muteness Disability");
                String deaf = documentSnapshot.getString("Hearing Impairment");
                String dylexia = documentSnapshot.getString("Dylexia Disorder");
                String physical = documentSnapshot.getString("Physical Impairment");


                if(!colorblind.equals(no)){
                    disabilityone.setText("Color Blindness");
                }
                if(!blind.equals(no)){
                    disabilitytwo.setText("Vision Impairment");
                    disabilitytwo.setVisibility(View.VISIBLE);
                }
                if(!mute.equals(no)){
                    disabilitythree.setText("Muteness Disability");
                    disabilitythree.setVisibility(View.VISIBLE);
                }
                if(!deaf.equals(no)){
                    disabilityfour.setText("Hearing Impairment");
                    disabilityfour.setVisibility(View.VISIBLE);
                }
                if(!dylexia.equals(no)){
                    disabilityfive.setText("Dylexia Disorder");
                    disabilityfive.setVisibility(View.VISIBLE);
                }
                if(!physical.equals(no)){
                    disabilitysix.setText("Physical Impairment");
                    disabilitysix.setVisibility(View.VISIBLE);

                }
            }
        });

    }
}
