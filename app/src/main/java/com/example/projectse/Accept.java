package com.example.projectse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Accept extends AppCompatActivity {


    Button CheckProfile, Home;
    TextView pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);

//        CheckProfile = (Button) findViewById(R.id.cekprofile);
        Home = (Button) findViewById(R.id.AccHome);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Accept.this, MainActivity.class);
                startActivity(intent2);
            }
        });

//        CheckProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Accept.this, editor.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                String pass = null;
//                startActivityForResult(intent,1);
//                startActivity(intent);
//
//            }
//        });

    }
}
