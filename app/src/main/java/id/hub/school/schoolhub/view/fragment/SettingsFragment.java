package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.RecycleItemClickListener;
import id.hub.school.schoolhub.presenter.RecycleItemClickListener.OnItemClickListener;
import id.hub.school.schoolhub.presenter.SettingsPresenter;
import id.hub.school.schoolhub.view.SettingsView;
import id.hub.school.schoolhub.view.adapter.RecycleViewAdapter;

import static android.support.v7.widget.RecyclerView.*;

public class SettingsFragment extends BaseFragment implements SettingsView, OnItemClickListener {

    String TITLES[] = {"Home","Events","Schedule","Discussion Room","Sign Out"};

    @InjectView(R.id.recyclerview) RecyclerView recyclerView;

    @Inject SettingsPresenter settingsPresenter;

    private Controller controller;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (! (activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SchoolHubApp.get(getActivity()).component().inject(this);
        settingsPresenter.setSettingsView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Adapter adapter = new RecycleViewAdapter(TITLES, "Siswa Teladan", null, "SMA NEGERI 14 JAKARTA");
        recyclerView.setAdapter(adapter);

        LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new RecycleItemClickListener(getActivity(), this));
    }

    @Override
    public void onItemClick(View childView, int position) {
        switch (position) {
            case 1:
                settingsPresenter.onNavHomeClick();
                break;
            case 2:
                settingsPresenter.onNavEventsClick();
                break;
            case 3:
                settingsPresenter.onNavScheduleClick();
                break;
            case 4:
                settingsPresenter.onNavDiscussionClick();
                break;
            case 5:
                settingsPresenter.onNavSignOutClick();
                break;
        }
    }

    @Override
    public void showHomeFragment() {
        controller.replaceWithHomeFragment();
    }

    @Override
    public void showEventsFragment() {}

    @Override
    public void showScheduleFragment() {
        controller.replaceWithScheduleFragment();
    }

    @Override
    public void showDiscussionRoomFragment() {
        controller.replaceWithDiscussionFragment();
    }

    public interface Controller {
        void replaceWithHomeFragment();

        void replaceWithScheduleFragment();

        void replaceWithDiscussionFragment();

    }
}
