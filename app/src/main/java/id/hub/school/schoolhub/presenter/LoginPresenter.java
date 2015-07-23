package id.hub.school.schoolhub.presenter;

import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.LoginInteractorImp;
import id.hub.school.schoolhub.view.LoginView;

@Singleton
public class LoginPresenter implements BasePresenter, LoginFinishListener {

    private LoginView loginView;

    @Inject LoginInteractorImp loginInteractor;

    @Inject public LoginPresenter() {}

    public void setLoginView(LoginView loginView) { this.loginView = loginView; }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    public void onLoginButtonClick() {
        loginView.hideStudentNumberError();
        loginView.hidePasswordError();

        if (TextUtils.isEmpty(loginView.getStudentNumber())) {
            loginView.showStudentNumberError("Student number cannot be empty");
        } else if (TextUtils.isEmpty(loginView.getPassword())) {
            loginView.showPasswordError("Password cannot be empty");
        } else {
            loginView.showLoadingView();
            loginInteractor.validateCredentials(loginView.getStudentNumber(),
                    loginView.getPassword(), this);
        }
    }

    @Override
    public void onSuccessLogin() {
        loginView.hideLoadingView();
        loginView.navigateToMainActivity();
    }

    @Override
    public void onFailedLogin(Exception e) {
        loginView.hideLoadingView();
        loginView.showError(e.getMessage());
    }

    @Override
    public void onStudentNumberError() {
        loginView.showStudentNumberError("Student cannot be empty.");
    }

    @Override
    public void onPasswordError() {
        loginView.showPasswordError("Password cannot be empty.");
    }
}
