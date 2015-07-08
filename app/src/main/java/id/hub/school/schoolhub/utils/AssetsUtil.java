package id.hub.school.schoolhub.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class AssetsUtil {

    public AssetsUtil() {}

    public static final Gson GSON = new GsonBuilder().create();

    public static Reader readFile(Context context, String fileName) throws IOException {
        InputStream inputStream = context.getAssets().open(fileName);
        Reader reader = new InputStreamReader(inputStream, "utf-8");
        return reader;
    }
}
