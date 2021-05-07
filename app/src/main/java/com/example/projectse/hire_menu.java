package com.example.projectse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectse.Data_Editor;
import com.example.projectse.editor;
import com.example.projectse.R;
import com.example.projectse.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.ByteArrayOutputStream;

public class hire_menu extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_menu);

        mrecyclerView = findViewById(R.id.RecycleView_id);
        mrecyclerView.setHasFixedSize(true);

        mrecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mfirebaseDatabase.getReference("dataeditor");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Data_Editor, ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Data_Editor, ViewHolder>(
                Data_Editor.class,
                R.layout.cardview_hire,
                ViewHolder.class,
                mref
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Data_Editor data_editor, int i) {
                viewHolder.setDetails(getApplicationContext(), data_editor.getName(), data_editor.getRating(), data_editor.getQuote(), data_editor.getBintang(), data_editor.getJob(), data_editor.getLove(), data_editor.getWork(), data_editor.getCheck(), data_editor.getSoftware(), data_editor.getImage());

            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                ImageView asd = findViewById(R.id.Check);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        TextView tv_nama = (TextView) view.findViewById(R.id.EditorName);
                        TextView tv_rating = (TextView) view.findViewById(R.id.EditorRating);
                        ImageView iv_image = (ImageView) view.findViewById(R.id.EditorImage);

                        String mnama = tv_nama.getText().toString();
                        String mrating = tv_rating.getText().toString();
                        Drawable mDrawable = iv_image.getDrawable();
                        Bitmap mBitmap = ((BitmapDrawable)mDrawable).getBitmap();

                        Intent intent = new Intent(view.getContext(), editor.class);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        mBitmap.compress(Bitmap.CompressFormat.PNG, 100,stream);
                        byte[] bytes = stream.toByteArray();

                        intent.putExtra("FOTO",bytes);
                        intent.putExtra("NAMA",mnama);
                        intent.putExtra("RATING",mrating);
                        startActivity(intent);

                    }

                    @Override
                    public void onItemlongClick(View view, int position) {

                    }
                });

                return viewHolder;
            }
        };

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }



}

//tanda

//    List<Data_Editor> ListEditor;
//    List<Data_Editor2> ListEditor2;
//
//    DatabaseReference dbreference;
//    RecyclerViewAdapter myadapter;

//private DatabaseReference reference;
//    ArrayList<Data_Editor> list;
//    RecyclerViewAdapter adapter;
//
//    private RecyclerView mRecylcer;
//    private LinearLayoutManager mManager;
//
//

//
//        mRecylcer = findViewById(R.id.RecycleView_id);
//        mRecylcer.setHasFixedSize(true);
//        mManager = new GridLayoutManager(this,2);
//        mRecylcer.setLayoutManager(mManager);
//
//        reference = FirebaseDatabase.getInstance().getReference().child("dataeditor");
//
//        reference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                list = new ArrayList<>();
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    Data_Editor editor = dataSnapshot1.getValue(Data_Editor.class);
//                    list.add(editor);
//                }
//                adapter = new RecyclerViewAdapter(getApplicationContext(), list);
//                mRecylcer.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), "Terjadi kesalahan", Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//
//
//
//    }


//        ListEditor = new ArrayList<>();
//        ListEditor2 = new ArrayList<>();


