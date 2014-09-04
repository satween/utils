package com.satween.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Encapsulate routine stuff for more often tasks with date to(from) strings converting.<br />
 * <p/>
 * https://github.com/satween/utils
 */

public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final int DAY_IN_MILLISECONDS = 1000 * 60 * 60 * 24;
    private String dateFormat;
    SimpleDateFormat formatter;


    /**
     * Create DateUtil object with default date format = "yyyy-MM-dd" <br />
     * e.g 2014-01-01 for the first January of 2014 year
     */
    public DateUtil() {
        this(DEFAULT_DATE_FORMAT);
    }

    /**
     * Create object with given format
     *
     * @param dateFormat - format string that used foe SimpleDateFormat class constructor
     */
    public DateUtil(String dateFormat) {
        this.dateFormat = dateFormat;
        this.formatter = new SimpleDateFormat(dateFormat);
    }

    public Date getTodayDate() {
        return new Date();
    }

    public Date getTomorrowDate() {
        return new Date(getTodayDate().getTime() + (DAY_IN_MILLISECONDS));
    }

    /**
     * @param date
     * @return String with date in same format that was used to create this object
     */
    public String getDateAsString(Date date) {
        checkNotNull(date);
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * Parse string and create Date object
     *
     * @param dateString in same format string that was used to create this object
     * @return Date object
     * @throws java.text.ParseException if string is invalid
     */
    public Date getDateFromString(String dateString) throws ParseException {
        return formatter.parse(dateString);
    }

    /**
     * @param timestamp
     * @return
     * @throws java.text.ParseException
     */
    public Date getDateFromTimestamp(String timestamp) throws ParseException {
        return new Date(Long.valueOf(timestamp) * 1000);
    }

    /**
     * @param date
     * @return
     */
    public String getTimestampStringFromDate(Date date) {
        return String.valueOf(date.getTime() / 1000);
    }
}
