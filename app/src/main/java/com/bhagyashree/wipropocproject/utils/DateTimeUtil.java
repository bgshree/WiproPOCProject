package com.bhagyashree.wipropocproject.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeUtil {

    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private DateTimeUtil() {

    }

    public static String getTimeInHeaderFormat(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(JSON_DATE_FORMAT);
        String formattedDate = simpleDateFormat.format(calendar.getTime());
        return formattedDate;
    }
}
