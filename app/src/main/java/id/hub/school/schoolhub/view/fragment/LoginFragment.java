package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.LoginPresenter;
import id.hub.school.schoolhub.view.LoginView;

public final class LoginFragment extends BaseFragment implements LoginView {

    @InjectView(R.id.nis) EditText nisEditText;
    @InjectView(R.id.password) EditText passwordEditText;

    @Inject LoginPresenter loginPresenter;

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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.login_button)
    void onLoginButtonClick() {
        loginPresenter.onLoginButtonClick();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SchoolHubApp.get(getActivity()).component().inject(this);
        loginPresenter.setLoginView(this);
    }

    @Override
    public void showStudentNumberError(String message) {
        nisEditText.requestFocus();
        nisEditText.setError(message);
    }

    @Override
    public void showPasswordError(String message) {
        passwordEditText.requestFocus();
        passwordEditText.setError(message);
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void navigateToMainActivity() {
        controller.navigateToMainActivity();
    }

    @Override
    public String getStudentNumber() {
        return nisEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return null;
    }

    public interface Controller {
        void navigateToMainActivity();
    }
}
