package id.hub.school.schoolhub.presenter;

import id.hub.school.schoolhub.model.data.ScheduleObject;

public interface CreateScheduleListener {
    void onTitleError(String message);

    void onCreateScheduleSuccess();

    void onCreateScheduleFailed(String message);

    void successGetObject(ScheduleObject object);

    void failetGetObject(String message);

    void updateSuccess();

    void updateFailed(String message);
}
