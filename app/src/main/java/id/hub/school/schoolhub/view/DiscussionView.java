package id.hub.school.schoolhub.view;

import java.util.List;

import id.hub.school.schoolhub.model.data.RuangDiskusiObject;

public interface DiscussionView extends LoadDataView {
    void showLoading();

    void hideLoading();

    void showDiscussionRoom(List<RuangDiskusiObject> list);

    void navigateToCreateDiscussion();

    void hideRefreshLoading();
}
