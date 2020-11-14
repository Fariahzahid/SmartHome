package com.example.smart_home.Contact_Person_Chat_Room;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smart_home.Adapter.NoteAdapter;
import com.example.smart_home.Adapter.UserAdapter;
import com.example.smart_home.Contact_Person_Screen.Contact_Person_Users_List;
import com.example.smart_home.Contact_Person_SignIn.Register;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.MainActivity;
import com.example.smart_home.Model.Chat;
import com.example.smart_home.Model.User;

import com.example.smart_home.Notifications.Token;
import com.example.smart_home.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Contact_Person_Chat_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private List<User> mUsers;
    FirebaseUser fuser;
    private List<String> userslist;
    DatabaseReference reference;
    StorageReference storageReference;
    ImageView profileimage;
    String userid, no ="NO";
    FirebaseAuth fAuth;
    //FIREBASE FIRESTORE
    private UserAdapter adapter;

    //URI
    private Uri mainImageURI;
    //REQUEST CODE
    public static final int REQUEST = 1;
    public static final int FUCK_UP = 2;


    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.contact_person_chat_fragment, container, false);

        profileimage = view.findViewById(R.id.profile_image);

        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        storageReference = FirebaseStorage.getInstance().getReference();
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        mUsers = new ArrayList<>();
        userslist = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 userslist.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    assert chat != null;

                    if(chat.getSender().equals(fuser.getUid())){
                        userslist.add(chat.getReciver());

                    }
                    if(chat.getReciver().equals(fuser.getUid())){
                        userslist.add(chat.getSender());
                    }
                }
                Set<String> hashSet = new HashSet<String>(userslist);

                userslist.clear();

                userslist.addAll(hashSet);


                ReadUsersProfile(view,userslist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateToken();
        readUsers(view);
        return view;

    }
    private void updateToken(){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        Token token= new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

    private void ReadUsersProfile(final View view, List<String> userslist) {

        for(int i =0; i<userslist.size();i++){
            final String UserId = userslist.get(i);
            DocumentReference documentReference = fStore.collection("USER").document(UserId);
//            StorageReference profileRef = storageReference.child("User_Profile/" +UserId+".jpg");
//            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    Picasso.get().load(uri).into(profileimage);
//                }
//            });

            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    String Name = documentSnapshot.getString("Name");
                    String Email =  documentSnapshot.getString("Email");
                    String PhoneNo = documentSnapshot.getString("Phone No");
                    String Address =documentSnapshot.getString("Address");
                    String Gender = documentSnapshot.getString("Gender");
                    String colorblind = documentSnapshot.getString("Color Blindness");
                    String visionimpaired = documentSnapshot.getString("Vision Impairment");
                    String mute = documentSnapshot.getString("Muteness Disability");
                    String deaf = documentSnapshot.getString("Hearing Impairment");
                    String dylexia = documentSnapshot.getString("Dylexia Disorder");
                    String physical = documentSnapshot.getString("Physical Impairment");
                    String contactpersonid = documentSnapshot.getString("ContactPersonUserId");
                    String Password = documentSnapshot.getString("Password");
                    String Role = documentSnapshot.getString("Role");

                    addUsers(UserId,Name,Email,PhoneNo,Address,Gender,colorblind,visionimpaired,mute,deaf,dylexia,physical,contactpersonid,Password,Role);
                }
            });

            //SAVE_NAME_AND_PHOTO(profileimage,UserId);
        }


    }
    private void addUsers(final String userId, final String name, final String email, final String phoneNo, final String address,
                          final String gender, final String colorblind, final String visionimpaired,
                          final String mute, final String deaf, final String dylexia,final String physical,
                          final String cpid, final String password, final String role ){

        final DocumentReference documentReference = fStore.collection("User_Chat").document(userId);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "Document exists!");

                    } else {
                        Log.d(TAG, "Document does not exist!");
                        Map<String, Object> user = new HashMap<>();
                        user.put("UserID", userId);
                        user.put("Name", name);
                        user.put("Email", email);
                        user.put("Password", password);
                        user.put("PhoneNo", phoneNo);
                        user.put("Address", address);
                        user.put("Gender", gender);
                        user.put("Role",  role);
                        user.put("Color Blindness",colorblind);
                        user.put("Dylexia Disorder",dylexia);
                        user.put("Hearing Impairment", deaf);
                        user.put("Physical Impairment",physical);
                        user.put("Vision Impairment", visionimpaired);
                        user.put("Muteness Disability", mute);
                        user.put("ContactPersonUserId",cpid);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                String note = "Success";
                                String settext ="User Profile Created" ;
                                Toast.makeText(getContext(), "Success: User Profile Created", Toast.LENGTH_SHORT).show();

                                // AlertDoalogBoxSuccess(Register.this,note,settext);
                                System.out.println(note +"note   "+settext +"setText");

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                String note = "Failure";
                                String settext ="User Profile not Created" ;
                                Toast.makeText(getContext(), "Failure: User Profile Not Created", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                } else {
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }

        });
        }

    private void readUsers(View view){

        Query query =  fStore.getInstance().collection("User_Chat");
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query,User.class)
                .build();

        adapter = new UserAdapter(options);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteitem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListner(new UserAdapter.OnItemClickListner(){

            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                GlobalVariables globalVariables = (GlobalVariables) getActivity().getApplication();

                User note = documentSnapshot.toObject(User.class);
                userid = documentSnapshot.getId();
                globalVariables.setUserIDUser(userid);//Setting UserID in global Variables

                Intent intent = new Intent(getActivity(), ContactPersonMessageActivity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

}
