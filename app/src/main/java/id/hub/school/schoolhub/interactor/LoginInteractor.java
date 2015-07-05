package id.hub.school.schoolhub.interactor;

import id.hub.school.schoolhub.presenter.LoginFinishListener;

public interface LoginInteractor {
    void validateCredentials(String username, String password, LoginFinishListener listener);
}
