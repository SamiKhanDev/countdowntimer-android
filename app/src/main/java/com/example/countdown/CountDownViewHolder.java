package com.example.countdown;

import android.os.CountDownTimer;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CountDownViewHolder extends RecyclerView.ViewHolder {
    TextView textname;
    TextView textdate;
    TextView textCounter;
    LottieAnimationView imageView;
    CountDownTimer timer;
    public CountDownViewHolder(@NonNull View itemView) {
        super(itemView);
        textname=itemView.findViewById(R.id.textevent);
        textdate=itemView.findViewById(R.id.textdate);
        textCounter=itemView.findViewById(R.id.textCounter);
        imageView=itemView.findViewById(R.id.imageEvent);
    }
    public void setTimer(String time){
        timer = new CountDownTimer(DateUtils.getDateFromString(time).getTime(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long days, hours, minutes, sec;

                Date currentDate = new Date();
                long diff = millisUntilFinished - currentDate.getTime();
                days = diff / (1000 * 60 * 60 * 24);
                hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24;
                minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60;
                sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;

                SpannableStringBuilder builder = new SpannableStringBuilder();
                RelativeSizeSpan span = new RelativeSizeSpan(3f);
                builder.append(String.valueOf(days), span, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(" Days ");
                builder.append(String.valueOf(hours), new RelativeSizeSpan(1.5f), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(" Hours ");
                builder.append(minutes + " ",  new RelativeSizeSpan(1.5f), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(" Minutes ");
                builder.append(sec + " ",  new RelativeSizeSpan(1.5f), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                builder.append(" Seconds ");


                textCounter.setText(
                        builder
                );
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();


    }
}
