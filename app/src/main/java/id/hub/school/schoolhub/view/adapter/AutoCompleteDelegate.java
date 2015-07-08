package id.hub.school.schoolhub.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import java.io.IOException;
import java.io.Reader;

import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.model.data.Sekolah;
import id.hub.school.schoolhub.utils.AssetsUtil;
import timber.log.Timber;

public class AutoCompleteDelegate {

    public static final String EXTRA_SEKOLAH = "extra_sekolah";

    private Context context;
    private Sekolah sekolah;

    public AutoCompleteDelegate(Context context) {
        this.context = context;
    }

    public void onRestoreState(Bundle savedInstanceState, AutoCompleteTextView schoolNameEditText) {
        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_SEKOLAH)) {
            sekolah = AssetsUtil.GSON.fromJson(savedInstanceState.getString(EXTRA_SEKOLAH),
                    Sekolah.class);
            AutoCompleteParseAdapter adapter = new AutoCompleteParseAdapter(context,
                    R.layout.list_item, sekolah.results);
            schoolNameEditText.setAdapter(adapter);
        } else {
            init(schoolNameEditText);
        }
    }

    private void init(AutoCompleteTextView schoolNameEditText) {
        Reader reader = null;
        try {
            reader = AssetsUtil.readFile(context, "Sekolah.json");
            sekolah = AssetsUtil.GSON.fromJson(reader, Sekolah.class);
            schoolNameEditText.setAdapter(new AutoCompleteParseAdapter(context,
                    R.layout.list_item, sekolah.results));
        } catch (IOException e) {
            throw new RuntimeException("Unexpected error");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                Timber.e("Reader already been closed");
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if (sekolah != null) {
            outState.putString(EXTRA_SEKOLAH, AssetsUtil.GSON.toJson(sekolah));
        }
    }

    public void setOnItemClick(final AutoCompleteTextView schoolNameEditText) {
        if (schoolNameEditText != null) {
            schoolNameEditText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Sekolah.Results results = (Sekolah.Results) schoolNameEditText.getAdapter()
                            .getItem(position);
                    schoolNameEditText.setText(results.Sekolah);
                }
            });
        }
    }
}
