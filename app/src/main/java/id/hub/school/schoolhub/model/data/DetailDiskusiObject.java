package id.hub.school.schoolhub.model.data;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Sekolah")
public class DetailDiskusiObject extends ParseObject{
    public DetailDiskusiObject() {}

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser user) {
        put("user", user);
    }

    public ParseObject getRuangDiskusi() {
        return getParseObject("ruangDiskusi");
    }

    public void setRuangDiskusi(ParseObject object) {
        put("ruangDiskui", object);
    }
}
