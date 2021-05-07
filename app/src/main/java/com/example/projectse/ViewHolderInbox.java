package com.example.projectse;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderInbox extends RecyclerView.ViewHolder {

    View view;


    public ViewHolderInbox(@NonNull View itemView) {
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

    public void setDetails(Context context, String nama){

        TextView tv_nama = (TextView) itemView.findViewById(R.id.EditorName);

        tv_nama.setText(nama);
    }

    private ViewHolderInbox.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemlongClick(View view, int position);

    }

    public void setOnClickListener(ViewHolderInbox.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
