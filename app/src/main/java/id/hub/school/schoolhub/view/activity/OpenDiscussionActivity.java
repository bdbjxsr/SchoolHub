package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.fragment.OpenDiscussionFragment;

import static id.hub.school.schoolhub.view.fragment.OpenDiscussionFragment.*;

public class OpenDiscussionActivity extends BaseActivity implements Controller {

    public static final String EXTRA_OBJECT_ID = "extra_object_id";
    public static final String EXTRA_QUESTION = "extra_question";
    public static final int CREATE_COMMENT_REQUEST_CODE = 10057;

    @Override
    public void setupToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            throw new IllegalStateException("Action bar must not be null.");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState == null) {
            OpenDiscussionFragment fragment =
                    newInstance(bundle.getString(EXTRA_OBJECT_ID, ""),
                            bundle.getString(EXTRA_QUESTION, ""));
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, fragment)
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
                // TODO fajar: tell fragment to reload list comment
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
