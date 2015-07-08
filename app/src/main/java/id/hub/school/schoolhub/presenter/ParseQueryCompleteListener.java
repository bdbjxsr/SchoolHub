package id.hub.school.schoolhub.presenter;

import java.util.List;

import id.hub.school.schoolhub.model.data.SekolahObject;

public interface ParseQueryCompleteListener {
    void onQuerySuccess(List<SekolahObject> list);

    void onQueryFailed(Exception e);
}
