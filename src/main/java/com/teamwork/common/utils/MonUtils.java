/**
 * Author : czy
 * Date : 2019年4月24日 下午8:10:13
 * Title : com.riozenc.cfs.common.MonUtils.java
 **/
package com.teamwork.common.utils;

import com.riozenc.titanTool.common.date.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class MonUtils {
    public static String getMon() {
        return DateUtil.getDate("yyyyMM");
    }

    public static String getMon(String date) {
        if (date == null) {
            return DateUtil.getDate("yyyyMM");
        }
        return date;
    }

    public static String getLastMon(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -1);
        return DateUtil.getDate(calendar.getTime(), "yyyyMM");
    }

    public static String getNextMon(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return DateUtil.getDate(calendar.getTime(), "yyyyMM");
    }

    public static Integer getDiffTime(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        long time1 = calendar.getTimeInMillis();
        calendar.setTime(endDate);
        long time2 = calendar.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String getNextDay(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(5,7))-1);
        calendar.set(Calendar.DATE, Integer.parseInt(date.substring(8))+1);
        return DateUtil.getDate(calendar.getTime(), "yyyy-MM-dd");
    }
}
