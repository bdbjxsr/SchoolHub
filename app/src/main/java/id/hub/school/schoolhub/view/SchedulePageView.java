package id.hub.school.schoolhub.view;

import id.hub.school.schoolhub.view.adapter.ScheduleAdapter;

public interface SchedulePageView extends LoadDataView {
    void showListSchedule(ScheduleAdapter adapter);

    void reloadList();
}
