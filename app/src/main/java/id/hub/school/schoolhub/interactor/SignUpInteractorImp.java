package id.hub.school.schoolhub.interactor;

import android.text.TextUtils;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.presenter.SignUpFinishListener;
import id.hub.school.schoolhub.view.SignupView;

@Singleton
public class SignUpInteractorImp implements SignUpInteractor {

    @Inject public SignUpInteractorImp() {}

    @Override
    public void registerNewStudent(String studentNumber, String schoolName, String fullName,
                                   String password, final SignUpFinishListener signUpFinishListener) {
        if (TextUtils.isEmpty(schoolName)) {
            signUpFinishListener.onSchoolNameError();
        } else if (TextUtils.isEmpty(studentNumber)) {
            signUpFinishListener.onStudentNumberError();
        } else if (TextUtils.isEmpty(fullName)) {
            signUpFinishListener.onFullNameError();
        } else if (TextUtils.isEmpty(password)) {
            signUpFinishListener.onPasswordError();
        } else {
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
}
