package id.hub.school.schoolhub.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import id.hub.school.schoolhub.view.adapter.SmartFragmentStatePagerAdapter;

public class AuthenticatePageAdapter extends SmartFragmentStatePagerAdapter {

    public static final int NUM_PAGES = 2;

    public AuthenticatePageAdapter(FragmentManager fragmentManager) { super(fragmentManager); }

    @Override
    public Fragment getItem(int position) {
        return AuthenticatePageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
