package com.example.smart_home.User_Chat_Room;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.widget.Toolbar;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.smart_home.Adapter.MessageAdapter;
import com.example.smart_home.Contact_Person_Chat_Room.APIService;
        import com.example.smart_home.GlobalVariables;
        import com.example.smart_home.Model.Chat;
        import com.example.smart_home.Notifications.Client;
        import com.example.smart_home.Notifications.Data;
        import com.example.smart_home.Notifications.MyResponse;
        import com.example.smart_home.Notifications.Sender;
        import com.example.smart_home.Notifications.Token;
        import com.example.smart_home.R;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.DocumentSnapshot;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.auth.User;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.StorageReference;
        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class User_Message_Activity extends AppCompatActivity {

    ImageView profile_image;
    TextView username;

    ImageButton btn_send;
    EditText text_send;
    //FirebaseUser fuser;
    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    String userid;
    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;
    MessageAdapter messageAdapter;
    List<Chat> mChat;

    RecyclerView recyclerView;
    StorageReference profileRef;
    Intent intent;
    APIService apiService;

    String senderid,reciverid,sendername,recivername;

    boolean notify = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_chat_room);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        recyclerView = findViewById(R.id.recycle_view_msg);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);

        firebaseAuth = FirebaseAuth.getInstance();

        intent = getIntent();

        userid = intent.getStringExtra("userid");

        final GlobalVariables globalVariable=(GlobalVariables)getApplication();  //Call the global variable class


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify = true;
                String msg = text_send.getText().toString();
                if(!msg.equals("")){
                    senderid = firebaseAuth.getCurrentUser().getUid();
                    reciverid = userid;
                    sendMessage(firebaseAuth.getCurrentUser().getUid(),userid,msg);
                }else
                {
                    Toast.makeText(User_Message_Activity.this,"You cannot send message",Toast.LENGTH_SHORT).show();

                }
                text_send.setText("");
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();

        readMessages(firebaseAuth.getCurrentUser().getUid(),userid);


        final DocumentReference documentReference = fStore.collection("Contact Person").document(userid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                username.setText(documentSnapshot.getString("Name"));
                if(username != null){
                    profileRef = storageReference.child("User_Profile/" +userid+".jpg");
                    profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).into(profile_image);
                        }
                    });

                }else
                {
                    profile_image.setImageResource(R.mipmap.ic_launcher);
                }
            }
        });

    }


    private void sendMessage(final String sender, final String reciver, String message){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object>hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("reciver",reciver);
        hashMap.put("message",message);


        reference.child("Chats").push().setValue(hashMap);

        final String msg =  message;
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if(notify) {

                    final DocumentReference documentReference = fStore.collection("USER").document(sender);

                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            sendername = documentSnapshot.getString("Name");
                            System.out.println("Sender Name : " +sendername);
                            sendNotification(reciver, sendername, msg);


                        }
                    });

                }
                notify = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void sendNotification(final String receiver, final String username, final String message){

        final DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
        Query query = tokens.orderByKey().equalTo(receiver);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Token token = snapshot.getValue(Token.class);
                    Data data = new Data(firebaseAuth.getUid(), R.mipmap.ic_launcher, username + ":  " + message, "New Message"
                            , receiver);

                    Sender sender = new Sender(data, token.getToken());
                    apiService.sendNotifcation(sender)
                            .enqueue(new Callback<MyResponse>() {
                                @Override
                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                    if (response.code() == 200) {
                                        if (response.body().success != 1) {
                                            Toast.makeText(User_Message_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MyResponse> call, Throwable t) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private  void  readMessages(final String myid, final String userid){
        mChat = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mChat.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if(chat.getReciver().equals(myid) && chat.getSender().equals(userid) ||
                            chat.getReciver().equals(userid) && chat.getSender().equals(myid)){

                        mChat.add(chat);
                    }


                    messageAdapter = new MessageAdapter(User_Message_Activity.this,mChat);

                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
