package com.example.projectse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Data_Editor> data_editors;
    private StorageReference reference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference ref = firebaseDatabase.getReference();



    public RecyclerViewAdapter (Context cont, ArrayList<Data_Editor> data){
        context = cont;
        data_editors = data;
        reference = FirebaseStorage.getInstance().getReference();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_hire ,parent, false);
        return new MyViewHolder(view);
    }

    private void LoadData(){

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.MyViewHolder holder, final int position) {
        holder.tv_nama.setText(data_editors.get(position).getName());
        holder.tv_rating.setText(data_editors.get(position).getRating());
        holder.tv_quote.setText(data_editors.get(position).getQuote());
        Picasso.get().load(data_editors.get(position).getImage()).into(holder.iv_foto);
        Picasso.get().load(data_editors.get(position).getBintang()).into(holder.iv_bintang);
//        Picasso.get().load(data_editors.get(position).getLove()).into(holder.iv_love);
        Picasso.get().load(data_editors.get(position).getJob()).into(holder.iv_job);
        Picasso.get().load(data_editors.get(position).getWork()).into(holder.iv_work);
        Picasso.get().load(data_editors.get(position).getSoftware()).into(holder.iv_software);
        Picasso.get().load(data_editors.get(position).getCheck()).into(holder.iv_check);
        Picasso.get().load(data_editors.get(position).getJob2()).into(holder.iv_job2);


        //hire menu clickable
        holder.iv_check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Drawable mFoto, mJob;
                mFoto = holder.iv_foto.getDrawable();
                mJob = holder.iv_job2.getDrawable();

                Bitmap mFotoBitmap, mJobBitmap;
                mFotoBitmap = ((BitmapDrawable)mFoto).getBitmap();
                mJobBitmap = ((BitmapDrawable)mJob).getBitmap();
                //ini ke editor.class

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                mFotoBitmap.compress(Bitmap.CompressFormat.PNG, 100,stream);
                mJobBitmap.compress(Bitmap.CompressFormat.PNG, 100,stream2);

                byte[] bytes = stream.toByteArray();
                byte[] bytes2 = stream2.toByteArray();

                Intent intent = new Intent(context.getApplicationContext(), editor.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("FOTO",bytes);
                intent.putExtra("JOB2",bytes2);
                intent.putExtra("NAMA", data_editors.get(position).getName());
                intent.putExtra("RATING", data_editors.get(position).getRating());
                intent.putExtra("REGNDATE", data_editors.get(position).getRegndate());


//                intent.putExtra("FOTO", data_editors.get(position).getImage());
                context.startActivity(intent);

            }

        });

    }

    @Override
    public int getItemCount() {
        return data_editors.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama, tv_quote, tv_rating;
        public ImageView iv_foto, iv_bintang, iv_love, iv_job, iv_work, iv_software, iv_check;
        public ImageView iv_job2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nama = (TextView) itemView.findViewById(R.id.EditorName);
            tv_quote = (TextView) itemView.findViewById(R.id.Quote);
            tv_rating = (TextView) itemView.findViewById(R.id.EditorRating);
            iv_foto = (ImageView) itemView.findViewById(R.id.EditorImage);
            iv_bintang = (ImageView) itemView.findViewById(R.id.Bintang);
//            iv_love = (ImageView) itemView.findViewById(R.id.EditorLove);
            iv_job = (ImageView) itemView.findViewById(R.id.EditorJob);
            iv_work = (ImageView) itemView.findViewById(R.id.EditorWork);
            iv_software = (ImageView) itemView.findViewById(R.id.EditorSoftware);
            iv_check = (ImageView) itemView.findViewById(R.id.Check);
            iv_job = (ImageView) itemView.findViewById(R.id.EditorJob);
            iv_job2 = (ImageView) itemView.findViewById(R.id.job2);


        }

    }

}


//    private Context mContext;
//
//    private List<Data_Editor> mData;
//    private List<Data_Editor2> zData;
//
//    public RecyclerViewAdapter(Context mContext, List<Data_Editor> mData, List<Data_Editor2> zData) {
//        this.mContext = mContext;
//        this.mData = mData;
//        this.zData = zData;
//    }
//
//    @NonNull
//
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        view = mInflater.inflate(R.layout.cardview_hire,parent,false);
//
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
//
//        //Activity Menu
//        holder.iv_image.setImageResource(mData.get(position).getImage());
//        holder.tv_name.setText(mData.get(position).getName());
//        holder.iv_bintang.setImageResource(mData.get(position).getBintang());
//        holder.tv_rating.setText(mData.get(position).getRating());
//        holder.iv_job.setImageResource(mData.get(position).getJob());
//        holder.iv_work.setImageResource(mData.get(position).getWork());
//        holder.iv_software.setImageResource(mData.get(position).getSoftware());
//        holder.tv_quote.setText(mData.get(position).getQuote());
//        holder.iv_check.setImageResource(mData.get(position).getCheck());
//        holder.iv_love.setImageResource(mData.get(position).getLove());
//
//        //hire menu clickable
//        holder.iv_check.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                //ini ke editor.class
//                Intent intent = new Intent(mContext,editor.class);
//                intent.putExtra("Foto", mData.get(position).getImage());
//                intent.putExtra("Nama", mData.get(position).getName());
//                intent.putExtra("Rating", mData.get(position).getRating());
//                intent.putExtra("Kerjaan2", zData.get(position).getJob2());
//                intent.putExtra("TempatDanTahun", zData.get(position).getTempat());
//
//                mContext.startActivity(intent);
//
//            }
//
//        });
//
//        //hire menu clickable
//        holder.iv_love.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//
//
//    }
//
//    // ini gk tau gunanya apa
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    //ini input data
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//        //Hire Menu
//        ImageView iv_image;
//        TextView tv_name;
//        ImageView iv_bintang;
//        TextView tv_rating;
//        ImageView iv_job;
//        ImageView iv_work;
//        ImageView iv_software;
//        TextView tv_quote;
//        ImageView iv_check;
//        ImageView iv_love;
//        CardView cardView;
//        ImageView iv_job2;
//
//        //pemanggilan data menggunakan ID
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            //Hire Menu
//            iv_image = (ImageView) itemView.findViewById(R.id.EditorImage);
//            tv_name = (TextView) itemView.findViewById(R.id.EditorName);
//            iv_bintang = (ImageView) itemView.findViewById(R.id.Bintang);
//            tv_rating = (TextView) itemView.findViewById(R.id.EditorRating);
//            iv_job = (ImageView) itemView.findViewById(R.id.EditorJob);
//            iv_work = (ImageView) itemView.findViewById(R.id.EditorWork);
//            iv_software = (ImageView) itemView.findViewById(R.id.EditorSoftware);
//            tv_quote = (TextView) itemView.findViewById(R.id.Quote);
//            iv_check = (ImageView) itemView.findViewById(R.id.Check);
//            iv_love = (ImageView) itemView.findViewById(R.id.EditorLove);
//            cardView = (CardView) itemView.findViewById(R.id.CardView);
//
//        }
//
//    }
