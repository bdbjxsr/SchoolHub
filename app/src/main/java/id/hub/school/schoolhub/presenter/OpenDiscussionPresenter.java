package id.hub.school.schoolhub.presenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.OpenDiscussionInteractorImp;
import id.hub.school.schoolhub.model.data.OpenDiscussionObject;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.view.OpenDiscussionView;

@Singleton
public class OpenDiscussionPresenter implements BasePresenter, OpenDiscussionListener {

    @Inject OpenDiscussionInteractorImp interactorImp;

    private OpenDiscussionView view;

    @Inject
    public OpenDiscussionPresenter() {}

    public void setView(OpenDiscussionView view) { this.view = view; }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    public void loadComment(String objectId){
        view.showProgress();

        view.hideEmptyTextView();
        view.hideRetry();

        interactorImp.loadCommentDiscussion(objectId, this);
    }

    public void onFABClick() { view.navigateToCreateComment(view.getDiscussionObectId()); }

    @Override
    public void onLoadSuccess(List<OpenDiscussionObject> list) {
        view.hideProgress();
        view.hideRefresh();
        view.hideEmptyTextView();

        if (list.size() > 0) {
            view.showComment(list);
        } else {
            view.showEmptyTextView();
        }
    }

    @Override
    public void onLoadFailed(String message) {
        view.hideProgress();
        view.hideRefresh();
        view.showRetry();
        view.showError(message);
    }

    @Override
    public void onLoadMoreSuccess(List<OpenDiscussionObject> list) { view.addMoreList(list); }

    @Override
    public void onReloadSuccess(List<OpenDiscussionObject> list) {
        view.hideRefresh();
        view.hideProgress();
        view.hideEmptyTextView();
        view.reloadList(list);
    }

    @Override
    public void getDiscussionFromLocalSuccess(RuangDiskusiObject object) {
        view.showQuestion(object);
    }

    @Override
    public void getDiscussionFromLocalFailed(String message) {
        view.showError(message);
    }

    public void refreshComment(String objectId) {
        view.showProgress();
        interactorImp.reloadCommentDiscussion(objectId, this);
    }

    public void loadMoreComment(String objectId, int loadMore) {
        interactorImp.loadMoreCommentDiscussion(objectId, loadMore, this);
    }

    public void getDiscussionFromLocal(String objectId) {
        interactorImp.getDiscussionFromLocal(objectId, this);
    }
}
