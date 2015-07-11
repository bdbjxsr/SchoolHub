package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import id.hub.school.schoolhub.view.fragment.AuthenticateFragment;

import static id.hub.school.schoolhub.view.fragment.AuthenticateFragment.*;

public class AuthenticateActivity extends BaseActivity implements Controller {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new AuthenticateFragment())
                    .commit();
        }
    }

    @Override
    public void navigatetoLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void navigateToSignupActivity() {
        startActivity(new Intent(this, SignupActivity.class));
    }

    @Override
    public void setupToolbar(Toolbar toolbar) {}
}
