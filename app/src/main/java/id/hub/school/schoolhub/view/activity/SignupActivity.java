package id.hub.school.schoolhub.view.activity;

import android.os.Bundle;

import id.hub.school.schoolhub.view.fragment.SignupFragment;

public class SignupActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new SignupFragment())
                    .commit();
        }
    }
}
