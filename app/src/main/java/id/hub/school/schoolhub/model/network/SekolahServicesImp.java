package id.hub.school.schoolhub.model.network;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.model.data.SekolahObject;
import id.hub.school.schoolhub.presenter.ParseQueryCompleteListener;

@Singleton
public class SekolahServicesImp implements SekolahServices {

    @Inject ParseQuery<SekolahObject> parseQuery;

    @Inject
    public SekolahServicesImp() {}

    @Override
    public void getListSekolah(ParseQueryCompleteListener listener) {
        parseQuery.findInBackground(new FindCallback<SekolahObject>() {
            @Override
            public void done(List<SekolahObject> list, ParseException e) {
                Log.v("TAG SEKOLAH", list.get(0).get("Sekolah").toString());
            }
        });
    }

    @Override
    public void getListSekolah(String name, final ParseQueryCompleteListener listener) {
        Log.v("KEY", name);
        parseQuery.cancel();
        parseQuery.whereContains("School", name)
                .findInBackground(new FindCallback<SekolahObject>() {
                    @Override
                    public void done(List<SekolahObject> list, ParseException e) {
                        Log.v("TAG SEKOLAH", list.get(0).getSekolah());
                        if (e == null) {
                            listener.onQuerySuccess(list);
                        } else {
                            listener.onQueryFailed(e);
                        }
                    }
                });
    }
}
