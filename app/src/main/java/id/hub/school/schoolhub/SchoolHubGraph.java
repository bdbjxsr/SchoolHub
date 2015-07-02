package id.hub.school.schoolhub;

import id.hub.school.schoolhub.view.activity.EntranceActivity;

public interface SchoolHubGraph {
    void inject(SchoolHubApp app);

    void inject(EntranceActivity activity);
}
