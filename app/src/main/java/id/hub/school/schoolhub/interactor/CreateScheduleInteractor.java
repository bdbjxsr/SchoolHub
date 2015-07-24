package id.hub.school.schoolhub.interactor;

import id.hub.school.schoolhub.model.data.ScheduleObject;
import id.hub.school.schoolhub.presenter.CreateScheduleListener;

public interface CreateScheduleInteractor {
    void validateCreateSchedule(String title, String day, String time,
                                CreateScheduleListener listener);

    void findSchedule(String objectId, CreateScheduleListener listener);

    void updateSchedule(ScheduleObject objectId, String title, String day, String time,
                        CreateScheduleListener listener);
}
