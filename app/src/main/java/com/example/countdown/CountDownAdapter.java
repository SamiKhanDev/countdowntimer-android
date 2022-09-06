package com.example.countdown;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CountDownAdapter extends RecyclerView.Adapter<CountDownViewHolder> {
    ArrayList<EventEntry> list;


    public CountDownAdapter(ArrayList<EventEntry> list) {
        this.list = list;
    }

    public void setList(ArrayList<EventEntry> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountDownViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);

        return new CountDownViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CountDownViewHolder holder, int position) {
        EventEntry d = list.get(position);
        holder.textname.setText(d.getName());
        holder.textdate.setText(d.getDate());
        holder.itemView.setBackgroundColor(Color.parseColor(d.theme.color));
        holder.imageView.setAnimationFromUrl(d.theme.image);
        holder.setTimer(d.getDate());
    }

    @Override
    public void onViewRecycled(@NonNull CountDownViewHolder holder) {
        super.onViewRecycled(holder);
        holder.timer.cancel();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
