package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;

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

        if (accountPreferences.accountID() != -1) {
            navigateToMainActivity();
        } else {
            navigateToLoginActivity();
        }
    }


    @Override
    public void navigateToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateToLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
