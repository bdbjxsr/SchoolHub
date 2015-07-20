package id.hub.school.schoolhub.interactor;

import android.text.TextUtils;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.model.data.ScheduleObject;
import id.hub.school.schoolhub.presenter.CreateScheduleListener;

@Singleton
public class CreateScheduleInteractorImp implements CreateScheduleInteractor {

    @Inject
    public CreateScheduleInteractorImp() {}

    @Override
    public void validateCreateSchedule(String title, String day, String time,
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
                        listener.onCreateScheduleSuccess();
                    } else {
                        listener.onCreateScheduleFailed(e.getMessage());
                    }
                }
            });
        }
    }
}
