package me.gamerzking.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by GamerzKing on 5/21/2016.
 */
public class UtilTime {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilTime() {}

    private static Calendar calendar = Calendar.getInstance();

    private static final String DATE_FORMAT_DETAILED = "MM-dd-yyyy HH:mm:ss";
    private static final String DATE_FORMAT_DATE = "MM-dd-yyyy";

    /**
     * Gets the current time.
     *
     * @return The current time in a detailed format.
     */

    public static String getCurrentTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DETAILED);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Gets the current date.
     *
     * @return The current date in the specific format.
     */

    public static String getDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DATE);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Determines whether time has passed between the two specified times.
     *
     * @param from The original time (mostly {@link System#currentTimeMillis()}
     * @param to The end time.
     * @return True if time has elapsed between the two times; otherwise, false.
     */

    public static boolean elapsed(long from, long to) {
        return System.currentTimeMillis() - from > to;
    }

    /**
     * @return The calendar instance.
     */

    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * @return The detailed date/time format.
     */

    public static String getDateFormatDetailed() {
        return DATE_FORMAT_DETAILED;
    }

    /**
     * @return The default date/time format.
     */

    public static String getDateFormatDate() {
        return DATE_FORMAT_DATE;
    }
}