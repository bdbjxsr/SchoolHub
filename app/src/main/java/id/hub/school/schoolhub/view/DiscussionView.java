package id.hub.school.schoolhub.view;

import java.util.List;

import id.hub.school.schoolhub.model.data.RuangDiskusiObject;

public interface DiscussionView extends LoadDataView {
    void showLoading();

    void hideLoading();

    void showEmptyView();

    void hideEmptyView();

    void showDiscussionRoom(List<RuangDiskusiObject> list);

    void navigateToCreateDiscussion();

    void reloadDiscussionRoom(List<RuangDiskusiObject> list);

    void hideRefreshLoading();

    void addMoreList(List<RuangDiskusiObject> list);
}
