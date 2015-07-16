package id.hub.school.schoolhub.model.data;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("RuangDiskusi")
public class RuangDiskusiObject extends ParseObject {

    public static final String KEY_USER = "user";
    public static final String KEY_JUDUL = "judul";
    public static final String KEY_KATEGORI = "kategori";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_IMAGE = "image";

    public RuangDiskusiObject() {}

    public void setUser(ParseUser user) { put(KEY_USER, user); }

    public ParseUser getUser() { return getParseUser(KEY_USER); }

    public void setJudul(String judul) { put(KEY_JUDUL, judul); }

    public String getJudul() { return getString(KEY_JUDUL); }

    public void setKategori(String kategori) { put(KEY_KATEGORI, kategori); }

    public String getKategori() { return getString(KEY_KATEGORI); }

    public void setQuestion (String question) { put(KEY_QUESTION, question); }

    public String getQuestion() { return getString(KEY_QUESTION); }

    public void setImage(ParseFile file) { put(KEY_IMAGE, file); }

    public ParseFile getImage() { return getParseFile(KEY_IMAGE); }
}
