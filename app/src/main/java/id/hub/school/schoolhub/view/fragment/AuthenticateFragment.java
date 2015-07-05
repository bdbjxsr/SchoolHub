package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.AuthenticatePresenter;
import id.hub.school.schoolhub.view.AuthenticateView;

public class AuthenticateFragment extends BaseFragment implements AuthenticateView {

    @Inject AuthenticatePresenter authenticatePresenter;

    private Controller controller;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authentication, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SchoolHubApp.get(getActivity()).component().inject(this);
        authenticatePresenter.setAuthenticateView(this);
    }

    @OnClick(R.id.login_button)
    void onButtonLoginClick() {
        authenticatePresenter.onButtonLoginClick();
    }

    @OnClick(R.id.signup_button)
    void onButtonSignupClick() {
        authenticatePresenter.onButtonSignupClick();
    }

    @Override
    public void navigateToLoginActivity() {
        controller.navigatetoLoginActivity();
    }

    @Override
    public void navigateToSignupActivity() {
        controller.navigateToSignupActivity();
    }

    public interface Controller {
        void navigatetoLoginActivity();

        void navigateToSignupActivity();
    }

}
