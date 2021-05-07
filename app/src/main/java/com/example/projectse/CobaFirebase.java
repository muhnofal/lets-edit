package com.example.projectse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CobaFirebase extends AppCompatActivity {

    private TextView et_nama, et_rating;
    private ImageView iv_foto;
    private Button SendData;
    private DatabaseReference dbref;
    private Member member;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba_firebase);


        et_nama = (TextView) findViewById(R.id.Namaaaa);
        et_rating = (TextView) findViewById(R.id.alamat);
        SendData = (Button) findViewById(R.id.SendData);
        iv_foto = (ImageView) findViewById(R.id.gambarrrr);

        member = new Member();
        dbref = FirebaseDatabase.getInstance().getReference().child("mahasiswa");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("Nama").getValue().toString();

                et_nama.setText(name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        SendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
