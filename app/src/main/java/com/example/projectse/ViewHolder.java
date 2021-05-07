package com.example.projectse;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View view;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemlongClick(view, getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context context, String nama, String rating, String quote, String bintang, String job, String love, String work, String check, String software, String foto){
        TextView tv_nama = (TextView) itemView.findViewById(R.id.EditorName);
        TextView tv_quote = (TextView) itemView.findViewById(R.id.Quote);
        TextView tv_rating = (TextView) itemView.findViewById(R.id.EditorRating);
        ImageView iv_foto = (ImageView) itemView.findViewById(R.id.EditorImage);
        ImageView iv_bintang = (ImageView) itemView.findViewById(R.id.Bintang);
        ImageView iv_love = (ImageView) itemView.findViewById(R.id.EditorLove);
        ImageView iv_job = (ImageView) itemView.findViewById(R.id.EditorJob);
        ImageView iv_work = (ImageView) itemView.findViewById(R.id.EditorWork);
        ImageView iv_software = (ImageView) itemView.findViewById(R.id.EditorSoftware);
        ImageView iv_check = (ImageView) itemView.findViewById(R.id.Check);

        tv_nama.setText(nama);
        tv_rating.setText(rating);
        tv_quote.setText(quote);
        Picasso.get().load(bintang).into(iv_bintang);
        Picasso.get().load(job).into(iv_job);
        Picasso.get().load(love).into(iv_love);
        Picasso.get().load(work).into(iv_work);
        Picasso.get().load(check).into(iv_check);
        Picasso.get().load(software).into(iv_software);
        Picasso.get().load(foto).into(iv_foto);

    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemlongClick(View view, int position);

    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
