package id.hub.school.schoolhub.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import id.hub.school.schoolhub.utils.ConvertUtil;
import id.hub.school.schoolhub.view.fragment.SchedulePageFragment;

public class SchedulePageAdapter extends SmartFragmentStatePagerAdapter {
    private final int PAGE_COUNT = 5;

    public SchedulePageAdapter(FragmentManager fm) { super(fm); }

    @Override
    public SchedulePageFragment getItem(int position) {
        return SchedulePageFragment.newInstance(position);
    }

    @Override
    public int getCount() { return PAGE_COUNT; }

    @Override
    public CharSequence getPageTitle(int position) {
        return ConvertUtil.convertToDayName(position);
    }

}
