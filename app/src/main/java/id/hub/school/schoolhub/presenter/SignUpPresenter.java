package id.hub.school.schoolhub.presenter;

import android.text.TextUtils;
import android.widget.CompoundButton;

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
        signupView.hideSchoolNameError();
        signupView.hideStudentNumberError();
        signupView.hideFullNameError();
        signupView.hidePasswordError();

        if (TextUtils.isEmpty(signupView.getSchoolName())) {
            signupView.showSchoolNameError("School name cannot be empty.");
        } else if (TextUtils.isEmpty(signupView.getStudentNumber())) {
            signupView.showStudentNumberError("Student number cannot be empty.");
        } else if (TextUtils.isEmpty(signupView.getFullName())) {
            signupView.showFullnameError("Fullname cannot be empty.");
        } else if (TextUtils.isEmpty(signupView.getPassword())) {
            signupView.showPasswordError("Password cannot be empty.");
        } else {
            signupView.showLoadingView();
            signUpInteractor.registerNewStudent(
                    signupView.getStudentNumber(),
                    signupView.getSchoolName(),
                    signupView.getFullName(),
                    signupView.getPassword(),
                    this);
        }
    }

    @Override
    public void onSuccessRegister() {
        signupView.hideLoadingView();
        signupView.navigateToMainActivity();
    }

    @Override
    public void onFailedRegister(Exception e) {
        signupView.hideLoadingView();
        signupView.showError(e.getMessage());
    }

    @Override
    public void onStudentNumberError() {
        signupView.showStudentNumberError("Student number cannot be empty.");
    }

    @Override
    public void onSchoolNameError() {
        signupView.showSchoolNameError("School name cannot be empty.");
    }

    public void onShowPasswordCheckedChange(CompoundButton view) {
        signupView.toogleShowHidePassword(view.isChecked());
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
