package id.hub.school.schoolhub.view.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.hub.school.schoolhub.view.fragment.CreateCommentFragment;

public class CreateCommentActivity extends BaseActivity implements CreateCommentFragment.Controller {

    public static final String EXTRA_OBJECT_ID = "extra_object_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState == null) {
            CreateCommentFragment fragment = CreateCommentFragment
                    .newInstance(bundle.getString(EXTRA_OBJECT_ID));
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, fragment)
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
    public void finishSubmit() {
        setResult(RESULT_OK);
        finish();
    }
}
