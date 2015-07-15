package id.hub.school.schoolhub.presenter;

public interface DiscussionFormCreateListener {
    void onSuccess();

    void onFailed(String message);

    void onTitleError(String message);

    void onQuestionError(String message);
}
