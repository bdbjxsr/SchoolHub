package id.hub.school.schoolhub.interactor;

import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.presenter.SignUpFinishListener;
import id.hub.school.schoolhub.view.SignupView;

@Singleton
public class SignUpInteractorImp implements SignUpInteractor {

    @Inject public SignUpInteractorImp() {}

    @Override
    public void registerNewStudent(String studentNumber, String schoolName, String fullName,
                                   String password, SignUpFinishListener signUpFinishListener) {
        if (TextUtils.isEmpty(schoolName)) {
            signUpFinishListener.onSchoolNameError();
        } else if (TextUtils.isEmpty(studentNumber)) {
            signUpFinishListener.onStudentNumberError();
        } else if (TextUtils.isEmpty(fullName)) {
            signUpFinishListener.onFullNameError();
        } else if (TextUtils.isEmpty(password)) {
            signUpFinishListener.onPasswordError();
        } else {
            signUpFinishListener.onSuccessRegister();
        }
    }
}
