package com.example.smart_home.Contact_Person_Chat_Room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_home.Adapter.MessageAdapter;
import com.example.smart_home.Contact_Person_Screen.Contact_Person_Users_List;
import com.example.smart_home.Contact_Person_SignIn.Login;
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
import com.google.firebase.auth.FirebaseUser;
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

public class ContactPersonMessageActivity extends AppCompatActivity {

    ImageView profile_image;
    TextView username;

    ImageButton btn_send;
    EditText text_send;
    //FirebaseUser fuser;
    DatabaseReference reference;

    FirebaseAuth fuser;
    StorageReference storageReference;
    //String value,userid;
    String userid,uname;
    FirebaseAuth fAuth;
    //FIREBASE FIRESTORE
    private FirebaseFirestore fStore;
    MessageAdapter messageAdapter;
    List<Chat> mChat;

    RecyclerView recyclerView;
    StorageReference profileRef;
    Intent intent;
    APIService apiService;

    String senderid,reciverid,sendername,recivername;
    public static final int REQUEST_CALL = 1;

    boolean notify = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_message);
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

        fuser = FirebaseAuth.getInstance();

        intent = getIntent();

       userid = intent.getStringExtra("userid");
        final GlobalVariables globalVariable=(GlobalVariables)getApplication();  //Call the global variable class

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify = true;
                String msg = text_send.getText().toString();
                if(!msg.equals("")){
                    senderid = fuser.getCurrentUser().getUid();
                    reciverid = userid;
                    sendMessage(fuser.getCurrentUser().getUid(),userid,msg);
                }else
                {
                    Toast.makeText(ContactPersonMessageActivity.this,"You cannot send message",Toast.LENGTH_SHORT).show();
                    text_send.setText("");
                }
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();

        readMessages(fuser.getCurrentUser().getUid(),userid);

        final DocumentReference documentReference = fStore.collection("USER").document(userid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                username.setText(documentSnapshot.getString("Name"));
                String phoneno = documentSnapshot.getString("Phone No");
                String succes = "SUCCESS";
                String note = "Connecting to User";

                AlertDialogBoxSuccess(ContactPersonMessageActivity.this,succes,note,phoneno);
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


    private  void AlertDialogBoxSuccess(Activity activity, String note, String setetxt, final String PhoneNo){
        String noteone = note;
        String settexttwo= setetxt;
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_box_contact_person);


        TextView notetext = (TextView) dialog.findViewById(R.id.TextNote);
        TextView info = (TextView) dialog.findViewById(R.id.Text);
        // imageView.setImageResource(R.drawable.success);

        notetext.setText(noteone);
        info.setText(settexttwo);

        Button okButton = (Button) dialog.findViewById(R.id.confirmbutton);
        okButton.setText("Call");
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(ContactPersonMessageActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ContactPersonMessageActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+PhoneNo));
                    startActivity(callIntent);
                    //startActivity(new Intent(ContactPersonMessageActivity.this, Contact_Person_Users_List.class));
                }

                //startActivity(new Intent(ContactPersonMessageActivity.this, Contact_Person_Users_List.class));
                dialog.dismiss();
            }
        });
        Button CancelButton = (Button) dialog.findViewById(R.id.cancelbutton);
        CancelButton.setText("Cancel");
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog.show();

    }
    private void sendMessage(String sender, final String reciver, String message){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object>hashMap = new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("reciver",reciver);
        hashMap.put("message",message);

        reference.child("Chats").push().setValue(hashMap);


        final String msg =  message;
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if(notify) {

                            final DocumentReference documentReference = fStore.collection("Contact Person").document(fuser.getCurrentUser().getUid());

                            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                         @Override
                                                         public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                             sendername = documentSnapshot.getString("Name");
                                                             System.out.println("Sender Name : " +sendername);
                                                             sendNotification(reciver, sendername, msg);
                                                            // notify = false;


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
                    Data data = new Data(fuser.getUid(), R.mipmap.ic_launcher, username + ":  " + message, "New Message"
                            , userid);

                    Sender sender = new Sender(data, token.getToken());
                    apiService.sendNotifcation(sender)
                            .enqueue(new Callback<MyResponse>() {
                                @Override
                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                    if (response.code() == 200) {
                                        if (response.body().success != 1) {
                                            Toast.makeText(ContactPersonMessageActivity.this, "Failed", Toast.LENGTH_SHORT).show();
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
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mChat.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if(chat.getReciver().equals(myid) && chat.getSender().equals(userid) ||
                            chat.getReciver().equals(userid) && chat.getSender().equals(myid)){

                        mChat.add(chat);
                    }


                    messageAdapter = new MessageAdapter(ContactPersonMessageActivity.this,mChat);

                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
