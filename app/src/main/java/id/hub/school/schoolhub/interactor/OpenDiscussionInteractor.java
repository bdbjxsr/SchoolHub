package id.hub.school.schoolhub.interactor;

import id.hub.school.schoolhub.presenter.OpenDiscussionListener;

public interface OpenDiscussionInteractor {
    void loadCommentDiscussion(String objectId, OpenDiscussionListener listener);
}
