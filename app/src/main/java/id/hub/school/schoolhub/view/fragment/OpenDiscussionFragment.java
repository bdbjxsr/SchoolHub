package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.presenter.OpenDiscussionPresenter;
import id.hub.school.schoolhub.view.OpenDiscussionView;
import id.hub.school.schoolhub.view.activity.OpenDiscussionActivity;
import id.hub.school.schoolhub.view.adapter.CommentAdapter;
import id.hub.school.schoolhub.view.widget.decorator.DividerItemDecoration;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class OpenDiscussionFragment extends BaseFragment implements OpenDiscussionView,
        OnOffsetChangedListener {

    public static final String ARG_OBJECT_ID = "args_object_id";

    @InjectView(R.id.action_bar) Toolbar toolbar;
    @InjectView(R.id.loading_view) ProgressBar loadingView;
    @InjectView(R.id.recyclerview) RecyclerView recyclerView;
    @InjectView(R.id.empty) TextView empty;
    @InjectView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.app_bar_layout) AppBarLayout appBarLayout;

    @Inject OpenDiscussionPresenter presenter;
    @Inject Tracker tracker;

    private Controller controller;
    @Inject CommentAdapter adapter;

    private LinearLayoutManager layoutManager;
    private boolean loading = false;


    public static OpenDiscussionFragment newInstance(String id) {
        Bundle args = new Bundle();
        args.putString(ARG_OBJECT_ID, id);

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
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_open_discussion, container, false);
        ButterKnife.inject(this, view);

        ((OpenDiscussionActivity) getActivity()).setupToolbar(toolbar);

        appBarLayout.addOnOffsetChangedListener(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadComment();
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getDiscussionFromLocal(getArguments().getString(ARG_OBJECT_ID));

    }

    public void reloadComment() {
        presenter.refreshComment(getArguments().getString(ARG_OBJECT_ID));
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
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyTextView() {
    }

    @Override
    public void hideEmptyTextView() {
    }

    @Override
    public void navigateToCreateComment(String objectId) {
        controller.navigateToCreateComment(objectId);
    }

    @Override
    public String getDiscussionObectId() { return getArguments().getString(ARG_OBJECT_ID, ""); }

    @Override
    public void hideRefresh() { swipeRefreshLayout.setRefreshing(false); }

    @Override
    public void addMoreList(List<OpenDiscussionObject> list) {}

    @Override
    public void reloadList(List<OpenDiscussionObject> list) {
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showQuestion(RuangDiskusiObject object) {
        swipeRefreshLayout.setVisibility(VISIBLE);

        adapter.setRuangDiskusiObject(object);
        adapter.notifyDataSetChanged();

        presenter.loadComment(getArguments().getString(ARG_OBJECT_ID));
    }

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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        swipeRefreshLayout.setEnabled(i == 0);
    }

    public interface Controller {
        void navigateToCreateComment(String objectId);
    }
}
