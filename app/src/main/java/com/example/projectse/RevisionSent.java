package com.example.projectse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RevisionSent extends AppCompatActivity {

    Button Homebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_sent);

        Homebtn = (Button) findViewById(R.id.RevisionHome);

        Homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RevisionSent.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
