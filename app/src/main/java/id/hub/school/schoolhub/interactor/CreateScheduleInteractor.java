package id.hub.school.schoolhub.interactor;

import id.hub.school.schoolhub.presenter.CreateScheduleListener;

public interface CreateScheduleInteractor {
    void validateCreateSchedule(String title, String day, String time,
                                CreateScheduleListener listener);
}
