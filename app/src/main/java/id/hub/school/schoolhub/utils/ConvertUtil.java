package id.hub.school.schoolhub.utils;

public final class ConvertUtil {
    public ConvertUtil() {}

    public static String convertToDayName(int position) {
        switch (position) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
        }
        return null;
    }
}
