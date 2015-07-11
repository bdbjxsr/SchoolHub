package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.parse.ParseUser;

import javax.inject.Inject;

import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.model.AccountPreferences;
import id.hub.school.schoolhub.view.EntranceView;

public final class EntranceActivity extends BaseActivity implements EntranceView {

    @Inject AccountPreferences accountPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SchoolHubApp.get(this).component().inject(this);

        if (ParseUser.getCurrentUser() != null) {
            navigateToMainActivity();
        } else {
            navigateToAuthenticateAcivity();
        }
    }


    @Override
    public void navigateToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateToAuthenticateAcivity() {
        startActivity(new Intent(this, AuthenticateActivity.class));
        finish();
    }

    @Override
    public void setupToolbar(Toolbar toolbar) {}
}
