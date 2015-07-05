package id.hub.school.schoolhub;

import id.hub.school.schoolhub.view.activity.EntranceActivity;
import id.hub.school.schoolhub.view.fragment.AuthenticateFragment;

public interface SchoolHubGraph {
    void inject(SchoolHubApp app);

    void inject(EntranceActivity activity);

    void inject(AuthenticateFragment fragment);
}
