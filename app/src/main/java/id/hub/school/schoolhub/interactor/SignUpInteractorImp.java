package id.hub.school.schoolhub.interactor;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.presenter.SignUpFinishListener;

@Singleton
public class SignUpInteractorImp implements SignUpInteractor {

    @Inject
    public SignUpInteractorImp() {}

    @Override
    public void registerNewStudent(String studentNumber, String schoolName, String fullName,
                                   String password,
                                   final SignUpFinishListener signUpFinishListener) {
        ParseUser dataObject = new ParseUser();
        dataObject.put("username", studentNumber);
        dataObject.put("password", password);
        dataObject.put("schoolName", schoolName);
        dataObject.put("fullName", fullName);
        dataObject.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    signUpFinishListener.onSuccessRegister();
                } else {
                    signUpFinishListener.onFailedRegister(e);
                }
            }
        });
    }
}
