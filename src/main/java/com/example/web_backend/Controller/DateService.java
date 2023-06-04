package com.example.web_backend.Controller;
import java.text.SimpleDateFormat;
public class DateService {
    public static String getTodayDate(){
        java.util.Date date=new java.util.Date();
        SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
