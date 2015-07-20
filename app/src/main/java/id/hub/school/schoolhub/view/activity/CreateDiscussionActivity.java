package id.hub.school.schoolhub.view.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.view.fragment.CreateDiscussionFragment;
import id.hub.school.schoolhub.view.fragment.CreateDiscussionFragment.Controller;

public class CreateDiscussionActivity extends BaseActivity implements Controller {

    @Inject Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SchoolHubApp.get(this).component().inject(this);
        tracker.setScreenName("Create Discussion Room");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new CreateDiscussionFragment())
                    .commit();
        }
    }

    @Override
    public void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            throw new IllegalStateException("Action bar must not be null.");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(getString(R.string.label_actionbar_title_create_discussion_room));
        actionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(this,
                R.drawable.ic_clear_white_24dp));
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void setResultAndFinish() {
        setResult(RESULT_OK);
        finish();
    }
}
