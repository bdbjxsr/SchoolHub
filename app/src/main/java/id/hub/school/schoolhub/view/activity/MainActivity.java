package id.hub.school.schoolhub.view.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.fragment.SettingsFragment;
import id.hub.school.schoolhub.view.fragment.SettingsFragment.Controller;

public final class MainActivity extends BaseActivity implements Controller {


    @InjectView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @InjectView(R.id.action_bar) Toolbar toolbar;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setupToolbar();
        setupNavigationDrawer(savedInstanceState);
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

    private void setupNavigationDrawer(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            SettingsFragment fragment = new SettingsFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.nav_drawer_container, fragment)
                    .commit();

        }

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
        drawerLayout.closeDrawers();
        Toast.makeText(this, "Show Home Fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void replaceWithEventsFragment() {
        drawerLayout.closeDrawers();
        Toast.makeText(this, "Show Events Fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void replaceWithScheduleFragment() {
        drawerLayout.closeDrawers();
        Toast.makeText(this, "Show Schedule Fragment", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void replaceWithDiscussionFragment() {
        drawerLayout.closeDrawers();
        Toast.makeText(this, "Show Discussion Fragment", Toast.LENGTH_SHORT).show();
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment).commit();
    }
}
