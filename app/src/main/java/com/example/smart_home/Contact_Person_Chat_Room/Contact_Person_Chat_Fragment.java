package com.example.smart_home.Contact_Person_Chat_Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smart_home.Adapter.NoteAdapter;
import com.example.smart_home.Contact_Person_Screen.Contact_Person_Users_List;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.MainActivity;
import com.example.smart_home.Model.Chat;
import com.example.smart_home.Model.User;
import com.example.smart_home.R;
import com.example.smart_home.User_Modes_Black_White.User_Home;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Contact_Person_Chat_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private List<User> mUsers;
    String userid;
    FirebaseUser fuser;
    private FirebaseFirestore fStore;
    private List<String> userslist;
    DatabaseReference reference;

    StorageReference storageReference;
    DocumentReference documentReference;
    private NoteAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.contact_person_chat_fragment, container, false);

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


               // readchats(view);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;

    }

//    private void readchats(final View view) {
//        mUsers = new ArrayList<>();
//        storageReference = FirebaseStorage.getInstance().getReference();
//        fStore = FirebaseFirestore.getInstance();
//
//        for (int i = 0;i<userslist.size();i++) {
//            String id = null;
//            id = userslist.get(i);
//            int j = 1;
//            System.out.println(j + "UserId   : " + id);
////            final DocumentReference documentReference = fStore.collection("USER").document(id);
////            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
////                @Override
////                public void onSuccess(DocumentSnapshot documentSnapshot) {
////                    String userid = documentSnapshot.getString("UserID");
////                    System.out.println(userid +documentSnapshot.getString("Name"));
////                }
////            });
//
//            DocumentReference documentReference = fStore.collection("USER").document(id);
//            final String finalId = id;
//            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                @Override
//                public void onSuccess(DocumentSnapshot documentSnapshot) {
//                    String userId = documentSnapshot.getString("UserID");
//
//                    Query query = fStore.getInstance().collection("USER").whereEqualTo(userId, finalId);
//                    FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
//                            .setQuery(query, User.class)
//                            .build();
//
//                    adapter = new NoteAdapter(options);
//
//                    RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
//                    recyclerView.setHasFixedSize(true);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                    recyclerView.setAdapter(adapter);
//
//                    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//                        @Override
//                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                            return false;
//                        }
//
//                        @Override
//                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                            adapter.deleteitem(viewHolder.getAdapterPosition());
//                        }
//                    }).attachToRecyclerView(recyclerView);
//
//
//                    adapter.setOnItemClickListner(new NoteAdapter.OnItemClickListner() {
//
//                        @Override
//                        public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
//                            GlobalVariables globalVariables = (GlobalVariables) getActivity().getApplication();
//
//                            User note = documentSnapshot.toObject(User.class);
//                            userid = documentSnapshot.getId();
//                            globalVariables.setUserIDUser(userid);//Setting UserID in global Variables
//
//                            Intent intent = new Intent(getActivity(), ContactPersonMessageActivity.class);
//                            intent.putExtra("userid", userid);
//                            startActivity(intent);
//                        }
//                    });
//                }
//            });
//        }
//
//    }

}
