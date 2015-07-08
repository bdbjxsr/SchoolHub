package id.hub.school.schoolhub.view;

public interface SignupView extends LoadDataView {
    void showSchoolNameError(String message);

    void showStudentNumberError(String message);

    void showFullnameError(String message);

    void showPasswordError(String message);

    void toogleShowHidePassword(boolean checked);

    void showLoadingView();

    void hideLoadingView();

    String getSchoolName();

    String getStudentNumber();

    String getFullName();

    String getPassword();

    void navigateToMainActivity();
}
