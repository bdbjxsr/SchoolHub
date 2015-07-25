package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.lang.reflect.Field;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.SchedulePresenter;
import id.hub.school.schoolhub.view.adapter.SchedulePageAdapter;
import timber.log.Timber;

public class ScheduleFragment extends BaseFragment {


    public static final String ARG_DAY = "arg_day";

    @InjectView(R.id.tabs) TabLayout tabs;
    @InjectView(R.id.view_pager) ViewPager viewPager;

    @Inject Tracker tracker;

    private Controller controller;
    private SchedulePageAdapter adapter;

    public static ScheduleFragment newInstance(int day) {
        Bundle args = new Bundle();
        args.putInt(ARG_DAY, day);

        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SchoolHubApp.get(activity).component().inject(this);
        if (! (activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
    }

    @Override
    public void onDetach() {
        controller = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_pager, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SchedulePageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(getArguments().getInt(ARG_DAY));
        tabs.setupWithViewPager(viewPager);
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
                .setLabel("Create New Schedule")
                .build());
        controller.navigateToCreateSchedule(viewPager.getCurrentItem());
    }

    public interface Controller {
        void navigateToCreateSchedule(int position);
    }
}
