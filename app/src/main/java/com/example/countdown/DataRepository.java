package com.example.countdown;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataRepository implements FirBaseHandler.DataCallBack {


    public static ArrayList<Theme> themes = new ArrayList<>();
    public ArrayList<EventEntry> Events = new ArrayList<>();
    private DataLoadListener listener;
    public DataReloadListener listener2;
FirBaseHandler handler;
    public static DataRepository INSTANCE;


    public static DataRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DataRepository();

        return INSTANCE;
    }


    public void setListener(DataLoadListener listener,DataReloadListener listener2) {
        this.listener = listener;
        this.listener2= listener2;
    }



    private DataRepository() {
        handler = new FirBaseHandler(this);
    }


    public void addData(String name, String date, int theme) {
        Theme t = themes.get(theme);
        EventEntry e = new EventEntry(name, date, t);
        handler.addEvent(e);


    }

    public ArrayList<EventEntry> getList() {
        return Events;
    }

    public ArrayList<Theme> getThemeList() {
        return themes;
    }


    @Override
    public void onDataRecivied(ArrayList<Theme> list) {
        themes = list;

        if(listener!=null){


            listener.onThemeLoaded();
        }
    }

    @Override
    public void onEventsReceived(ArrayList<EventEntry> list) {
        ArrayList<EventEntry> filteredList = (ArrayList<EventEntry>) list.stream().filter(eventEntry ->
                System.currentTimeMillis() < DateUtils.getDateFromString( eventEntry.date)
                        .getTime())
                .collect(Collectors.toList());
        Collections.sort(filteredList, (o1, o2) -> {
            Long time = DateUtils.getDateFromString(o1.getDate()).getTime();
            Long time2 = DateUtils.getDateFromString(o2.getDate()).getTime();
            long res =  time-time2;
            return (int) res;
        });
        this.Events =  filteredList;
        listener2.onDataReceived(this.Events);
    }

    interface DataLoadListener{
        void onThemeLoaded();
  }
  interface DataReloadListener{
        void onDataReceived(ArrayList<EventEntry> list);
  }
}
