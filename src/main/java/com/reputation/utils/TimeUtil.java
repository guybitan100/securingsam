package com.reputation.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
    public static final long MIN = 60 * 1000;
    public static final long HOUR = 60 * MIN;
    public static final long DAY = HOUR * 24;

    public static final long PAST_30_MIN = 30 * MIN;
    public static final long PAST_3_HOURS = 3 * HOUR;
    public static final long PAST_DAY = 1 * DAY;
    public static final long PAST_WEEK = 7 * DAY;
    public static final long PAST_MONTH = 30 * DAY;
    public static final long HALF_YEAR = 6 * PAST_MONTH;

    public static String getCurrentTime() {
        long now = System.currentTimeMillis();
        String currentTimeBase = now + "";
        return currentTimeBase.substring(0, currentTimeBase.length() - 3);
    }

    public static String getPast30Min() {
        long past = System.currentTimeMillis() - PAST_30_MIN;
        String pastStr = past + "";
        pastStr = pastStr.substring(0, pastStr.length() - 3);
        return pastStr;
    }

    public static String getPast3Hours() {
        long past = System.currentTimeMillis() - PAST_3_HOURS;
        String pastStr = past + "";
        pastStr = pastStr.substring(0, pastStr.length() - 3);
        return pastStr;
    }

    public static String getPastDay() {
        long past = System.currentTimeMillis() - PAST_DAY;
        String pastStr = past + "";
        pastStr = pastStr.substring(0, pastStr.length() - 3);
        return pastStr;
    }

    public static String getPastWeek() {
        long past = System.currentTimeMillis() - PAST_WEEK;
        String pastStr = past + "";
        pastStr = pastStr.substring(0, pastStr.length() - 3);
        return pastStr;
    }

    public static String getPastMonth() {
        long past = System.currentTimeMillis() - PAST_MONTH;
        String pastStr = past + "";
        pastStr = pastStr.substring(0, pastStr.length() - 3);
        return pastStr;
    }

    public static String getPastHalfYear() {
        long past = System.currentTimeMillis() - HALF_YEAR;
        String pastStr = past + "";
        pastStr = pastStr.substring(0, pastStr.length() - 3);
        return pastStr;
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM");
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static String getDateFromEpocTime(long epocTime) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(epocTime);
        return sdfDate.format(date);
    }

    public static String getCurrentEpocTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss.SSS");
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static String getDateFromTimeStamp(long timeStamp) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss.SSS");
        Date date = new Date(timeStamp);
        return sdfDate.format(date);
    }

    public static int getHour() {
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
