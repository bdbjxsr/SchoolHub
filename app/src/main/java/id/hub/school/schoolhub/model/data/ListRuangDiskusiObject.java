package id.hub.school.schoolhub.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListRuangDiskusiObject implements Parcelable, List<RuangDiskusiObject> {

    protected ListRuangDiskusiObject(Parcel in) {
    }

    public static final Creator<ListRuangDiskusiObject> CREATOR =
            new Creator<ListRuangDiskusiObject>() {
        @Override
        public ListRuangDiskusiObject createFromParcel(Parcel in) {
            return new ListRuangDiskusiObject(in);
        }

        @Override
        public ListRuangDiskusiObject[] newArray(int size) {
            return new ListRuangDiskusiObject[size];
        }
    };

    @Override
    public void add(int location, RuangDiskusiObject object) {

    }

    @Override
    public boolean add(RuangDiskusiObject object) {
        return false;
    }

    @Override
    public boolean addAll(int location, Collection<? extends RuangDiskusiObject> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends RuangDiskusiObject> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }

    @Override
    public RuangDiskusiObject get(int location) {
        return null;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @NonNull
    @Override
    public Iterator<RuangDiskusiObject> iterator() {
        return null;
    }

    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public ListIterator<RuangDiskusiObject> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<RuangDiskusiObject> listIterator(int location) {
        return null;
    }

    @Override
    public RuangDiskusiObject remove(int location) {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public RuangDiskusiObject set(int location, RuangDiskusiObject object) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @NonNull
    @Override
    public List<RuangDiskusiObject> subList(int start, int end) {
        return null;
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @NonNull
    @Override
    public <T> T[] toArray(T[] array) {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
