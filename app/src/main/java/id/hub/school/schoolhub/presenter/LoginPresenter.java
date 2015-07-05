package id.hub.school.schoolhub.presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.LoginInteractor;
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
        loginInteractor.validateCredentials(loginView.getStudentNumber(),
                loginView.getPassword(), this);
    }

    @Override
    public void onSuccessLogin() {
        loginView.navigateToMainActivity();
    }

    @Override
    public void onFailedLogin(Exception e) {

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
