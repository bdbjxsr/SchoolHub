package id.hub.school.schoolhub.presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.view.DiscussionView;

@Singleton
public class DiscussionPresenter implements BasePresenter {

    @Inject public DiscussionPresenter() {}

    private DiscussionView discussionView;

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void setDiscussionView(DiscussionView discussionView) {
        this.discussionView = discussionView;
    }

    public void onFABClick() {
        if (discussionView != null) {
            discussionView.navigateToCreateDiscussion();
        }
    }
}
