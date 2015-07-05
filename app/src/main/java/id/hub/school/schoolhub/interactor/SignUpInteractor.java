package id.hub.school.schoolhub.interactor;

import id.hub.school.schoolhub.presenter.SignUpFinishListener;

public interface SignUpInteractor {
    void registerNewStudent(String studentNumber, String schoolId, String fullName, String password,
                            SignUpFinishListener signUpFinishListener);
}
