package id.hub.school.schoolhub.interactor;

import android.text.TextUtils;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.presenter.LoginFinishListener;

@Singleton
public class LoginInteractorImp implements LoginInteractor {

    @Inject public LoginInteractorImp() {}

    @Override
    public void validateCredentials(String username, String password,
                                    final LoginFinishListener listener) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {
                    listener.onSuccessLogin();
                } else {
                    listener.onFailedLogin(e);
                }
            }
        });
    }
}
