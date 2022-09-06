package com.example.countdown;

public class EventEntry {
    String name;
    String date;
    Theme theme;


    public EventEntry(){}
    public EventEntry(String name, String date, Theme theme) {
        this.name = name;
        this.date = date;
        this.theme= theme;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
    @Override
    public String toString() {
        return "CategoryModel{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }
}
