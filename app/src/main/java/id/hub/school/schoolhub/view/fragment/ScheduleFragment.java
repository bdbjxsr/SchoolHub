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

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.adapter.SchedulePageAdapter;
import timber.log.Timber;

public class ScheduleFragment extends BaseFragment {


    public static final String ARG_DAY = "arg_day";
    @InjectView(R.id.tabs) TabLayout tabs;
    @InjectView(R.id.view_pager) ViewPager viewPager;

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
        if (! (activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_pager, container, false);
        ButterKnife.inject(this, view);
        adapter = new SchedulePageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(getArguments().getInt(ARG_DAY));
        tabs.setupWithViewPager(viewPager);
        return view;
    }

    @OnClick(R.id.fab)
    void onFABClick() { controller.navigateToCreateSchedule(viewPager.getCurrentItem()); }

    public interface Controller {
        void navigateToCreateSchedule(int position);
    }
}
