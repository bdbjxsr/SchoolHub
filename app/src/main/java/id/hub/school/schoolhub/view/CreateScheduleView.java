package id.hub.school.schoolhub.view;

public interface CreateScheduleView extends LoadDataView {
    void showTitleError(String message);

    void hideTitleError();

    String getTitle();

    String getDay();

    String getTime();

    void finishCreateSchedule();
}
