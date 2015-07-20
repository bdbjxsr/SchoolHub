package id.hub.school.schoolhub.view.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.Tracker;
import com.parse.ParseUser;

import java.util.Calendar;

import javax.inject.Inject;

import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.model.AccountPreferences;
import id.hub.school.schoolhub.service.NotifyService;
import id.hub.school.schoolhub.utils.ConvertUtil;
import id.hub.school.schoolhub.utils.TimeUtil;
import id.hub.school.schoolhub.view.EntranceView;

public final class EntranceActivity extends BaseActivity implements EntranceView {

    @Inject AccountPreferences accountPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SchoolHubApp.get(this).component().inject(this);

        alarmMethod();

        if (ParseUser.getCurrentUser() != null) {
            navigateToMainActivity();
        } else {
            navigateToAuthenticateAcivity();
        }
    }

    private void alarmMethod() {
        Intent myIntent = new Intent(this, NotifyService.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 40);
        calendar.set(Calendar.HOUR, 21);
        calendar.set(Calendar.AM_PM, Calendar.PM);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 1000*60*60*24 , pendingIntent);
    }


    @Override
    public void navigateToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void navigateToAuthenticateAcivity() {
        startActivity(new Intent(this, AuthenticateActivity.class));
        finish();
    }

    @Override
    public void setupToolbar(Toolbar toolbar) {}
}
