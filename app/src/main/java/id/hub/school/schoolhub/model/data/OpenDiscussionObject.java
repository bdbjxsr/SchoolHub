package id.hub.school.schoolhub.model.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("OpenDiscussion")
public class OpenDiscussionObject extends ParseObject {
    public static final String KEY_USER = "user";
    public static final String KEY_ANSWER = "answer";
    public static final String RUANG_DISKUSI = "ruangDiskusi";

    public void setUser(ParseUser user) { put(KEY_USER, user); }

    public ParseUser getUser() { return getParseUser(KEY_USER); }

    public void setAnswer(String answer) { put(KEY_ANSWER, answer); }

    public String getAnswer() { return getString(KEY_ANSWER); }

    public void setRuangDiskusi(RuangDiskusiObject ruangDiskusi) {
        put(RUANG_DISKUSI, ruangDiskusi);
    }

    public ParseObject getRuangDiskusi() { return getParseObject(RUANG_DISKUSI); }
}
