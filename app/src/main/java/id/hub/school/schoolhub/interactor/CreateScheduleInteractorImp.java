package id.hub.school.schoolhub.interactor;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.model.data.ScheduleObject;
import id.hub.school.schoolhub.presenter.CreateScheduleListener;

@Singleton
public class CreateScheduleInteractorImp implements CreateScheduleInteractor {

    @Inject
    public CreateScheduleInteractorImp() {}

    @Override
    public void validateCreateSchedule(String title, final String day, final String time,
                                       final CreateScheduleListener listener) {
        ScheduleObject object = new ScheduleObject();
        UUID uuid = UUID.randomUUID();
        object.setUuid(uuid.toString());
        object.setTitle(title);
        object.setDay(day);
        object.setTime(time);
        object.setUser(ParseUser.getCurrentUser());
        object.setNotification(true);
        object.pinInBackground(ScheduleObject.ALL_SCHEDULE, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    listener.onCreateScheduleSuccess();
                } else {
                    listener.onCreateScheduleFailed(e.getMessage());
                }
            }
        });
    }

    @Override
    public void findSchedule(String objectId, final CreateScheduleListener listener) {
        ParseQuery<ScheduleObject> query = ScheduleObject.getQuery();
        query.fromLocalDatastore();
        query.whereEqualTo(ScheduleObject.UUID, objectId);
        query.getFirstInBackground(new GetCallback<ScheduleObject>() {
            @Override
            public void done(ScheduleObject object, ParseException e) {
                if (e == null) {
                    listener.successGetObject(object);
                } else {
                    listener.failetGetObject(e.getMessage());
                }
            }
        });

    }

    @Override
    public void updateSchedule(ScheduleObject object, final String title, final String day,final String time,
                               final CreateScheduleListener listener) {
        object.setTitle(title);
        object.setDay(day);
        object.setTime(time);
        object.setUser(ParseUser.getCurrentUser());
        object.setNotification(true);
        object.pinInBackground(ScheduleObject.ALL_SCHEDULE, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    listener.updateSuccess();
                } else {
                    listener.updateFailed(e.getMessage());
                }
            }
        });

    }


}
