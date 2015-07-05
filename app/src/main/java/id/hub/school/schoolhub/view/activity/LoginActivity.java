package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import id.hub.school.schoolhub.view.fragment.LoginFragment;

import static id.hub.school.schoolhub.view.fragment.LoginFragment.*;

public final class LoginActivity extends BaseActivity implements Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, new LoginFragment())
                    .commit();
        }
    }


    @Override
    public void navigateToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
