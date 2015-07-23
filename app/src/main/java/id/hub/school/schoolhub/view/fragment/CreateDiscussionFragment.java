package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.DiscussionFormPresenter;
import id.hub.school.schoolhub.view.DiscussionFormView;
import id.hub.school.schoolhub.view.activity.CreateDiscussionActivity;

public class CreateDiscussionFragment extends BaseFragment implements DiscussionFormView {

    public static final String LOADING_TAG = "loading_tag";
    @InjectView(R.id.action_bar) Toolbar toolbar;
    @InjectView(R.id.category_spinner) Spinner categorySpinner;
    @InjectView(R.id.title_text_input_layout) TextInputLayout titleTextInputLayout;
    @InjectView(R.id.question_text_input_layout) TextInputLayout questionTextInputLayout;

    @Inject DiscussionFormPresenter presenter;
    @Inject Tracker tracker;

    private Controller controller;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SchoolHubApp.get(getActivity()).component().inject(this);
        if (!(activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discussion_form, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((CreateDiscussionActivity) getActivity()).setupToolbar(toolbar);
    }

    @Override
    public void showJudulError(String message) {
        titleTextInputLayout.setError(message);
        titleTextInputLayout.setErrorEnabled(true);
        titleTextInputLayout.getEditText().requestFocus();
    }

    @Override
    public void showQuestionError(String message) {
        questionTextInputLayout.setError(message);
        questionTextInputLayout.setErrorEnabled(true);
        questionTextInputLayout.getEditText().requestFocus();
    }

    @Override
    public void hideJudulError() {
        titleTextInputLayout.setErrorEnabled(false);
    }

    @Override
    public void hideQuestionError() {
        questionTextInputLayout.setErrorEnabled(false);
    }

    @OnClick(R.id.button_submit)
    void onSubmitClick() {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Button")
                .setAction("click")
                .setLabel("Submit Question")
                .build());
        presenter.onSubmitClick();
    }

    @Override
    public String getJudul() { return titleTextInputLayout.getEditText().getText().toString(); }

    @Override
    public String getQuestion() {
        return questionTextInputLayout.getEditText().getText().toString();
    }

    @Override
    public String getKategori() { return categorySpinner.getSelectedItem().toString(); }

    @Override
    public File getImage() {
        return null;
    }

    @Override
    public void finish() { controller.setResultAndFinish(); }

    @Override
    public void showProgress() {
        ProgressDialogFragment.show(getString(R.string.message_loading),
                getFragmentManager(), LOADING_TAG);
    }

    @Override
    public void hideProgress() {
        ProgressDialogFragment.dismiss(getFragmentManager(), LOADING_TAG);
    }

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

    public interface Controller{
        void setResultAndFinish();
    }
}
