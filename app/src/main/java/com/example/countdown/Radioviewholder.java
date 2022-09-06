package com.example.countdown;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Radioviewholder extends RecyclerView.ViewHolder {
    TextView tvName;
     ImageView imageView;


    public Radioviewholder(@NonNull View itemView) {
        super(itemView);
        tvName= itemView.findViewById(R.id.tvName);
        imageView= itemView.findViewById(R.id.imageView);

    }
}
