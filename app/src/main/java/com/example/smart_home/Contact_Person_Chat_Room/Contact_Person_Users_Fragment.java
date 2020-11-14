package com.example.smart_home.Contact_Person_Chat_Room;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_home.Adapter.NoteAdapter;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.Model.User;
import com.example.smart_home.Notifications.Token;
import com.example.smart_home.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

public class Contact_Person_Users_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private List<User> mUsers;
    String userid;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private NoteAdapter adapter;
    FirebaseAuth fuser;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.contact_person_users_fragment, container, false);
            // Inflate the layout for this fragment

            recyclerView = view.findViewById(R.id.recycle_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            mUsers = new ArrayList<>();

            fuser = FirebaseAuth.getInstance();

            readUsers(view);
            updateToken();
            return view;

        }

     private void updateToken(){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        Token token= new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

    private void readUsers(View view){
            Query query =  fStore.getInstance().collection("USER");
            FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                    .setQuery(query,User.class)
                    .build();

            adapter = new NoteAdapter(options);

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


            adapter.setOnItemClickListner(new NoteAdapter.OnItemClickListner(){

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
