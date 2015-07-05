package id.hub.school.schoolhub.presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.view.AuthenticateView;

@Singleton
public class AuthenticatePresenter implements BasePresenter {

    private AuthenticateView authenticateView;

    @Inject
    public AuthenticatePresenter() {}

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    public void setAuthenticateView(AuthenticateView authenticateView) {
        this.authenticateView = authenticateView;
    }

    public void onButtonLoginClick() {
        if (authenticateView != null) {
            authenticateView.navigateToLoginActivity();
        }
    }

    public void onButtonSignupClick() {
        if (authenticateView != null) {
            authenticateView.navigateToSignupActivity();
        }
    }


}
