package com.example.projectse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView username;
    ImageView checkprofile;
    private CardView hireCard, inboxCard, progressCard;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Defining card
        setContentView(R.layout.activity_main);

        hireCard = (CardView) findViewById(R.id.hire);
        inboxCard = (CardView) findViewById(R.id.inbox);
        progressCard = (CardView) findViewById(R.id.progress);
        username = (TextView) findViewById(R.id.NamaUser);
        checkprofile = (ImageView) findViewById(R.id.profile);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nama = dataSnapshot.child("user").child(uid).child("nama").getValue(String.class);

                username.setText(nama);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        checkprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });


        hireCard.setOnClickListener(this);
        inboxCard.setOnClickListener(this);
        progressCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {

            case R.id.hire:
                Intent hire_Menu = new Intent(this, Main2Activity.class);
                startActivity(hire_Menu);

                break;

            case R.id.inbox:i = new Intent(this, Inbox.class); startActivity(i) ;break;
            case R.id.progress:i = new Intent(this, Progress.class); startActivity(i) ;break;
            default:break;

        }


    }
}
