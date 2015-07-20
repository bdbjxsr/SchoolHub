package id.hub.school.schoolhub.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.activity.MainActivity;

public class NotifyService extends Service {
    private static final int NOTIFICATION = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this , MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_NOTIFICATION, true);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("School Hub")
                .setContentText("Reminder for your class today")
                .setSmallIcon(R.drawable.ic_alarm_white_24dp)
                .setContentIntent(contentIntent)
                .setSound(sound)
                .addAction(0, "Open My Schedules", contentIntent)
                .build();

        notificationManager.notify(NOTIFICATION, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
