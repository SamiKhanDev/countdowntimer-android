package com.example.countdown;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListUtil;

import com.bumptech.glide.load.data.DataFetcher;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class FirBaseHandler {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("/themes");
    DatabaseReference myRef2 = database.getReference("/Events");

    public void getThemes(DataCallBack listener) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Theme> list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Theme theme = snapshot.getValue(Theme.class);
                    list.add(theme);

                }
                listener.onDataRecivied(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // HashMap<String, Theme> themes = new HashMap<>();

        // private void addThemes() {
        //   themes.put("Wedding", new Theme(R.color.magenta, R.raw.anim_wedding));
        //  themes.put("Covid", new Theme(R.color.purple_700, R.raw.anim_covid));
        //  themes.put("Walk", new Theme(R.color.green, R.raw.anim_walk));
        //  themes.put("Sleep", new Theme(R.color.red, R.raw.anim_sleep));


    }

    public void getEvent(DataCallBack listner) {
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<EventEntry> list = new ArrayList<>();
                for (DataSnapshot snapshot2 : snapshot.getChildren()) {
                    EventEntry eventEntry = snapshot2.getValue(EventEntry.class);
                    list.add(eventEntry);
                }
                listner.onEventsReceived(list);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public FirBaseHandler(DataCallBack listener) {
        getThemes(listener);
        getEvent(listener);
    }

    public interface DataCallBack {
        void onDataRecivied(ArrayList<Theme> list);

        void onEventsReceived(ArrayList<EventEntry> list);
    }

    public void addEvent(EventEntry e) {
        myRef2.child(UUID.randomUUID().toString()).setValue(e);
    }
}
