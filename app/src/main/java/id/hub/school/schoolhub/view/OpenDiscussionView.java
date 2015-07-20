package id.hub.school.schoolhub.view;

import java.util.List;

import id.hub.school.schoolhub.model.data.OpenDiscussionObject;

public interface OpenDiscussionView extends LoadDataView {
    void showComment(List<OpenDiscussionObject> list);

    void showEmptyTextView();

    void hideEmptyTextView();

    void navigateToCreateComment(String objectId);

    String getDiscussionObectId();

    void hideRefresh();

    void addMoreList(List<OpenDiscussionObject> list);
}
