package com.project.petmanagement.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateUtils {
    public static String DateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
