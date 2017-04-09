package com.wuchaooooo.kpi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wuchaooooo on 05/12/2016.
 */
public class TimeUtil {
    public static String dateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = dateFormat.format(date);
        return dateTime;
    }

    public static String dateFormat1(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String dateTime = dateFormat.format(date);
        return dateTime;
    }
}
