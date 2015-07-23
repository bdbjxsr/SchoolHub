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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.CreateCommentPresenter;
import id.hub.school.schoolhub.view.CreateCommentView;
import id.hub.school.schoolhub.view.activity.CreateCommentActivity;

public class CreateCommentFragment extends BaseFragment implements CreateCommentView {

    public static final String ARGS_OBJECT_ID = "args_object_id";
    public static final String LOADING_TAG = "loading_tag";

    @InjectView(R.id.answer_text_input_layout) TextInputLayout answerTextInputLayout;
    @InjectView(R.id.action_bar) Toolbar toolbar;

    @Inject CreateCommentPresenter presenter;
    @Inject Tracker tracker;

    private Controller controller;

    public CreateCommentFragment() {}

    public static CreateCommentFragment newInstance(String objectId) {
        Bundle args = new Bundle();
        args.putString(ARGS_OBJECT_ID, objectId);

        CreateCommentFragment fragment = new CreateCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SchoolHubApp.get(getActivity()).component().inject(this);
        if (! (activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }

        controller = (Controller) activity;
        presenter.setView(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment_form, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((CreateCommentActivity) getActivity()).setupToolbar(toolbar);
    }

    @OnClick(R.id.button_submit)
    void onSubmitClick() {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Button")
                .setAction("click")
                .setLabel("Submit Answer")
                .build());
        presenter.onSubmitClick();
    }

    @Override
    public void showQuestionError(String message) {
        answerTextInputLayout.setError(message);
        answerTextInputLayout.setErrorEnabled(true);
        answerTextInputLayout.getEditText().requestFocus();
    }

    @Override
    public void hideQuestionError() { answerTextInputLayout.setErrorEnabled(false); }

    @Override
    public String getObjectId() { return getArguments().getString(ARGS_OBJECT_ID); }

    @Override
    public String getAnswerText() {
        return answerTextInputLayout.getEditText().getText().toString();
    }

    @Override
    public void finish() { controller.finishSubmit(); }

    @Override
    public void showProgress() {
        ProgressDialogFragment.show(getString(R.string.message_loading), getFragmentManager(),
                LOADING_TAG);
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
    public Context getContext() { return getActivity(); }

    public interface Controller {
        void finishSubmit();
    }
}
