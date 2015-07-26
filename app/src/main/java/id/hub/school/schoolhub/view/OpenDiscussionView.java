package id.hub.school.schoolhub.view;

import java.util.List;

import id.hub.school.schoolhub.model.data.OpenDiscussionObject;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;

public interface OpenDiscussionView extends LoadDataView {
    void showComment(List<OpenDiscussionObject> list);

    void showEmptyTextView();

    void hideEmptyTextView();

    void navigateToCreateComment(String objectId);

    String getDiscussionObectId();

    void hideRefresh();

    void addMoreList(List<OpenDiscussionObject> list);

    void reloadList(List<OpenDiscussionObject> list);

    void showQuestion(RuangDiskusiObject object);
}
