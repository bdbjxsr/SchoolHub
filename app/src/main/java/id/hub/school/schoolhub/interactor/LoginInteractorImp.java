package id.hub.school.schoolhub.interactor;

import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.presenter.LoginFinishListener;

@Singleton
public class LoginInteractorImp implements LoginInteractor {

    @Inject public LoginInteractorImp() {}

    @Override
    public void validateCredentials(String username, String password,
                                    LoginFinishListener listener) {
        if (TextUtils.isEmpty(username)){
            listener.onStudentNumberError();
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
        } else {
            listener.onSuccessLogin();
        }
    }
}
