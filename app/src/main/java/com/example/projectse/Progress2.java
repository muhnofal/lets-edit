package com.example.projectse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Progress2 extends AppCompatActivity {

    TextView nama;
    Button CheckBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress2);

        nama = findViewById(R.id.ProgessMenuName);
        CheckBtn = findViewById(R.id.checktorev);

        final Intent intent = getIntent();

        String Nama = intent.getStringExtra("NAMASENDER");

        nama.setText(Nama);

        CheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Progress2.this, rev.class);
                startActivity(intent1);
            }
        });

    }
}
