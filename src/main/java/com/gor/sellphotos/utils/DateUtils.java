package com.gor.sellphotos.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.StringUtils;

public class DateUtils {

    /*
     * See https://github.com/arunpjohny/spring-web-app/blob/master/src/java/com/arunpjohny/core/DateUtils.java
     */

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static final SimpleDateFormat sdf_date_format = new SimpleDateFormat(DATE_FORMAT);

    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static final SimpleDateFormat sdf_datetime_format = new SimpleDateFormat(DATETIME_FORMAT);

    public static Date parseDate(String date) {
        return parseDate(date, sdf_date_format);
    }

    public static Date parseDateTime(String date) {
        return parseDate(date, sdf_datetime_format);
    }

    public static Date parseDate(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return parseDate(date, df);
    }

    public static Date parseDate(String date, SimpleDateFormat df) {
        Date result = null;

        try {
            ParsePosition pp = new ParsePosition(0);
            result = StringUtils.isEmpty(date) ? null : df.parse(date, pp);
            if (date.length() != pp.getIndex()) {
                result = null;
            }
        }
        catch (Exception e) {
            // do nothing
        }
        return result;
    }

    public static String formatDate(Date d) {
        return formatDate(d, sdf_date_format);
    }

    public static String formatDateTime(Date d) {
        return formatDate(d, sdf_datetime_format);
    }

    public static String formatDate(Date d, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format);
        return formatDate(d, f);
    }

    public static String formatDate(Date d, SimpleDateFormat df) {
        return d == null ? "" : df.format(d);
    }

    public static Date getCurrentDate() {
        return (new GregorianCalendar()).getTime();
    }

}
