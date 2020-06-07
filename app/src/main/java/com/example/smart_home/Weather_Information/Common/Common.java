package com.example.smart_home.Weather_Information.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static String API_KEY = "6a53e4f2f4066dcd497cedade9fac4d8";
    public static String API_LINK ="https://api.openweathermap.org/data/2.5/weather";
    public static String apiRequest(String lat,String lng) {
        StringBuilder sb = new StringBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric", lat, lng, API_KEY));
        return sb.toString();
    }
    public static String unixTimeStampToDateTine(double unixTimeStamp){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long)unixTimeStamp*1000);
        return dateFormat.format(date);
    }
    public static String gerImage(String icon){
        return String.format("https://openweathermap.org/img/w/%s.png",icon);
    }
    public static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }


}
