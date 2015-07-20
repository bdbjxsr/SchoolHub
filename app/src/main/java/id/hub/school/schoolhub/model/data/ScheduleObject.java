package id.hub.school.schoolhub.model.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("Schedule")
public class ScheduleObject extends ParseObject {
    public static final String TITLE = "title";
    public static final String USER = "user";
    public static final String UUID = "uuid";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String DAY = "day";
    public static final String NOTIFICATION = "notification";

    public String getTITLE() { return getString(TITLE); }

    public ParseUser getUSER() { return getParseUser(USER); }

    public String getUUID() { return getString(UUID); }

    public String getDATE() { return getString(DATE); }

    public String getTIME() { return getString(TIME); }

    public String getDAY() { return getString(DAY); }

    public boolean getNOTIFICATION() { return getBoolean(NOTIFICATION); }

    public void setTitle(String title) { put(TITLE, title); }

    public void setUser(ParseUser user) { put(USER, user); }

    public void setUuid(String uuid) { put(UUID, uuid); }

    public void setDate(String date) { put(DATE, date); }

    public void setTime(String time) { put(TIME, time); }

    public void setDay(String day) { put(DAY, day); }

    public void setNotification(boolean notification) { put(NOTIFICATION, notification); }

    public static ParseQuery<ScheduleObject> getQuery() {
        return ParseQuery.getQuery(ScheduleObject.class);
    }
}
