package id.hub.school.schoolhub.view;

public interface LoginView extends LoadDataView {
    void showStudentNumberError(String message);

    void showPasswordError(String message);

    void showLoadingView();

    void hideLoadingView();

    void navigateToMainActivity();

    String getStudentNumber();

    String getPassword();
}
