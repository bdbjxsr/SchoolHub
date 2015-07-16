package id.hub.school.schoolhub.presenter;

public interface CreateCommentListener {
    void onAnswerError(String message);

    void onSuccessCreateComment();

    void onFailedCreateComment(String message);
}
