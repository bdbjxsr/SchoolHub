package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

    private static final String LOADING_TAG = "loading_tag";
    @InjectView(R.id.nis_text_input_layout) TextInputLayout nisTextInputLayout;
    @InjectView(R.id.password_text_input_layout) TextInputLayout passwordTextInputLayout;

    @Inject LoginPresenter loginPresenter;

    private Controller controller;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SchoolHubApp.get(activity).component().inject(this);
        if (!(activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
        loginPresenter.setLoginView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nisTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    hideStudentNumberError();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        passwordTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    hidePasswordError();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @OnClick(R.id.login_button)
    void onLoginButtonClick() {
        loginPresenter.onLoginButtonClick();
    }

    @Override
    public void showStudentNumberError(String message) {
        nisTextInputLayout.setErrorEnabled(true);
        nisTextInputLayout.setError(message);
        nisTextInputLayout.getEditText().requestFocus();
    }

    @Override
    public void showPasswordError(String message) {
        passwordTextInputLayout.setErrorEnabled(true);
        passwordTextInputLayout.setError(message);
        passwordTextInputLayout.getEditText().requestFocus();
    }

    @Override
    public void hideStudentNumberError() { nisTextInputLayout.setErrorEnabled(false); }

    @Override
    public void hidePasswordError() { passwordTextInputLayout.setErrorEnabled(false); }

    @Override
    public void showLoadingView() {
        ProgressDialogFragment.show(getString(R.string.message_loading), getFragmentManager(), LOADING_TAG);
    }

    @Override
    public void hideLoadingView() {
        ProgressDialogFragment.dismiss(getFragmentManager(), LOADING_TAG);
    }

    @Override
    public void navigateToMainActivity() {
        controller.navigateToMainActivity();
    }

    @Override
    public String getStudentNumber() {
        return nisTextInputLayout.getEditText().getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordTextInputLayout.getEditText().getText().toString();
    }

    @Override
    public void showProgress() {}

    @Override
    public void hideProgress() {}

    @Override
    public void showRetry() {}

    @Override
    public void hideRetry() {}

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() { return getActivity(); }

    public interface Controller {
        void navigateToMainActivity();
    }
}
