package id.hub.school.schoolhub.interactor;

import id.hub.school.schoolhub.presenter.OpenDiscussionListener;
import id.hub.school.schoolhub.presenter.OpenDiscussionPresenter;

public interface OpenDiscussionInteractor {
    void loadCommentDiscussion(String objectId, OpenDiscussionListener listener);

    void reloadCommentDiscussion(String objectId, OpenDiscussionListener listener);

    void loadMoreCommentDiscussion(String objectId, int current_page, OpenDiscussionListener listener);
}
