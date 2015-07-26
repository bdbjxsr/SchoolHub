package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.view.fragment.OpenDiscussionFragment;

import static id.hub.school.schoolhub.view.fragment.OpenDiscussionFragment.Controller;
import static id.hub.school.schoolhub.view.fragment.OpenDiscussionFragment.newInstance;

public class OpenDiscussionActivity extends BaseActivity implements Controller {

    public static final String EXTRA_OBJECT_ID = "extra_object_id";
    public static final int CREATE_COMMENT_REQUEST_CODE = 10057;
    public static final String TAG_DISCUSSION = "tag_discussion";

    @Inject Tracker tracker;

    @Override
    public void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            throw new IllegalStateException("Action bar must not be null.");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SchoolHubApp.get(this).component().inject(this);

        tracker.setScreenName("Open Discussion");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState == null) {
            OpenDiscussionFragment fragment =
                    OpenDiscussionFragment.newInstance(bundle.getString(EXTRA_OBJECT_ID, ""));
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, fragment, TAG_DISCUSSION)
                    .commit();
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_COMMENT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                OpenDiscussionFragment fragment = (OpenDiscussionFragment) getSupportFragmentManager()
                        .findFragmentByTag(TAG_DISCUSSION);
                fragment.reloadComment();
            }
        }
    }

    @Override
    public void navigateToCreateComment(String objectId) {
        Intent intent = new Intent(this, CreateCommentActivity.class);
        intent.putExtra(CreateCommentActivity.EXTRA_OBJECT_ID, objectId);
        startActivityForResult(intent, CREATE_COMMENT_REQUEST_CODE);
    }
}
