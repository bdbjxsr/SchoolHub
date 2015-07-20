package id.hub.school.schoolhub.utils;

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

}
