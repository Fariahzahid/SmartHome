package com.example.smart_home.Contact_Person_Screen;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smart_home.R;

public class NoteAdapter extends FirestoreRecyclerAdapter<User,NoteAdapter.NoteHolder> {

    private OnItemClickListner listner;
    public NoteAdapter(@NonNull FirestoreRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull User model) {

        holder.list_email.setText(model.getEmail());
        holder.list_name.setText(model.getName());
        String userid = model.getUserID();



    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_cardview,parent,false);
        return new NoteHolder(v);
    }
    public void deleteitem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView list_name;
        TextView list_email;
        Button edit;

        public  NoteHolder(View itemView){
            super(itemView);
            list_name = itemView.findViewById(R.id.user_name);
            list_email = itemView.findViewById(R.id.user_email);
            edit = itemView.findViewById(R.id.user_edit_button);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION  && listner!=null) {
                        listner.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if(position != RecyclerView.NO_POSITION  && listner!=null){
//                        listner.onItemClick(getSnapshots().getSnapshot(position),position);
//                    }
//
//                }
//            });
        }
}
    public interface OnItemClickListner{
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }
    public void setOnItemClickListner(OnItemClickListner listner){
    this.listner = listner;
    }
}