package com.example.countdown;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class RadioAdapter extends RecyclerView.Adapter<Radioviewholder> {
    ArrayList<Theme>themes;
    int selectedTheme =  -1;
    public RadioAdapter(ArrayList<Theme> themes){this.themes=themes;}
    @NonNull
    @Override
    public Radioviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview2,parent,false);

        return new Radioviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Radioviewholder holder, int position) {
        Theme t= themes.get(position);

        holder.tvName.setText(t.name);

        if(selectedTheme!=-1 && themes.get(selectedTheme).name.equals(t.getName())){
            holder.imageView.setVisibility(View.VISIBLE);
        }else{
            holder.imageView.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(v -> {
            selectedTheme = holder.getAdapterPosition();
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return themes.size();
    }
}
