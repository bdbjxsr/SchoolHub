package id.hub.school.schoolhub.view;

public interface CreateScheduleView extends LoadDataView {
    void setDay();

    void showTitleError(String message);

    void hideTitleError();

    String getTitle();

    String getDay();

    String getTime();

    void finishCreateSchedule(int day);

    void setTitleEditTExt(String title);

    void setTimeView(String time);

    void setCurrentDay(String day);
}
