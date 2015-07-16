package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.model.data.OpenDiscussionObject;
import id.hub.school.schoolhub.presenter.OpenDiscussionPresenter;
import id.hub.school.schoolhub.view.OpenDiscussionView;
import id.hub.school.schoolhub.view.activity.OpenDiscussionActivity;
import id.hub.school.schoolhub.view.adapter.CommentAdapter;
import id.hub.school.schoolhub.view.widget.LoadingView;

import static android.view.View.*;
import static android.view.View.GONE;

public class OpenDiscussionFragment extends BaseFragment implements OpenDiscussionView {

    public static final String ARGS_OBJECT_ID = "args_object_id";
    public static final String ARGS_QUESTION = "arg_question";

    @InjectView(R.id.action_bar) Toolbar toolbar;
    @InjectView(R.id.question_header) TextView questionHeaderTextView;
    @InjectView(R.id.loading_view) LoadingView loadingView;
    @InjectView(R.id.recyclerview) RecyclerView recyclerView;
    @InjectView(R.id.empty) TextView empty;

    @Inject OpenDiscussionPresenter presenter;
    @Inject Tracker tracker;

    private Controller controller;

    public static OpenDiscussionFragment newInstance(String id, String question) {
        Bundle args = new Bundle();
        args.putString(ARGS_OBJECT_ID, id);
        args.putString(ARGS_QUESTION, question);

        OpenDiscussionFragment fragment = new OpenDiscussionFragment();
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
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_discussion, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((OpenDiscussionActivity) getActivity()).setupToolbar(toolbar);

        questionHeaderTextView.setText(getArguments().getString(ARGS_QUESTION));

        presenter.loadComment(getArguments().getString(ARGS_OBJECT_ID));
    }

    @OnClick(R.id.fab)
    void onFABClick () {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("FAB")
                .setAction("click")
                .setLabel("Create New Comment")
                .build());
        presenter.onFABClick();
    }

    @Override
    public void showComment(List<OpenDiscussionObject> list) {
        CommentAdapter adapter = new CommentAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setVisibility(VISIBLE);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showEmptyTextView() { empty.setVisibility(VISIBLE); }

    @Override
    public void hideEmptyTextView() { empty.setVisibility(GONE); }

    @Override
    public void navigateToCreateComment(String objectId) {
        controller.navigateToCreateComment(objectId);
    }

    @Override
    public String getDiscussionObectId() { return getArguments().getString(ARGS_OBJECT_ID, ""); }

    @Override
    public void showProgress() { loadingView.setVisibility(VISIBLE); }

    @Override
    public void hideProgress() { loadingView.setVisibility(GONE); }

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
        return getActivity();
    }

    public interface Controller {
        void navigateToCreateComment(String objectId);
    }
}
