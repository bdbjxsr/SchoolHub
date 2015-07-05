package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;

import id.hub.school.schoolhub.view.fragment.SignupFragment;
import id.hub.school.schoolhub.view.fragment.SignupFragment.Controller;

public class SignupActivity extends BaseActivity implements Controller {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new SignupFragment())
                    .commit();
        }
    }

    @Override
    public void navigateToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}