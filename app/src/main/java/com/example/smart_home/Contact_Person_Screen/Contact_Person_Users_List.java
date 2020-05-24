package com.example.smart_home.Contact_Person_Screen;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_home.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;


public class Contact_Person_Users_List extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    String userid;
    FirebaseAuth fAuth;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private CollectionReference notebook = fStore.collection("USER");
    RecyclerView recycle;
    //private FirestoreRecyclerAdapter adapter;
    private NoteAdapter adapter;

    Context context;
    ArrayList<User> users;


    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_user_list);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userID");
        System.out.println(userid +"USER ID ==");

        bottomNavigation = findViewById(R.id.bottomNavView_Bar);
        buttomNavigationBar(userid);


        FloatingActionButton addNewUser = findViewById(R.id.button_add_user);
        addNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Contact_Person_Users_List.this,Contact_Person_Add_New_Users.class));
            }
        });

        setUpRecycleView();
    }

    private void setUpRecycleView(){
        Query query =  fStore.getInstance().collection("USER");
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query,User.class)
                .build();

        adapter = new NoteAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                User note = documentSnapshot.toObject(User.class);
                String userid = documentSnapshot.getId();
               // String path = documentSnapshot.getReference().getPath();

                Intent intent = new Intent(Contact_Person_Users_List.this,Contact_Person_User_Profile.class);
                intent.putExtra("UserId",userid);
                //Log.d(TAG,"Button is click");
                Toast.makeText(Contact_Person_Users_List.this,"Position" +position+"ID------" +userid,Toast.LENGTH_SHORT).show();
                startActivity(intent);
                System.out.println("Name 2"  +userid);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

        private void buttomNavigationBar(final String user_id){

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_profile:
                        Intent a = new Intent(Contact_Person_Users_List.this,Contact_Person_Profile.class);
                        a.putExtra("userID",user_id);
                        startActivity(a);
                        break;
                    case R.id.navigation_notifications:
                        Intent b = new Intent(Contact_Person_Users_List.this,Contact_Person_Notification.class);
                        b.putExtra("userID",user_id);
                        startActivity(b);
                        break;

                }
                return false;
            }
        });
    }
}


