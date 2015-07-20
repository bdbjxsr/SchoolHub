package id.hub.school.schoolhub.presenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.OpenDiscussionInteractorImp;
import id.hub.school.schoolhub.model.data.OpenDiscussionObject;
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

    public void refreshComment(String objectId) {
        interactorImp.loadCommentDiscussion(objectId, this);
    }
}
