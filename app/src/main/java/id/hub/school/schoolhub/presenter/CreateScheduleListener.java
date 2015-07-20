package id.hub.school.schoolhub.presenter;

public interface CreateScheduleListener {
    void onTitleError(String message);

    void onCreateScheduleSuccess();

    void onCreateScheduleFailed(String message);
}
