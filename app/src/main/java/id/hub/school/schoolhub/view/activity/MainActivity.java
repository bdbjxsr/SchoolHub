package id.hub.school.schoolhub.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.model.data.ScheduleObject;
import id.hub.school.schoolhub.utils.TimeUtil;
import id.hub.school.schoolhub.view.MainView;
import id.hub.school.schoolhub.view.fragment.DiscussionFragment;
import id.hub.school.schoolhub.view.fragment.HomeFragment;
import id.hub.school.schoolhub.view.fragment.ProgressDialogFragment;
import id.hub.school.schoolhub.view.fragment.ScheduleFragment;
import id.hub.school.schoolhub.view.fragment.SchedulePageFragment;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

public final class MainActivity extends BaseActivity implements MainView,
        DiscussionFragment.Controller, ScheduleFragment.Controller, SchedulePageFragment.Controller {

    public static final String TAG_LOADING = "loading";
    public static final int REQUEST_CODE_DISCUSSION_FORM = 100;
    public static final int REQUEST_CODE_CREATE_SCHEDULE = 200;
    public static final String EXTRA_NOTIFICATION = "extra_notification";
    public static final String STATE_SELECTED_NAV_ITEM = "state_selected_nav_item";

    @Inject Tracker tracker;

    @InjectView(R.id.action_bar) Toolbar toolbar;
    @InjectView(R.id.navigation) NavigationView navigationView;
    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @InjectView(R.id.circleview) CircleImageView circleImageView;
    @InjectView(R.id.name) TextView nameTextView;
    @InjectView(R.id.schoolname) TextView schoolNameTextView;

    private ActionBarDrawerToggle drawerToggle;
    private int currentSelectedPosition;
    private DiscussionFragment discussionFragment;
    private ScheduleFragment scheduleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SchoolHubApp.get(this).component().inject(this);
        setContentView(R.layout.activity_main_with_navigation_view);
        ButterKnife.inject(this);

        setupToolbar(toolbar);
        setupNavigationDrawer();

        if (savedInstanceState != null) {
            currentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_NAV_ITEM);
        } else {
            currentSelectedPosition = 0;
        }

        if (currentSelectedPosition == 0 ) {
            replaceWithScheduleFragment();
        } else {
            replaceWithDiscussionFragment();
        }
    }

    private Fragment initDiscussionFragment() {
        discussionFragment = new DiscussionFragment();
        return discussionFragment;
    }

    private Fragment initScheduleFragment() {
        scheduleFragment = ScheduleFragment.newInstance(TimeUtil.getCurrentDayOnWeek());
        return scheduleFragment;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_NAV_ITEM, 0);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_NAV_ITEM, currentSelectedPosition);
    }

    private void setupNavigationDrawer() {
        Picasso.with(this).load(R.drawable.male).noFade().into(circleImageView);
        nameTextView.setText(ParseUser.getCurrentUser().getString("fullName"));
        schoolNameTextView.setText(ParseUser.getCurrentUser().getString("schoolName"));
        navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navSchedule:
                        replaceWithScheduleFragment();
                        currentSelectedPosition = 0;
                        break;
                    case R.id.navDiscussion:
                        replaceWithDiscussionFragment();
                        currentSelectedPosition = 1;
                        break;
                    case R.id.navSignOut:
                        signOutApplication();
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);

        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void signOutApplication() {
        showLoading();
        ParseUser.logOutInBackground(new LogOutCallback() {
            @Override
            public void done(ParseException e) {
                hideLoading();
                if (e == null) {
                    Intent intent = new Intent(MainActivity.this, EntranceActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    showError(e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DISCUSSION_FORM) {
            if (resultCode == RESULT_OK) {
                replaceFragment(new DiscussionFragment());
            }
        }

        if (requestCode == REQUEST_CODE_CREATE_SCHEDULE) {
            if (resultCode == RESULT_OK) {
                int day  = data.getIntExtra(CreateScheduleActivity.EXTRA_POSITION, 0);
                replaceFragment(ScheduleFragment.newInstance(day));
            }
        }
    }

    @Override
    public void replaceWithHomeFragment() {
        tracker.setScreenName("Home");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        toolbar.setTitle(getString(R.string.app_name));
        replaceFragment(new HomeFragment());
    }

    @Override
    public void replaceWithScheduleFragment() {
        tracker.setScreenName("Schedule");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        if (getSupportActionBar() != null ) {
            getSupportActionBar().setTitle(R.string.title_schedule_screen);
        }

        initScheduleFragment();
        replaceFragment(scheduleFragment);
    }

    @Override
    public void replaceWithDiscussionFragment() {
        tracker.setScreenName("Discussion Room");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        if (getSupportActionBar() != null ) {
            getSupportActionBar().setTitle(R.string.title_discussion_screen);
        }

        initDiscussionFragment();
        replaceFragment(discussionFragment);
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        drawerLayout.closeDrawers();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment).commit();
    }

    @Override
    public void showLoading() {
        ProgressDialogFragment.show(getString(R.string.message_loading),
                getSupportFragmentManager(), TAG_LOADING);
    }

    @Override
    public void hideLoading() {
        ProgressDialogFragment.dismiss(getSupportFragmentManager(), TAG_LOADING);
    }

    @Override
    public void showProgress() {}

    @Override
    public void hideProgress() {}

    @Override
    public void showRetry() {}

    @Override
    public void hideRetry() {}

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() { return this; }

    @Override
    public void navigateToCreateDiscussion() {
        startActivityForResult(new Intent(this, CreateDiscussionActivity.class),
                REQUEST_CODE_DISCUSSION_FORM);
    }

    @Override
    public void navigateToDiscussionRoom(String id, String question) {
        Intent intent = new Intent(this, OpenDiscussionActivity.class);
        intent.putExtra(OpenDiscussionActivity.EXTRA_OBJECT_ID, id);
        intent.putExtra(OpenDiscussionActivity.EXTRA_QUESTION, question);
        startActivity(intent);
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
    public void navigateToCreateSchedule(int position) {
        Intent intent= new Intent(this, CreateScheduleActivity.class);
        intent.putExtra(CreateScheduleActivity.EXTRA_POSITION, position);
        startActivityForResult(intent, REQUEST_CODE_CREATE_SCHEDULE);
    }

    @Override
    public void navigateToEditSchedule(ScheduleObject object, int position) {
        Intent intent= new Intent(this, CreateScheduleActivity.class);
        intent.putExtra(CreateScheduleActivity.EXTRA_POSITION, position);
        intent.putExtra(CreateScheduleActivity.EXTRA_OBJECT_ID, object.getUUID());
        startActivityForResult(intent, REQUEST_CODE_CREATE_SCHEDULE);
    }
}
