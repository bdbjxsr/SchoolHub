package id.hub.school.schoolhub.presenter;

public interface LoginFinishListener {
    void onSuccessLogin();

    void onFailedLogin(Exception e);

    void onStudentNumberError();

    void onPasswordError();
}
