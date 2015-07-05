package id.hub.school.schoolhub.presenter;

public interface SignUpFinishListener {
    void onSuccessRegister();

    void onFailedRegister();

    void onStudentNumberError();

    void onSchoolNameError();

    void onFullNameError();

    void onPasswordError();
}
