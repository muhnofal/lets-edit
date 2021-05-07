package com.example.projectse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class rev extends AppCompatActivity {

    TextView SenderName;
    Button revisionbtn, acceptbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rev);

        revisionbtn = (Button) findViewById(R.id.revision);
        acceptbtn = (Button) findViewById(R.id.accept);



        revisionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rev.this, Revision.class);
                startActivity(intent);
            }
        });

        acceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(rev.this, Accept.class);
                startActivity(intent1);
            }
        });

    }

}
