package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.view.fragment.LoginFragment;

import static id.hub.school.schoolhub.view.fragment.LoginFragment.*;

public final class LoginActivity extends BaseActivity implements Controller {

    @Inject Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SchoolHubApp.get(this).component().inject(this);
        tracker.setScreenName("Log In");

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, new LoginFragment())
                    .commit();
        }
    }


    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void setupToolbar(Toolbar toolbar) {}
}
