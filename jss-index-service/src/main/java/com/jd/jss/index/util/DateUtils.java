package com.jd.jss.index.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Date;
import java.util.Locale;

/**
 * Date parsing and writing utilities.
 */
public final class DateUtils {

    public static final DateTimeFormatter RFC1123_DATE_FORMAT = DateTimeFormat
            .forPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'").withLocale(Locale.US).withZone(DateTimeZone.UTC);

    private static final DateTimeFormatter ISO8601_DATE_FORMAT = ISODateTimeFormat.dateTime()
            .withZone(DateTimeZone.UTC);

    private DateUtils() {
    }

    /**
     * Parses an RFC1123 format date.  Returns null if the date fails to parse for
     * any reason.
     *
     * @param dateStr
     * @return the date
     */
    public static Date parseRfc1123Date(String dateStr) {
        try {
            return RFC1123_DATE_FORMAT.parseDateTime(dateStr).toDate();
        } catch (Exception e) {
            // Don't care.
            return null;
        }
    }

    /**
     * Parses an ISO8601 formatted datetime into a Date or null
     * is parsing fails.
     *
     * @param dateStr A datetime string in ISO8601 format
     * @return the date
     */
    public static Date parseIso8601DateTime(String dateStr) {
        try {
            // joda does our ISO 8601 parsing
            return new DateTime(dateStr).toDate();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Formats an ISO 8601 format date.
     */
    public static String formatIso8601Date(Date date) {
        return formatIso8601Date(date.getTime());
    }

    /**
     * Formats an ISO 8601 format date.
     */
    public static String formatIso8601Date(long time) {
        return ISO8601_DATE_FORMAT.print(time);
    }

    /**
     * Formats an RFC 1123 format date.
     */
    public static String formatRfc1123Date(Date date) {
        return formatRfc1123Date(date.getTime());
    }

    /**
     * Formats an RFC 1123 format date.
     */
    public static String formatRfc1123Date(long timeStamp) {
        return RFC1123_DATE_FORMAT.print(timeStamp);
    }

    public static void main(String[] args) {
        System.out.println(formatRfc1123Date((new Date())));
    }

}