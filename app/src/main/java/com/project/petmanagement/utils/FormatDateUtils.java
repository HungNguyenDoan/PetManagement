package com.project.petmanagement.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class FormatDateUtils {
    public static String DateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(date);
    }
    public static Date StringToDate1(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date1 = sdf.parse(date);
        return date1;
    }
    public static Date StringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date1 = sdf.parse(date);
        return date1;
    }
    public static String DateToString1(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date1 = sdf.format(date);
        return date1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String calculate(Date dateOfBirth){
        LocalDate dob = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate nowDate = LocalDate.now();
        Period period = Period.between(dob, nowDate);
        String age = "";
        if(period.getYears()!=0){
            age+= period.getYears()+" Năm tuổi";
        }else if(period.getMonths()!=0){
            age+=period.getMonths()+" Tháng tuổi";
        } else {
            age+=period.getDays() +" Ngày tuổi";
        }
        return age;
    }
}
