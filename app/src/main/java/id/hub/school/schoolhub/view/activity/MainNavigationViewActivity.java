package id.hub.school.schoolhub.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.fragment.DiscussionFragment;
import id.hub.school.schoolhub.view.fragment.EventsFragment;
import id.hub.school.schoolhub.view.fragment.HomeFragment;
import id.hub.school.schoolhub.view.fragment.ScheduleFragment;
import id.hub.school.schoolhub.view.fragment.SettingsFragment;
import id.hub.school.schoolhub.view.fragment.SettingsFragment.Controller;

import static android.support.design.widget.NavigationView.*;

public final class MainNavigationViewActivity extends BaseActivity implements Controller {

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
                    case R.id.navEvents:
                        replaceWithEventsFragment();
                        break;
                    case R.id.navSchedule:
                        replaceWithScheduleFragment();
                        break;
                    case R.id.navDiscussion:
                        replaceWithDiscussionFragment();
                        break;
                    case R.id.navSignOut:
                        break;
                }
                return true;
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);

        drawerLayout.setDrawerListener(drawerToggle);
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
    public void replaceWithHomeFragment() {
        openFragment(new HomeFragment());
    }

    @Override
    public void replaceWithEventsFragment() {
        openFragment(new EventsFragment());
    }

    @Override
    public void replaceWithScheduleFragment() {
        openFragment(new ScheduleFragment());
    }

    @Override
    public void replaceWithDiscussionFragment() {
        openFragment(new DiscussionFragment());
    }

    private void openFragment(Fragment fragment) {
        drawerLayout.closeDrawers();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment).commit();
    }
}
