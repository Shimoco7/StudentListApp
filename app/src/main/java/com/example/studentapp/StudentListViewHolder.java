package com.example.studentapp;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class StudentListViewHolder  extends RecyclerView.ViewHolder {
    TextView nameTv;
    TextView idTv;
    CheckBox cb;

    public StudentListViewHolder(@NonNull View itemView, OnItemClickListener itemClickListener, OnCheckBoxClickListener checkBoxClickListener) {
        super(itemView);
        nameTv = itemView.findViewById(R.id.listrow_name_tv);
        idTv = itemView.findViewById(R.id.listrow_id_tv);
        cb = itemView.findViewById(R.id.listrow_cb);
        itemView.setOnClickListener(v->{
            int pos = getAdapterPosition();
            itemClickListener.onItemClick(pos);
        });
        cb.setOnClickListener(v->{
            int pos = getAdapterPosition();
            checkBoxClickListener.onClick(pos);
        });
    }
}
