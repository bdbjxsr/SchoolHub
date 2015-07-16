package id.hub.school.schoolhub.interactor;

import id.hub.school.schoolhub.presenter.CreateCommentListener;

public interface CreateCommentInteractor {
    void validateCreateComment(String objectId, String answer, CreateCommentListener listener);
}
