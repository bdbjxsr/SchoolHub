package id.hub.school.schoolhub;

import id.hub.school.schoolhub.view.activity.EntranceActivity;
import id.hub.school.schoolhub.view.fragment.AuthenticateFragment;
import id.hub.school.schoolhub.view.fragment.LoginFragment;
import id.hub.school.schoolhub.view.fragment.SettingsFragment;
import id.hub.school.schoolhub.view.fragment.SignupFragment;

public interface SchoolHubGraph {
    void inject(SchoolHubApp app);

    void inject(EntranceActivity activity);

    void inject(AuthenticateFragment fragment);

    void inject(LoginFragment fragment);

    void inject(SignupFragment fragment);

    void inject(SettingsFragment fragment);
}
