package id.hub.school.schoolhub.presenter;

import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.DiscussionFormInteractorImp;
import id.hub.school.schoolhub.view.DiscussionFormView;

@Singleton
public class DiscussionFormPresenter implements BasePresenter, DiscussionFormCreateListener {

    private DiscussionFormView view;

    @Inject
    DiscussionFormInteractorImp interactorImp;

    @Inject
    public DiscussionFormPresenter() {
    }

    public void setView(DiscussionFormView view) {
        this.view = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    public void onSubmitClick() {
        view.hideJudulError();
        view.hideQuestionError();

        if (TextUtils.isEmpty(view.getJudul())) {
            view.showJudulError("Title cannot be empty");
        } else if (TextUtils.isEmpty(view.getQuestion())) {
            view.showQuestionError("Question cannot be empty");
        } else {
            view.showProgress();
            interactorImp.validateCreateDiscussionForm(view.getKategori(), view.getJudul(),
                    view.getQuestion(), this);
        }
    }

    @Override
    public void onSuccess() {
        view.hideProgress();
        view.finish();
    }

    @Override
    public void onFailed(String message) {
        view.hideProgress();
        view.showError(message);
    }

    @Override
    public void onTitleError(String message) {
        view.showJudulError(message);
    }

    @Override
    public void onQuestionError(String message) {
        view.showQuestionError(message);
    }
}
