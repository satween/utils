package com.satween.utils;

import junit.framework.TestCase;

import java.text.ParseException;
import java.util.Date;

/**
 * Clearview project<br />
 * http://www.clear--view.com<br />
 * Created by satween <br />
 */

public class DateUtilTest extends TestCase {

    public void testGetTodayDate() {
        DateUtil dateUtil = new DateUtil();
        Date today = new Date();

        assertEquals("Objects are not equals", today, dateUtil.getTodayDate());
    }

    public void testGetTomorrowDate() {
        DateUtil dateUtil = new DateUtil();
        Date today = new Date();
        Date tomorrow = dateUtil.getTomorrowDate();


        assertTrue(tomorrow.getTime() - today.getTime() == DateUtil.DAY_IN_MILLISECONDS);
    }

    public void testValidParseDateString() throws ParseException {
        final String dateString = "2014-12-01";
        DateUtil dateUtil = new DateUtil();

        Date parsedDate = dateUtil.getDateFromString(dateString);

        assertEquals("Invalid day", 1, parsedDate.getDay());
        assertEquals("Invalid month", 11, parsedDate.getMonth()); // Jan == 0
        assertEquals("Invalid year", 2014-1900, parsedDate.getYear());// 2014 - 1900 = 114
    }

    public void testValidDateSerialization() {
        DateUtil dateUtil = new DateUtil();
        Date date = new Date(2014-1900, 11, 1);
        final String expectedDateString = "2014-12-01";

        assertEquals(expectedDateString, dateUtil.getDateAsString(date));
    }

    public void testValidTimeStampParsing() throws ParseException {
        final String timestampString = "1577836800";
        DateUtil dateUtil = new DateUtil();

        Date parsedDate = dateUtil.getDateFromTimestamp(timestampString);

        assertEquals(0, parsedDate.getMonth());
        assertEquals(3, parsedDate.getDay());
        assertEquals(2020 - 1900, parsedDate.getYear());
    }
}
