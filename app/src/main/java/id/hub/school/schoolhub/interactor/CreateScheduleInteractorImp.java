package id.hub.school.schoolhub.interactor;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.model.data.ScheduleObject;
import id.hub.school.schoolhub.presenter.CreateScheduleListener;
import id.hub.school.schoolhub.service.NotifyService;
import id.hub.school.schoolhub.utils.ConvertUtil;
import id.hub.school.schoolhub.utils.TimeUtil;

@Singleton
public class CreateScheduleInteractorImp implements CreateScheduleInteractor {

    private Application application;

    @Inject
    public CreateScheduleInteractorImp(Application application) {
        this.application = application;
    }

    @Override
    public void validateCreateSchedule(String title, final String day, final String time,
                                       final CreateScheduleListener listener) {
        if (TextUtils.isEmpty(title)) {
            listener.onTitleError("Title cannot be empty");
        } else {
            ScheduleObject object = new ScheduleObject();
            object.setTitle(title);
            object.setDay(day);
            object.setTime(time);
            object.setUser(ParseUser.getCurrentUser());
            object.setNotification(true);
            object.pinInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        Intent myIntent = new Intent(application, NotifyService.class);
                        AlarmManager alarmManager = (AlarmManager) application
                                .getSystemService(Context.ALARM_SERVICE);
                        PendingIntent pendingIntent = PendingIntent.getService(application, 0,
                                myIntent, 0);

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MINUTE, TimeUtil.getMinuteFromString(time));
                        calendar.set(Calendar.HOUR, TimeUtil.getHourFromString(time));
                        calendar.set(Calendar.AM_PM, Calendar.AM_PM);
                        calendar.add(Calendar.DAY_OF_MONTH, ConvertUtil
                                .convertToDayPositionOnWeek(day));

                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                                calendar.getTimeInMillis(), 1000*60*60*24 , pendingIntent);

                        listener.onCreateScheduleSuccess();
                    } else {
                        listener.onCreateScheduleFailed(e.getMessage());
                    }
                }
            });
        }
    }
}
