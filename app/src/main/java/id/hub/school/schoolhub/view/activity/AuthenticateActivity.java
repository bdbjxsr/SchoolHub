package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.view.fragment.AuthenticateFragment;

import static id.hub.school.schoolhub.view.fragment.AuthenticateFragment.*;

public class AuthenticateActivity extends BaseActivity implements Controller {

    @Inject Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SchoolHubApp.get(this).component().inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new AuthenticateFragment())
                    .commit();
        }
    }

    @Override
    public void navigatetoLoginActivity() {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Button")
                .setAction("click")
                .setLabel("Log In")
                .build());
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void navigateToSignupActivity() {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Button")
                .setAction("click")
                .setLabel("Sign Up")
                .build());
        startActivity(new Intent(this, SignupActivity.class));
    }

    @Override
    public void setupToolbar(Toolbar toolbar) {}
}
