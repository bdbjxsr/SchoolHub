package id.hub.school.schoolhub.view;

public interface CreateCommentView extends LoadDataView{
    void showQuestionError(String message);

    void hideQuestionError();

    String getObjectId();

    String getAnswerText();

    void finish();
}
