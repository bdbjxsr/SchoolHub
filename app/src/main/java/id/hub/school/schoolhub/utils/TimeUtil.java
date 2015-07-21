package id.hub.school.schoolhub.utils;

import java.util.Calendar;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.getInstance;

public final class TimeUtil {
    public TimeUtil() {}

    public static int getHourFromString(String time) {
        return Integer.parseInt(splitTimeString(time)[0]);
    }

    public static int getMinuteFromString(String time) {
        return Integer.parseInt(splitTimeString(time)[1]);
    }

    public static String[] splitTimeString(String time) {
        return time.split(":");
    }

    public static int getCurrentDayOnWeek() {
        Calendar c = getInstance();
        int dayOfWeek = c.get(DAY_OF_WEEK);
        if (dayOfWeek == 1 || dayOfWeek == 7) {
            dayOfWeek = 0;
        } else {
            dayOfWeek = dayOfWeek - 2;
        }

        return dayOfWeek;
    }

}
