package id.hub.school.schoolhub.model.data;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("RuangDiskusi")
public class RuangDiskusiObject extends ParseObject {

    public static final String USER = "user";
    public static final String JUDUL = "judul";
    public static final String KATEGORI = "kategori";
    public static final String QUESTION = "question";
    public static final String IMAGE = "image";

    public RuangDiskusiObject() {}

    public void setUser(ParseUser user) { put(USER, user); }

    public ParseUser getUser() { return getParseUser(USER); }

    public void setJudul(String judul) { put(JUDUL, judul); }

    public String getJudul() { return getString(JUDUL); }

    public void setKategori(String kategori) { put(KATEGORI, kategori); }

    public String getKategori() { return getString(KATEGORI); }

    public void setQuestion (String question) { put(QUESTION, question); }

    public String getQuestion() { return getString(QUESTION); }

    public void setImage(ParseFile file) { put(IMAGE, file); }

    public ParseFile getImage() { return getParseFile(IMAGE); }
}
