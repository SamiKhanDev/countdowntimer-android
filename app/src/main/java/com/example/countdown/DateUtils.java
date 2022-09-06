package com.example.countdown;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    //return date in format 19/10/1992 09:33 AM
    public static String getDate(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
        String time = df.format(calendar.getTime());
        return time;
    }

    public static Date getDateFromString(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
        try {
            Date date1 = df.parse(date);
            return date1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
