package com.example.smart_home.Adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_home.Model.User;
import com.example.smart_home.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ContactAdapter extends FirestoreRecyclerAdapter<User,ContactAdapter.NoteHolder> {
    private static final String TAG = "MyActivity";
    private FirebaseFirestore fStore;

    private NoteAdapter.OnItemClickListner listner;
    public ContactAdapter(@NonNull FirestoreRecyclerOptions<User> options) {

        super(options);
    }

    @NonNull
    @Override
    public ContactAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_cardview,parent,false);
        return new ContactAdapter.NoteHolder(v);
    }
    @Override
    protected void onBindViewHolder(@NonNull final ContactAdapter.NoteHolder holder, int position, @NonNull User model) {

        holder.list_email.setText(model.getEmail());
        holder.list_name.setText(model.getName());
        holder.list_userid.setText(model.getUserID());
        String id = model.getUserID().toString();


        fStore = FirebaseFirestore.getInstance();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        final DocumentReference documentReference = fStore.collection("Contact Person").document(id);

        StorageReference profileRef = storageReference.child("profile/" +id+".jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.d(TAG,"Failure");
            }
        });


    }


    public void deleteitem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView list_name;
        TextView list_email;
        ImageView imageView;
        TextView list_userid;
        LinearLayout edit;

        public  NoteHolder(View itemView){
            super(itemView);
            list_name = itemView.findViewById(R.id.user_name);
            list_email = itemView.findViewById(R.id.user_email);
            imageView = itemView.findViewById(R.id.profileImageUser);
            list_userid = itemView.findViewById(R.id.user_id);
            edit = itemView.findViewById(R.id.user_profile_layout);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION  && listner!=null) {
                        listner.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListner(NoteAdapter.OnItemClickListner listner){
        this.listner = listner;
    }
}