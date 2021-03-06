package id.hub.school.schoolhub.presenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.DiscussionRoomInteractorImp;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.view.DiscussionView;

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

        if (list.size() > 0 ){
            view.hideEmptyView();
            view.showDiscussionRoom(list);
        } else {
            view.showEmptyView();
        }
    }

    @Override
    public void onLoadFailed(String message) {
        view.showError(message);
        view.hideEmptyView();
        view.hideLoading();
    }

    @Override
    public void onReloadSuccess(List<RuangDiskusiObject> list) {
        view.hideRefreshLoading();
        view.reloadDiscussionRoom(list);
    }

    @Override
    public void onLoadMoreSuccess(List<RuangDiskusiObject> list) {
        view.addMoreList(list);
    }

    public void reloadDiscussionRoom() {
        interactorImp.reloadDiscussionRoom(this); }

    public void loadMoreDiscussionRoom(int loadMore) {
        interactorImp.loadMoreDiscussionRoom(loadMore, this);
    }
}
