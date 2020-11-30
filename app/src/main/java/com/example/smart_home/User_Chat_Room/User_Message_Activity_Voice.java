package com.example.smart_home.User_Chat_Room;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.Contact_Person_Chat_Room.APIService;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.Notifications.Client;
import com.example.smart_home.Notifications.Data;
import com.example.smart_home.Notifications.MyResponse;
import com.example.smart_home.Notifications.Sender;
import com.example.smart_home.Notifications.Token;
import com.example.smart_home.R;
import com.example.smart_home.User_Modes_Black_White.User_Home;
import com.example.smart_home.User_Voice_Mode.User_Home_Weather;
import com.example.smart_home.User_Voice_Mode.User_Home_Wooden_Voice_Mode;
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
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class User_Message_Activity_Voice extends AppCompatActivity {

    private String userID;
    private String contactPersonId;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference databaseReference;

    APIService apiService;
    boolean notify = false;
    String message;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_chat_room);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        final GlobalVariables globalVariable=(GlobalVariables)getApplication();  //Call the global variable class


        notify = true;
        userID = firebaseAuth.getCurrentUser().getUid();
        updateToken();
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);

        final DocumentReference documentReference = firebaseFirestore.collection("USER").document(userID);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String contactPersonId = documentSnapshot.getString("ContactPersonUserId");
                String UserName = documentSnapshot.getString("Name");
                String PhoneNo = documentSnapshot.getString("Phone No");
                message = "User  " +UserName +"   Wants To Contact with you.    " +"Phone Number :  "+PhoneNo;

                sendMessage(userID, contactPersonId, message);
            }
        });
    }


    private void sendMessage(final String sender, final String reciver, String message){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap = new HashMap<>();
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

                    final DocumentReference documentReference = firebaseFirestore.collection("USER").document(sender);

                    documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String sendername = documentSnapshot.getString("Name");
                            //System.out.println("Sender Name : " +sendername);
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

    private void updateToken(){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken= FirebaseInstanceId.getInstance().getToken();
        Token token= new Token(refreshToken);
        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
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
                                        String success= "SUCCESS";
                                        String note = "Message Sent";
                                        AlertDialogBoxSuccess(User_Message_Activity_Voice.this,success,note);
                                        // Toast.makeText(Test_Chatting.this, "Success", Toast.LENGTH_SHORT).show();

                                        if (response.body().success != 1) {
                                            String warn= "WARNING";
                                            String note2 = "Message Not Sent";
                                            AlertDialogBoxWarning(User_Message_Activity_Voice.this,success,note);
                                            Toast.makeText(User_Message_Activity_Voice.this, "Failed", Toast.LENGTH_SHORT).show();
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
    private  void AlertDialogBoxSuccess(Activity activity, String note, String setetxt){
        String noteone = note;
        String settexttwo= setetxt;
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_box_user_wooden);


        TextView notetext = (TextView) dialog.findViewById(R.id.TextNote);
        TextView info = (TextView) dialog.findViewById(R.id.Text);
        // imageView.setImageResource(R.drawable.success);

        notetext.setText(noteone);
        info.setText(settexttwo);

        Button okButton = (Button) dialog.findViewById(R.id.confirmbutton);
        okButton.setText("OK");
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                startActivity(new Intent(User_Message_Activity_Voice.this, User_Home_Wooden_Voice_Mode.class));
                dialog.dismiss();
            }
        });
        Button CancelButton = (Button) dialog.findViewById(R.id.cancelbutton);
        CancelButton.setText("Cancel");
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                //startActivity(new Intent(Login.this, Contact_Person_Users_List.class));
                //dialog.dismiss();
                finish();
            }
        });

        dialog.show();

    }
    private  void AlertDialogBoxWarning(Activity activity, String note, String setetxt){
        String noteone = note;
        String settexttwo= setetxt;
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_box_user_wooden_warning);


        TextView notetext = (TextView) dialog.findViewById(R.id.TextNote);
        TextView info = (TextView) dialog.findViewById(R.id.Text);
        // imageView.setImageResource(R.drawable.success);

        notetext.setText(noteone);
        info.setText(settexttwo);

        Button okButton = (Button) dialog.findViewById(R.id.confirmbutton);
        okButton.setText("OK");
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                startActivity(new Intent(User_Message_Activity_Voice.this, User_Home_Weather.class));
                dialog.dismiss();
            }
        });
        Button CancelButton = (Button) dialog.findViewById(R.id.cancelbutton);
        CancelButton.setText("Cancel");
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                //startActivity(new Intent(Login.this, Contact_Person_Users_List.class));
                //dialog.dismiss();
                finish();
            }
        });

        dialog.show();

    }

}
