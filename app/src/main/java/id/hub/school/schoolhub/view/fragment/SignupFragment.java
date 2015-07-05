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
import id.hub.school.schoolhub.presenter.SignUpPresenter;
import id.hub.school.schoolhub.view.SignupView;

public class SignupFragment extends BaseFragment implements SignupView {

    @InjectView(R.id.nis) EditText nisEditText;
    @InjectView(R.id.fullname) EditText fullnameEditText;
    @InjectView(R.id.school_name) EditText schoolNameEditText;
    @InjectView(R.id.password) EditText passwordEditText;

    private Controller controller;

    @Inject SignUpPresenter signUpPresenter;

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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SchoolHubApp.get(getActivity()).component().inject(this);
        signUpPresenter.setSignupView(this);
    }

    @Override
    public void showSchoolNameError(String message) {
        schoolNameEditText.requestFocus();
        schoolNameEditText.setError(message);
    }

    @Override
    public void showStudentNumberError(String message) {
        nisEditText.requestFocus();
        nisEditText.setError(message);
    }

    @Override
    public void showFullnameError(String message) {
        fullnameEditText.requestFocus();
        fullnameEditText.setError(message);
    }

    @Override
    public void showPasswordError(String message) {
        passwordEditText.requestFocus();
        passwordEditText.setError(message);
    }

    @OnClick(R.id.signup_button)
    void onSignUpButtonClick() {
        signUpPresenter.onSignUpClick();
    }

    @Override
    public void toogleShowHidePassword() {

    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public String getSchoolName() { return schoolNameEditText.getText().toString(); }

    @Override
    public String getStudentNumber() { return nisEditText.getText().toString(); }

    @Override
    public String getFullName() { return fullnameEditText.getText().toString(); }

    @Override
    public String getPassword() { return passwordEditText.getText().toString(); }

    @Override
    public void navigateToMainActivity() { controller.navigateToMainActivity(); }

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
