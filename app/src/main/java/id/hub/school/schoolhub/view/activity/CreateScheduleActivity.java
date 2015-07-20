package id.hub.school.schoolhub.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.fragment.CreateScheduleFragment;
import id.hub.school.schoolhub.view.fragment.CreateScheduleFragment.Controller;

public class CreateScheduleActivity extends BaseActivity implements Controller {

    public static final String EXTRA_POSITION = "extra_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content,
                            CreateScheduleFragment.newInstance(bundle.getInt(EXTRA_POSITION)))
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finishCreateSchedule(int day) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_POSITION, day);
        setResult(RESULT_OK, intent);
        finish();
    }
}
