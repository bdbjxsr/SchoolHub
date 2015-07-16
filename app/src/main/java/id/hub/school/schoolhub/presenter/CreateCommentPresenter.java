package id.hub.school.schoolhub.presenter;

import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.CreateCommentInteractorImp;
import id.hub.school.schoolhub.view.CreateCommentView;

@Singleton
public class CreateCommentPresenter implements BasePresenter, CreateCommentListener {

    private CreateCommentView view;

    @Inject CreateCommentInteractorImp interactorImp;

    @Inject public CreateCommentPresenter() {}

    public void setView(CreateCommentView view) { this.view = view; }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    public void onSubmitClick() {
        view.hideQuestionError();
        if (TextUtils.isEmpty(view.getAnswerText())) {
            onAnswerError("Answer cannot be empty.");
        } else {
            interactorImp.validateCreateComment(view.getObjectId(), view.getAnswerText(), this);
        }
    }

    @Override
    public void onAnswerError(String message) {
        view.showQuestionError(message);
    }

    @Override
    public void onSuccessCreateComment() {
        view.hideProgress();
        view.finish();
    }

    @Override
    public void onFailedCreateComment(String message) {
        view.hideProgress();
        view.showError(message);
    }
}
