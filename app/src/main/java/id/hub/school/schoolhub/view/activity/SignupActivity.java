package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.view.fragment.SignupFragment;
import id.hub.school.schoolhub.view.fragment.SignupFragment.Controller;

public class SignupActivity extends BaseActivity implements Controller {

    @Inject Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SchoolHubApp.get(this).component().inject(this);
        tracker.setScreenName("Sign Up");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new SignupFragment())
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
