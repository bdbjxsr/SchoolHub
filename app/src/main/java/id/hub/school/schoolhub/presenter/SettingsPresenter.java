package id.hub.school.schoolhub.presenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.view.SettingsView;

@Singleton
public class SettingsPresenter implements BasePresenter {

    private SettingsView settingsView;

    @Inject
    public SettingsPresenter() {}

    public void setSettingsView(SettingsView settingsView) { this.settingsView = settingsView; }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    public void onNavHomeClick() {
        settingsView.showHomeFragment();
    }

    public void onNavEventsClick() {
        settingsView.showEventsFragment();
    }

    public void onNavScheduleClick() {
        settingsView.showScheduleFragment();
    }

    public void onNavDiscussionClick() {
        settingsView.showDiscussionRoomFragment();
    }

    public void onNavSignOutClick() {

    }

}
