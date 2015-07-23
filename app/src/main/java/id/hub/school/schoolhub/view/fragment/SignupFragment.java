package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.SignUpPresenter;
import id.hub.school.schoolhub.view.SignupView;
import id.hub.school.schoolhub.view.adapter.AutoCompleteDelegate;

public class SignupFragment extends BaseFragment implements SignupView {

    public static final String LOADING_TAG = "loadingTag";
    @InjectView(R.id.nis_text_input_layout) TextInputLayout nisTextInputLayout;
    @InjectView(R.id.fullname_text_input_layout) TextInputLayout fullnameTextInputLayout;
    @InjectView(R.id.password_text_input_layout) TextInputLayout passwordTextInputLayout;
    @InjectView(R.id.auto_text_input_layout) TextInputLayout autoTextInputLayout;
    @InjectView(R.id.show_password) CheckBox showPasswordCheckbox;

    private Controller controller;

    @Inject SignUpPresenter signUpPresenter;

    private AutoCompleteDelegate autoCompleteDelegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        autoCompleteDelegate = new AutoCompleteDelegate(getActivity());
    }

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
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SchoolHubApp.get(getActivity()).component().inject(this);
        signUpPresenter.setSignupView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)
                autoTextInputLayout.getEditText();
        autoCompleteDelegate.setOnItemClick(autoCompleteTextView);

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

        fullnameTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    hideFullNameError();
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

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)
                autoTextInputLayout.getEditText();
        autoCompleteDelegate.onRestoreState(savedInstanceState, autoCompleteTextView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        autoCompleteDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void showSchoolNameError(String message) {
        autoTextInputLayout.setErrorEnabled(true);
        autoTextInputLayout.setError(message);
        autoTextInputLayout.getEditText().requestFocus();
    }

    @Override
    public void showStudentNumberError(String message) {
        nisTextInputLayout.setErrorEnabled(true);
        nisTextInputLayout.setError(message);
        nisTextInputLayout.getEditText().requestFocus();
    }

    @Override
    public void showFullnameError(String message) {
        fullnameTextInputLayout.setErrorEnabled(true);
        fullnameTextInputLayout.setError(message);
        fullnameTextInputLayout.getEditText().requestFocus();
    }

    @Override
    public void showPasswordError(String message) {
        passwordTextInputLayout.setErrorEnabled(true);
        passwordTextInputLayout.setError(message);
        passwordTextInputLayout.getEditText().requestFocus();
    }

    @Override
    public void hideSchoolNameError() { autoTextInputLayout.setErrorEnabled(false); }

    @Override
    public void hideStudentNumberError() { nisTextInputLayout.setErrorEnabled(false); }

    @Override
    public void hideFullNameError() { fullnameTextInputLayout.setErrorEnabled(false); }

    @Override
    public void hidePasswordError() { passwordTextInputLayout.setErrorEnabled(false); }

    @OnClick(R.id.signup_button)
    void onSignUpButtonClick() {
        signUpPresenter.onSignUpClick();
    }

    @OnCheckedChanged(R.id.show_password)
    void onShowPasswordCheckedChange(CompoundButton view, boolean state) {
        signUpPresenter.onShowPasswordCheckedChange(view);
    }

    @Override
    public void toogleShowHidePassword(boolean checked) {
        EditText passwordEditText = passwordTextInputLayout.getEditText();
        if (checked) {
            showPasswordCheckbox.setText(getActivity().getString(R.string.label_hide_button));
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            showPasswordCheckbox.setText(getActivity().getString(R.string.label_show_button));
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        passwordEditText.setSelection(passwordEditText.getText().length());
    }

    @Override
    public void showLoadingView() {
        ProgressDialogFragment.show(getString(R.string.message_loading),
                getFragmentManager(), LOADING_TAG);
    }

    @Override
    public void hideLoadingView() {
        ProgressDialogFragment.dismiss(getFragmentManager(), LOADING_TAG);
    }

    @Override
    public String getSchoolName() {
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)
                autoTextInputLayout.getEditText();
        return autoCompleteTextView.getText().toString();
    }

    @Override
    public String getStudentNumber() { return nisTextInputLayout.getEditText().getText().toString(); }

    @Override
    public String getFullName() { return fullnameTextInputLayout.getEditText().getText().toString(); }

    @Override
    public String getPassword() { return passwordTextInputLayout.getEditText().getText().toString(); }

    @Override
    public void navigateToMainActivity() { controller.navigateToMainActivity(); }

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
    public Context getContext() {
        return null;
    }

    public interface Controller {
        void navigateToMainActivity();
    }
}
