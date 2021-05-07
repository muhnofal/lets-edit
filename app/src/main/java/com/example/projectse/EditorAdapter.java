package com.example.projectse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EditorAdapter extends RecyclerView.Adapter<EditorAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Data_Editor> data_editors;

    public EditorAdapter(Context cont, ArrayList<Data_Editor> data){
        context = cont;
        data_editors = data;
    }

    @NonNull
    @Override
    public EditorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_editor ,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditorAdapter.MyViewHolder holder, final int position) {
//        holder.tv_nama.setText(data_editors.get(position).getName());
        Picasso.get().load(data_editors.get(position).getImage()).into(holder.iv_foto);


    }

    @Override
    public int getItemCount() {
        return data_editors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

//        TextView tv_nama, tv_quote, tv_rating;
//        ImageView iv_foto, iv_bintang, iv_love, iv_job, iv_work, iv_software, iv_check;
        ImageView iv_foto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            tv_nama = (TextView) itemView.findViewById(R.id.EditorName);
            iv_foto = (ImageView) itemView.findViewById(R.id.foto);

        }

    }

}