//        //Data yang terlihat Profile
//        ListEditor.add(new Data_Editor(R.drawable.fotobambang, "Bambang", R.drawable.bintang, "4.5", R.drawable.editor, R.drawable.common, R.drawable.premierpro, "Saya mahir dalam mengedit video, saya bisa memberikan kualitas editan yang baik", R.drawable.check, R.drawable.lovekecil));
//        ListEditor.add(new Data_Editor(R.drawable.fotoaisyah, "Aisyah", R.drawable.bintang, "4.8", R.drawable.motiondesainer, R.drawable.motiongrap, R.drawable.aftereffect, "Saya sudah 5 tahun bergelut dibidang motion graphic", R.drawable.check, R.drawable.lovekecil));
//
//        ListEditor.add(new Data_Editor(R.drawable.fotoaisyah, "Aisyah", R.drawable.bintang, "4.8", R.drawable.motiondesainer, R.drawable.motiongrap, R.drawable.aftereffect, "Saya sudah 5 tahun bergelut dibidang motion graphic", R.drawable.check, R.drawable.lovekecil));
//        ListEditor.add(new Data_Editor(R.drawable.fotobambang, "Bambang", R.drawable.bintang, "4.5", R.drawable.editor, R.drawable.common, R.drawable.premierpro, "Saya mahir dalam mengedit video, saya bisa memberikan kualitas editan yang baik", R.drawable.check, R.drawable.lovekecil));
//
//        ListEditor.add(new Data_Editor(R.drawable.fotobambang, "Bambang", R.drawable.bintang, "4.5", R.drawable.editor, R.drawable.common, R.drawable.premierpro, "Saya mahir dalam mengedit video, saya bisa memberikan kualitas editan yang baik", R.drawable.check, R.drawable.lovekecil));
//        ListEditor.add(new Data_Editor(R.drawable.fotoaisyah, "Aisyah", R.drawable.bintang, "4.8", R.drawable.motiondesainer, R.drawable.motiongrap, R.drawable.aftereffect, "Saya sudah 5 tahun bergelut dibidang motion graphic", R.drawable.check, R.drawable.lovekecil));
//
//        ListEditor.add(new Data_Editor(R.drawable.fotobambang, "Bambang", R.drawable.bintang, "4.5", R.drawable.editor, R.drawable.common, R.drawable.premierpro, "Saya mahir dalam mengedit video, saya bisa memberikan kualitas editan yang baik", R.drawable.check, R.drawable.lovekecil));
//        ListEditor.add(new Data_Editor(R.drawable.fotoaisyah, "Aisyah", R.drawable.bintang, "4.8", R.drawable.motiondesainer, R.drawable.motiongrap, R.drawable.aftereffect, "Saya sudah 5 tahun bergelut dibidang motion graphic", R.drawable.check, R.drawable.lovekecil));
//
//
//        //Data yang tersembunyi Profile
//        ListEditor2.add(new Data_Editor2(R.drawable.motionagraphungu2, "INDONESIA | JANUARY 2020"));
//        ListEditor2.add(new Data_Editor2(R.drawable.motionagraphungu, "INDONESIA | FEBRUARY 2019"));
//
//        ListEditor2.add(new Data_Editor2(R.drawable.motionagraphungu, "INDONESIA | FEBRUARY 2019"));
//        ListEditor2.add(new Data_Editor2(R.drawable.motionagraphungu2, "INDONESIA | JANUARY 2020"));
//
//        ListEditor2.add(new Data_Editor2(R.drawable.motionagraphungu2, "INDONESIA | JANUARY 2020"));
//        ListEditor2.add(new Data_Editor2(R.drawable.motionagraphungu, "INDONESIA | FEBRUARY 2019"));
//
//        ListEditor2.add(new Data_Editor2(R.drawable.motionagraphungu2, "INDONESIA | JANUARY 2020"));
//        ListEditor2.add(new Data_Editor2(R.drawable.motionagraphungu, "INDONESIA | FEBRUARY 2019"));
//
//
//
//
//        //Data untuk inbox
////        ListEditor3.add(new Data_Sender(R.drawable.fotobambang, "Bambang", "15 PM (7jam yang lalu)", "Projec kamu telah selesai, slahkan dicek"));
////        ListEditor3.add(new Data_Sender(R.drawable.fotobambang, "Aisyah", "13 PM (5jam yang lalu)", "Hi, Malih projectmu telah selesai, silahkan di cek"));
//
//        RecyclerView myrv = (RecyclerView) findViewById(R.id.RecycleView_id);
//        myadapter = new RecyclerViewAdapter(this,ListEditor,ListEditor2);
//        myrv.setLayoutManager(new GridLayoutManager(this,2));
//        myrv.setAdapter(myadapter);

