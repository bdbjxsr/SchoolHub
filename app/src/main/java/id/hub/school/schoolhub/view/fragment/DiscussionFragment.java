package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;
import id.hub.school.schoolhub.presenter.DiscussionPresenter;
import id.hub.school.schoolhub.view.DiscussionView;
import id.hub.school.schoolhub.view.adapter.DiscussionRoomAdapter;
import id.hub.school.schoolhub.view.adapter.DiscussionRoomAdapter.ClickListener;
import id.hub.school.schoolhub.view.widget.LoadingView;
import id.hub.school.schoolhub.view.widget.decorator.DividerItemDecoration;

public class DiscussionFragment extends BaseFragment implements DiscussionView, ClickListener {

    @InjectView(R.id.fab) FloatingActionButton fab;
    @InjectView(R.id.loading_view) LoadingView loadingView;
    @InjectView(R.id.recyclerview) RecyclerView recyclerView;
    @InjectView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.empty) TextView empty;

    @Inject DiscussionPresenter presenter;
    @Inject Tracker tracker;

    private Controller controller;
    private DiscussionRoomAdapter adapter;

    private LinearLayoutManager layoutManager;
    private boolean loading = false;

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
        adapter = new DiscussionRoomAdapter();
        layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    }

    @Override
    public void onDetach() {
        controller = null;
        adapter = null;
        layoutManager = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discussion, container, false);
        ButterKnife.inject(this, view);

        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadDiscussionRoom();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.reloadDiscussionRoom();
            }
        });

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + 10)) {
                    loading = true;
                    // End has been reached
                    presenter.loadMoreDiscussionRoom(totalItemCount - 1);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
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
    public void showLoading() {
        if (loadingView != null) { loadingView.setVisibility(View.VISIBLE); }
    }

    @Override
    public void hideLoading() {
        if (loadingView != null) { loadingView.setVisibility(View.GONE); }
    }

    @Override
    public void showEmptyView() {
        swipeRefreshLayout.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        empty.setVisibility(View.GONE);
    }

    @Override
    public void showDiscussionRoom(List<RuangDiskusiObject> list) {
        if (list.size() >= 10) { list.add(null); }
        adapter.setList(list);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void navigateToCreateDiscussion() {
        controller.navigateToCreateDiscussion();
    }

    @Override
    public void reloadDiscussionRoom(List<RuangDiskusiObject> list) {
        if (list.size() >= 10) { list.add(null); }
        adapter.setList(list);
        adapter.notifyDataSetChanged();
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
    public void hideRefreshLoading() { swipeRefreshLayout.setRefreshing(false); }

    @Override
    public void addMoreList(List<RuangDiskusiObject> list) {
        loading = false;
        if (list.size() >= 10) { list.add(null); }
        adapter.addMoreList(list);
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
