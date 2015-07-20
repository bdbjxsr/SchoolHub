package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.presenter.DiscussionPresenter;
import id.hub.school.schoolhub.view.DiscussionView;
import id.hub.school.schoolhub.view.adapter.DiscussionRoomAdapter;
import id.hub.school.schoolhub.view.adapter.DiscussionRoomAdapter.ClickListener;
import id.hub.school.schoolhub.view.widget.LoadingView;
import timber.log.Timber;

public class DiscussionFragment extends BaseFragment implements DiscussionView, ClickListener {

    @InjectView(R.id.fab) FloatingActionButton fab;
    @InjectView(R.id.loading_view) LoadingView loadingView;
    @InjectView(R.id.recyclerview) RecyclerView recyclerView;

    @Inject DiscussionPresenter presenter;
    @Inject Tracker tracker;

    private Controller controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SchoolHubApp.get(activity).component().inject(this);
        if (! (activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
        presenter.setDiscussionView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discussion, container, false);
        ButterKnife.inject(this, view);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadDiscussionRoom();

    }

    @OnClick(R.id.fab)
    void onFABClick() {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("FAB")
                .setAction("click")
                .setLabel("Create New Discussion Room")
                .build());
        presenter.onFABClick();
    }


    @Override
    public void showLoading() { loadingView.setVisibility(View.VISIBLE); }

    @Override
    public void hideLoading() { loadingView.setVisibility(View.GONE); }

    @Override
    public void showDiscussionRoom(List<RuangDiskusiObject> list) {
        DiscussionRoomAdapter adapter = new DiscussionRoomAdapter(list);
        adapter.setClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void navigateToCreateDiscussion() {
        controller.navigateToCreateDiscussion();
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
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onItemClickListener(View view, int position) {
        DiscussionRoomAdapter adapter = (DiscussionRoomAdapter) recyclerView.getAdapter();
        String objectId = adapter.getItem(position).getObjectId();
        String question = adapter.getItem(position).getQuestion();
        String type = adapter.getItem(position).getKategori();

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Row Item")
                .setAction("click")
                .setLabel(type + "/" + objectId)
                .build());

        controller.navigateToDiscussionRoom(objectId, question);
    }

    public interface Controller {
        void navigateToCreateDiscussion();

        void navigateToDiscussionRoom(String id, String question);
    }
}
