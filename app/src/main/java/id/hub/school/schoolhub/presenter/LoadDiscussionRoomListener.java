package id.hub.school.schoolhub.presenter;

import java.util.List;

import id.hub.school.schoolhub.model.data.RuangDiskusiObject;

public interface LoadDiscussionRoomListener {
    void onLoadSuccess(List<RuangDiskusiObject> list);

    void onLoadFailed(String message);

    void onLoadMoreSuccess(List<RuangDiskusiObject> list);
}
