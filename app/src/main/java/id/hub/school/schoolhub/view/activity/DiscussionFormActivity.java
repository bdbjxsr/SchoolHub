package id.hub.school.schoolhub.view.activity;

import android.os.Bundle;

import id.hub.school.schoolhub.view.fragment.DiscussionFormFragment;

public class DiscussionFormActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new DiscussionFormFragment())
                    .commit();
        }
    }
}
