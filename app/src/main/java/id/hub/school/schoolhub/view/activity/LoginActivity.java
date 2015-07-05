package id.hub.school.schoolhub.view.activity;

import android.os.Bundle;
import id.hub.school.schoolhub.view.fragment.LoginFragment;

public final class LoginActivity extends BaseActivity {

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


}
