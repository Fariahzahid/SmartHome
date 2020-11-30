package com.example.smart_home.Contact_Person_SignIn;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smart_home.Contact_Person_Screen.Contact_Person_Users_List;
import com.example.smart_home.GlobalVariables;
import com.example.smart_home.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    //INITIALIZING THE ELEMENTS
    EditText email, password;
    String regemail,regpassword;
    Button contact_person_login,contact_person_signup;
    Button forgetPassword;

    //STRINGS
    private String userID;

    //FIREBASE VARIABLES
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_person_login);


        email = (EditText) findViewById(R.id.contact_person_login_email);
        password = (EditText) findViewById(R.id.contact_person_login_password);
        contact_person_login = (Button) findViewById(R.id.contact_person_btn_login);
        contact_person_signup = (Button) findViewById(R.id.contact_person_btn_signup);
        forgetPassword = (Button) findViewById(R.id.contact_person_login_forget_password);
        imageView = (ImageView) findViewById(R.id.Dialog_Image);

        mFirebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        contact_person_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regemail = email.getText().toString().trim();
                regpassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(regemail)){
                    email.setError("Email is required");
                    return;

                }

                if(TextUtils.isEmpty(regpassword)){
                    password.setError("password is empty");
                    return;
                }

                mFirebaseAuth.signInWithEmailAndPassword(regemail,regpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String success = "Success";
                            String note = "User Login";
                            //Toast.makeText(Login.this, "User Logged In.",Toast.LENGTH_SHORT).show();
                            userID =mFirebaseAuth.getCurrentUser().getUid();
                            GlobalVariables globalVariable=(GlobalVariables)getApplication();

                            globalVariable.setUserIDContactPerson(userID);
                            DocumentReference documentReference = fStore.collection("Contact Person").document(userID);

                            documentReference.update("Password",regpassword)
                                    .addOnSuccessListener(new OnSuccessListener<Void>(){
                                        @Override
                                        public void  onSuccess(Void aVoid){
                                            Log.d(TAG,"Documnetsnapshot successfully updated!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener(){
                                        @Override
                                        public void onFailure(@NonNull Exception e){
                                            Log.w(TAG,"Error updating document",e);
                                        }
                                    });
                            //imageView.setImageResource(R.drawable.success);
                            AlertDialogBoxSuccess(Login.this,success,note);


                        }
                        else{
                            String error= "Error";
                            String note = task.getException().getMessage();
                            //imageView.setImageResource(R.drawable.warning);
                            //Toast.makeText(Login.this, "Error."+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            AlertDialogBoxWarn(Login.this,error,note);
                        }
                    }
                });

            }
        });

        contact_person_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email Id to reset link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email for reseting the password
                        String mail = resetMail.getText().toString();
                        mFirebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Reset Link sent to your Email",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,"Error ! Reset link is not sent" +e.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog
                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }

    private  void AlertDialogBoxSuccess(Activity activity, String note, String setetxt){
        String noteone = note;
        String settexttwo= setetxt;
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_box_contact_person);


        TextView notetext = (TextView) dialog.findViewById(R.id.TextNote);
        TextView info = (TextView) dialog.findViewById(R.id.Text);
        imageView = (ImageView) dialog.findViewById(R.id.profileimage);
       // imageView.setImageResource(R.drawable.success);

        notetext.setText(noteone);
        info.setText(settexttwo);

        Button okButton = (Button) dialog.findViewById(R.id.confirmbutton);
        okButton.setText("OK");
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                startActivity(new Intent(Login.this, Contact_Person_Users_List.class));
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
    private  void AlertDialogBoxWarn(Activity activity, String note, String setetxt){
        String noteone = note;
        String settexttwo= setetxt;
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.dialog_box_contact_person_warning);


        TextView notetext = (TextView) dialog.findViewById(R.id.TextNote);
        TextView info = (TextView) dialog.findViewById(R.id.Text);
        imageView = (ImageView) dialog.findViewById(R.id.profileimage);
        // imageView.setImageResource(R.drawable.success);

        notetext.setText(noteone);
        info.setText(settexttwo);

        Button okButton = (Button) dialog.findViewById(R.id.confirmbutton);
        okButton.setText("OK");
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                startActivity(new Intent(Login.this, Contact_Person_Users_List.class));
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
}
