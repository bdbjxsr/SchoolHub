package id.hub.school.schoolhub;

import id.hub.school.schoolhub.view.activity.AuthenticateActivity;
import id.hub.school.schoolhub.view.activity.DiscussionFormActivity;
import id.hub.school.schoolhub.view.activity.EntranceActivity;
import id.hub.school.schoolhub.view.activity.LoginActivity;
import id.hub.school.schoolhub.view.activity.MainActivity;
import id.hub.school.schoolhub.view.activity.SignupActivity;
import id.hub.school.schoolhub.view.adapter.AutoCompleteParseAdapter;
import id.hub.school.schoolhub.view.fragment.AuthenticateFragment;
import id.hub.school.schoolhub.view.fragment.CreateCommentFragment;
import id.hub.school.schoolhub.view.fragment.DiscussionFormFragment;
import id.hub.school.schoolhub.view.fragment.DiscussionFragment;
import id.hub.school.schoolhub.view.fragment.LoginFragment;
import id.hub.school.schoolhub.view.fragment.OpenDiscussionFragment;
import id.hub.school.schoolhub.view.fragment.SignupFragment;

public interface SchoolHubGraph {
    void inject(SchoolHubApp app);

    void inject(EntranceActivity activity);

    void inject(AuthenticateFragment fragment);

    void inject(LoginFragment fragment);

    void inject(SignupFragment fragment);

    void inject(AutoCompleteParseAdapter autoCompleteParseAdapter);

    void inject(DiscussionFragment fragment);

    void inject(DiscussionFormFragment fragment);

    void inject(MainActivity activity);

    void inject(DiscussionFormActivity activity);

    void inject(LoginActivity activity);

    void inject(SignupActivity activity);

    void inject(AuthenticateActivity activity);

    void inject(OpenDiscussionFragment fragment);

    void inject(CreateCommentFragment fragment);
}
