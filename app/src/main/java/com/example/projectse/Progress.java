package com.example.projectse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.projectse.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Progress extends AppCompatActivity {

    DatabaseReference reference;
    ArrayList<Data_Sender> list;
    RecyclerViewProgress adapter;
    RecyclerView mRecylcer;
    FirebaseUser user;
    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


        LinearLayoutManager mManager;

        mRecylcer = findViewById(R.id.progressrecycler);
        mRecylcer.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mRecylcer.setLayoutManager(mManager);

        reference = FirebaseDatabase.getInstance().getReference().child("user").child(uid).child("inbox");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Data_Sender data_sender = dataSnapshot1.getValue(Data_Sender.class);
                    list.add(data_sender);
                }
                adapter = new RecyclerViewProgress(getApplicationContext(), list);
                mRecylcer.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_LONG).show();
            }
        });



    }
}
