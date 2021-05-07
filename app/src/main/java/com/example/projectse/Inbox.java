package com.example.projectse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Inbox extends AppCompatActivity {

    DatabaseReference reference;
    ArrayList<Data_Sender> list;
    RecyclerAdapterInbox adapter;
    RecyclerView mRecylcer;
    FirebaseUser user;
    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        LinearLayoutManager mManager;

        mRecylcer = findViewById(R.id.RecycleView2_id);
        mRecylcer.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mRecylcer.setLayoutManager(mManager);

        reference = FirebaseDatabase.getInstance().getReference().child("user").child(uid).child("inbox");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Data_Sender sender = dataSnapshot1.getValue(Data_Sender.class);
                    list.add(sender);
                }
                adapter = new RecyclerAdapterInbox(getApplicationContext(), list);
                mRecylcer.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_LONG).show();
            }
        });

    }



}

//        ListEditor = new ArrayList<>();
//
//        ListEditor.add(new Data_Sender(R.drawable.fotoaisyah, "Aisyah", "13 PM (5jam yang lalu)", "Hi, Malih projectmu telah selesai, silahkan di cek"));
//        ListEditor.add(new Data_Sender(R.drawable.fotobambang, "Bambang", "15 PM (7jam yang lalu)", "Project telah selesai, silahkan di cek"));
//
//
//        RecyclerView myrv = (RecyclerView) findViewById(R.id.RecycleView2_id);
//        RecyclerAdapterInbox myadapter = new RecyclerAdapterInbox(this,ListEditor);
//        myrv.setLayoutManager(new GridLayoutManager(this,1));
//        myrv.setAdapter(myadapter);