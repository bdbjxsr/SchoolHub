package id.hub.school.schoolhub.presenter;

public interface SignUpFinishListener {
    void onSuccessRegister();

    void onFailedRegister(Exception e);

    void onStudentNumberError();

    void onSchoolNameError();

    void onFullNameError();

    void onPasswordError();
}
