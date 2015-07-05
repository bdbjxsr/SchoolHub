package id.hub.school.schoolhub.presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.SignUpInteractorImp;
import id.hub.school.schoolhub.view.SignupView;

@Singleton
public class SignUpPresenter implements BasePresenter, SignUpFinishListener {

    private SignupView signupView;

    @Inject SignUpInteractorImp signUpInteractor;

    @Inject public SignUpPresenter() {}

    public void setSignupView(SignupView signupView) {
        this.signupView = signupView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void onSignUpClick() {
        signUpInteractor.registerNewStudent(signupView.getStudentNumber(),
                signupView.getSchoolName(), signupView.getFullName(), signupView.getPassword(), this);
    }

    @Override
    public void onSuccessRegister() {
        signupView.navigateToMainActivity();
    }

    @Override
    public void onFailedRegister() {

    }

    @Override
    public void onStudentNumberError() {
        signupView.showStudentNumberError("Student number cannot be empty.");
    }

    @Override
    public void onSchoolNameError() {
        signupView.showSchoolNameError("School name cannot be empty.");
    }

    @Override
    public void onFullNameError() {
        signupView.showFullnameError("Fullname cannot be empty.");
    }

    @Override
    public void onPasswordError() {
        signupView.showPasswordError("Password cannot be empty.");
    }
}
