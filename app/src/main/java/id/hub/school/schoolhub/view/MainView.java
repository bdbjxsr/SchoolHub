package id.hub.school.schoolhub.view;

import android.support.v4.app.Fragment;

public interface MainView extends LoadDataView {
    void showLoading();

    void hideLoading();

    void replaceWithHomeFragment();

    void replaceWithScheduleFragment();

    void replaceWithDiscussionFragment();

    void replaceFragment(Fragment fragment);
}
