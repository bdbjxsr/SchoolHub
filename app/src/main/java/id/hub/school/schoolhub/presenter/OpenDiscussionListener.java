package id.hub.school.schoolhub.presenter;

import java.util.List;

import id.hub.school.schoolhub.model.data.OpenDiscussionObject;

public interface OpenDiscussionListener {
    void onLoadSuccess(List<OpenDiscussionObject> list);

    void onLoadFailed(String message);

    void onLoadMoreSuccess(List<OpenDiscussionObject> list);

    void onReloadSuccess(List<OpenDiscussionObject> list);
}
