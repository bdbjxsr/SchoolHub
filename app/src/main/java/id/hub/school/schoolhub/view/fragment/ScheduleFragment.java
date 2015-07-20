package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.adapter.SchedulePageAdapter;

public class ScheduleFragment extends BaseFragment {

    @InjectView(R.id.tabs) TabLayout tabs;
    @InjectView(R.id.view_pager) ViewPager viewPager;

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
        View view = inflater.inflate(R.layout.fragment_tab_pager, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager.setAdapter(new SchedulePageAdapter(getFragmentManager(), getActivity()));
        tabs.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.fab)
    void onFABClick() { controller.navigateToCreateSchedule(); }

    public interface Controller {
        void navigateToCreateSchedule();
    }
}
