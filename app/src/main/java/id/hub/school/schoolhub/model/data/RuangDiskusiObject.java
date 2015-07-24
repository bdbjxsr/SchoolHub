package id.hub.school.schoolhub.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("RuangDiskusi")
public class RuangDiskusiObject extends ParseObject implements Parcelable {

    public static final String KEY_USER = "user";
    public static final String KEY_JUDUL = "judul";
    public static final String KEY_KATEGORI = "kategori";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_COMMENT_COUNT = "commentCount";

    public RuangDiskusiObject() {}

    protected RuangDiskusiObject(Parcel in) {}

    public static final Creator<RuangDiskusiObject> CREATOR = new Creator<RuangDiskusiObject>() {
        @Override
        public RuangDiskusiObject createFromParcel(Parcel in) {
            return new RuangDiskusiObject(in);
        }

        @Override
        public RuangDiskusiObject[] newArray(int size) {
            return new RuangDiskusiObject[size];
        }
    };

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

    public void setCommentCount(int commentCount) { put(KEY_COMMENT_COUNT, commentCount); }

    public int getCommentCount() { return getInt(KEY_COMMENT_COUNT); }

    public Date getCreatedDate() { return getCreatedAt(); }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getJudul());
        dest.writeString(getKategori());
        dest.writeString(getQuestion());
        dest.writeInt(getCommentCount());
        dest.writeValue(getUser());
        dest.writeValue(getImage());
        dest.writeValue(getCreatedDate());
    }
}
