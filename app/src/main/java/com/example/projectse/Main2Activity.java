package com.example.projectse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    DatabaseReference reference;
    ArrayList<Data_Editor> list;
    RecyclerViewAdapter adapter;
    RecyclerView mRecylcer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        GridLayoutManager mManager;

        mRecylcer = findViewById(R.id.RecycleView_iddd);
        mRecylcer.setHasFixedSize(true);
        mManager = new GridLayoutManager(this,2);
        mRecylcer.setLayoutManager(mManager);

        reference = FirebaseDatabase.getInstance().getReference().child("dataeditor");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Data_Editor editor = dataSnapshot1.getValue(Data_Editor.class);
                    list.add(editor);
                }
                adapter = new RecyclerViewAdapter(getApplicationContext(), list);
                mRecylcer.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_LONG).show();
            }
        });

    }


}
