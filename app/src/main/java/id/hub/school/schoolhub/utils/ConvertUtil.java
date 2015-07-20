package id.hub.school.schoolhub.utils;

public final class ConvertUtil {

    public static final String MONDAY = "Monday";
    public static final String TUESDAY = "Tuesday";
    public static final String WEDNESDAY = "Wednesday";
    public static final String THURSDAY = "Thursday";
    public static final String FRIDAY = "Friday";

    public ConvertUtil() {}

    public static String convertToDayName(int position) {
        switch (position) {
            case 0:
                return MONDAY;
            case 1:
                return TUESDAY;
            case 2:
                return WEDNESDAY;
            case 3:
                return THURSDAY;
            case 4:
                return FRIDAY;
        }
        return null;
    }

    public static int convertToDayPositionOnPage(String day) {
        switch (day) {
            case MONDAY:
                return 0;
            case TUESDAY:
                return 1;
            case WEDNESDAY:
                return 2;
            case THURSDAY:
                return 3;
            case FRIDAY:
                return 4;
        }
        return 0;
    }

    public static int convertToDayPositionOnWeek(String day) {
        switch (day) {
            case MONDAY:
                return 2;
            case TUESDAY:
                return 3;
            case WEDNESDAY:
                return 4;
            case THURSDAY:
                return 5;
            case FRIDAY:
                return 6;
        }
        return 0;
    }
}
