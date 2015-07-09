package id.hub.school.schoolhub.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.MainView;
import id.hub.school.schoolhub.view.fragment.DiscussionFragment;
import id.hub.school.schoolhub.view.fragment.HomeFragment;
import id.hub.school.schoolhub.view.fragment.ProgressDialogFragment;
import id.hub.school.schoolhub.view.fragment.ScheduleFragment;
import id.hub.school.schoolhub.view.fragment.SettingsFragment;

import static android.support.design.widget.NavigationView.*;

public final class MainActivity extends BaseActivity implements MainView,
        SettingsFragment.Controller, DiscussionFragment.Controller {

    public static final String TAG_LOADING = "loading";
    public static final int REQUEST_CODE_DISCUSSION_FORM = 100;
    @InjectView(R.id.action_bar) Toolbar toolbar;
    @InjectView(R.id.navigation) NavigationView navigationView;
    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_navigation_view);
        ButterKnife.inject(this);

        setupToolbar();
        setupNavigationDrawer();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            throw new IllegalStateException("Action bar must not be null.");
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }


    private void setupNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navHome:
                        replaceWithHomeFragment();
                        break;
                    case R.id.navSchedule:
                        replaceWithScheduleFragment();
                        break;
                    case R.id.navDiscussion:
                        replaceWithDiscussionFragment();
                        break;
                    case R.id.navSignOut:
                        signOutApplication();
                        break;
                }
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
            // TODO fajar: call interactor to reload list discussion room from fragment presenter
        }
    }

    @Override
    public void replaceWithHomeFragment() {
        toolbar.setTitle(getString(R.string.app_name));
        openFragment(new HomeFragment());
    }

    @Override
    public void replaceWithScheduleFragment() {
        toolbar.setTitle("Schedule");
        openFragment(new ScheduleFragment());
    }

    @Override
    public void replaceWithDiscussionFragment() {
        toolbar.setTitle("Discussion Room");
        openFragment(new DiscussionFragment());
    }

    private void openFragment(Fragment fragment) {
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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void navigateToCreateDiscussion() {
        startActivityForResult(new Intent(this, DiscussionFormActivity.class),
                REQUEST_CODE_DISCUSSION_FORM);
    }
}
