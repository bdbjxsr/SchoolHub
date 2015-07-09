package id.hub.school.schoolhub.view;

import java.io.File;

public interface DiscussionFormView extends LoadDataView {
    void showJudulError(String message);

    void showQuestionError(String message);

    String getJudul();

    String getQuestion();

    String getKategori();

    File getImage();
}
