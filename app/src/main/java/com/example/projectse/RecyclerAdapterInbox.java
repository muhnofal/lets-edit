package com.example.projectse;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerAdapterInbox extends RecyclerView.Adapter<RecyclerAdapterInbox.MyViewHolder> {

    private Context context;
    private ArrayList<Data_Sender> data_senders;
    private StorageReference reference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference ref;
    public FirebaseUser user;
    String uid;


    public RecyclerAdapterInbox (Context cont, ArrayList<Data_Sender> data){
        context = cont;
        data_senders = data;
//        reference = FirebaseStorage.getInstance().getReference();
    }


    @NonNull
    @Override
    public RecyclerAdapterInbox.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_inbox ,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapterInbox.MyViewHolder holder, final int position) {
        holder.tv_sendername.setText(data_senders.get(position).getName());

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        ref = FirebaseDatabase.getInstance().getReference().child("user").child(uid);


        //hire menu clickable
        holder.cardview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //ini ke editor.class
                Intent intent = new Intent(context.getApplicationContext(), rev.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("NAMASENDER", data_senders.get(position).getName());

                context.startActivity(intent);

            }

        });

    }

    @Override
    public int getItemCount() {
        return data_senders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_sendername;
        CardView cardview;
        String uid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_sendername = (TextView) itemView.findViewById(R.id.SenderName);
            cardview = (CardView) itemView.findViewById(R.id.CardViewInbox);


        }
    }

}
