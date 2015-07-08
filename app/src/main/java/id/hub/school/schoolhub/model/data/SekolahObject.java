package id.hub.school.schoolhub.model.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Sekolah")
public class SekolahObject extends ParseObject {
    public SekolahObject() {}

    public String getSekolah() { return getString("Sekolah"); }
}
