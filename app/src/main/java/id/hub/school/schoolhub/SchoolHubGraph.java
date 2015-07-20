package id.hub.school.schoolhub;

import id.hub.school.schoolhub.view.activity.AuthenticateActivity;
import id.hub.school.schoolhub.view.activity.CreateCommentActivity;
import id.hub.school.schoolhub.view.activity.CreateDiscussionActivity;
import id.hub.school.schoolhub.view.activity.EntranceActivity;
import id.hub.school.schoolhub.view.activity.LoginActivity;
import id.hub.school.schoolhub.view.activity.MainActivity;
import id.hub.school.schoolhub.view.activity.OpenDiscussionActivity;
import id.hub.school.schoolhub.view.activity.SignupActivity;
import id.hub.school.schoolhub.view.adapter.AutoCompleteParseAdapter;
import id.hub.school.schoolhub.view.fragment.AuthenticateFragment;
import id.hub.school.schoolhub.view.fragment.CreateCommentFragment;
import id.hub.school.schoolhub.view.fragment.CreateDiscussionFragment;
import id.hub.school.schoolhub.view.fragment.DiscussionFragment;
import id.hub.school.schoolhub.view.fragment.LoginFragment;
import id.hub.school.schoolhub.view.fragment.OpenDiscussionFragment;
import id.hub.school.schoolhub.view.fragment.SchedulePageFragment;
import id.hub.school.schoolhub.view.fragment.SignupFragment;

public interface SchoolHubGraph {
    void inject(SchoolHubApp app);

    void inject(EntranceActivity activity);

    void inject(AuthenticateFragment fragment);

    void inject(LoginFragment fragment);

    void inject(SignupFragment fragment);

    void inject(AutoCompleteParseAdapter autoCompleteParseAdapter);

    void inject(DiscussionFragment fragment);

    void inject(CreateDiscussionFragment fragment);

    void inject(MainActivity activity);

    void inject(CreateDiscussionActivity activity);

    void inject(LoginActivity activity);

    void inject(SignupActivity activity);

    void inject(AuthenticateActivity activity);

    void inject(OpenDiscussionFragment fragment);

    void inject(CreateCommentFragment fragment);

    void inject(OpenDiscussionActivity activity);

    void inject(CreateCommentActivity activity);

    void inject(SchedulePageFragment fragment);
}
