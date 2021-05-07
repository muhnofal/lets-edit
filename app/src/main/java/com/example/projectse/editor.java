package com.example.projectse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class editor extends AppCompatActivity {


    private ViewPager2 viewPager2;
    private Button buttonsend;
    private DatabaseReference reference;
    public Uri mImageUrl;
    private StorageReference msStorageRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser, user;
    private FirebaseDatabase firebaseDatabase;

    EditText addcom;
    TextView tvNama, tvRating, tvregndate, reply;
    ImageView ivImage, ivjob, ivcontact, likebtn;
    Button btn, btn2, addbtn;
    Data_Editor dataEditor;
    Data_Pesan data_pesan;
    long maxid = 0;
    String uid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        dataEditor = new Data_Editor();

        buttonsend = findViewById(R.id.send);
        tvNama = findViewById(R.id.nama);
        tvRating = findViewById(R.id.rating);
        tvregndate = findViewById(R.id.tempattahun);
        ivImage = findViewById(R.id.foto);
        ivjob = findViewById(R.id.kerjaan);
        ivcontact = findViewById(R.id.contactme);
        btn = findViewById(R.id.hirenormal);
        btn2 = findViewById(R.id.hireexpress);
        reply = findViewById(R.id.replyyy);
        addcom = findViewById(R.id.teksfield);
        likebtn = findViewById(R.id.mantap);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();


//        String nama = getIntent().getStringExtra("NAMA");
//        String rating = getIntent().getStringExtra("RATING");
//        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        //Ambil Data
        final Intent intent = getIntent();
        byte[] bytes = intent.getByteArrayExtra("FOTO");
        byte[] bytes2 = intent.getByteArrayExtra("JOB2");

        String nama = intent.getStringExtra("NAMA");
        String rating =  intent.getStringExtra("RATING");
        String regndate = intent.getStringExtra("REGNDATE");


        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Bitmap bmp2 = BitmapFactory.decodeByteArray(bytes2, 0, bytes2.length);

        tvNama.setText(nama);
        tvRating.setText(rating);
        tvregndate.setText(regndate);
        ivImage.setImageBitmap(bmp);
        ivjob.setImageBitmap(bmp2);


        //Pager
        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<SliderItem> sliderItems = new ArrayList<>();

        sliderItems.add(new SliderItem(R.drawable.video));
        sliderItems.add(new SliderItem(R.drawable.video));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        //akhir dari pager

        msStorageRef = FirebaseStorage.getInstance().getReference("pesan");
        reference = FirebaseDatabase.getInstance().getReference().child("user").child(uid).child("inbox");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //ini databsenya
                if(dataSnapshot.exists()){
                    maxid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataEditor.setName(tvNama.getText().toString().trim());
                reference.child(String.valueOf(maxid+1)).setValue(dataEditor);
                Toast.makeText(editor.this, "Hire editor success", Toast.LENGTH_SHORT).show();
//                uploadFile();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataEditor.setName(tvNama.getText().toString().trim());
                reference.child(String.valueOf(maxid+1)).setValue(dataEditor);
                Toast.makeText(editor.this, "Hire editor success", Toast.LENGTH_SHORT).show();

            }
        });

        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcom.setVisibility(View.VISIBLE);
                buttonsend.setVisibility(View.VISIBLE);
            }
        });

        //Send Reply

        buttonsend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addcom.setText(null);
                addcom.setVisibility(View.INVISIBLE);
                buttonsend.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Comment sent", Toast.LENGTH_SHORT).show();
            }

        });

        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likebtn.setImageResource(R.drawable.mantaptekan);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
