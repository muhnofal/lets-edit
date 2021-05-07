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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewProgress extends RecyclerView.Adapter<RecyclerViewProgress.MyViewHolder> {

    private Context context;
    private ArrayList<Data_Sender> data_senders;
    private StorageReference reference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference ref;
    FirebaseUser user;
    String uid;


    public RecyclerViewProgress (Context cont, ArrayList<Data_Sender> data){
        context = cont;
        data_senders = data;
        reference = FirebaseStorage.getInstance().getReference();
    }


    @NonNull
    @Override
    public RecyclerViewProgress.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desainprogresscard ,parent, false);
        return new RecyclerViewProgress.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewProgress.MyViewHolder holder, final int position) {

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        ref = FirebaseDatabase.getInstance().getReference().child("user").child(uid).child("inbox");

        holder.tv_sendername.setText(data_senders.get(position).getName());

        //hire menu clickable
        holder.cardview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //ini ke editor.class
                Intent intent = new Intent(context.getApplicationContext(), Progress2.class);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_sendername;
        CardView cardview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_sendername = (TextView) itemView.findViewById(R.id.ProgresName);
            cardview = (CardView) itemView.findViewById(R.id.progresscard);


        }

    }
}
