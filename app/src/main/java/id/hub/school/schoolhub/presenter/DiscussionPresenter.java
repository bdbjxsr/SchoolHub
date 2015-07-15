package id.hub.school.schoolhub.presenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.DiscussionRoomInteractorImp;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.view.DiscussionView;
import timber.log.Timber;

@Singleton
public class DiscussionPresenter implements BasePresenter, LoadDiscussionRoomListener {

    @Inject DiscussionRoomInteractorImp interactorImp;

    @Inject public DiscussionPresenter() {}

    private DiscussionView view;

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    public void setDiscussionView(DiscussionView discussionView) { this.view = discussionView; }

    public void onFABClick() {
        if (view != null) {
            view.navigateToCreateDiscussion();
        }
    }

    public void loadDiscussionRoom() {
        view.showLoading();
        interactorImp.loadDiscussionRoom(this);
    }

    @Override
    public void onLoadSuccess(List<RuangDiskusiObject> list) {
        view.hideLoading();
        view.showDiscussionRoom(list);
    }

    @Override
    public void onLoadFailed(String message) {
        view.showError(message);
        view.hideLoading();
    }
}
