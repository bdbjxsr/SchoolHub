package id.hub.school.schoolhub.presenter;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.model.data.ScheduleObject;
import id.hub.school.schoolhub.view.SchedulePageView;
import id.hub.school.schoolhub.view.adapter.ScheduleAdapter;

@Singleton
public class SchedulePresenter implements BasePresenter {

    private SchedulePageView view;

    @Inject
    public SchedulePresenter() {}

    @Override
    public void resume() { }

    @Override
    public void pause() { }

    @Override
    public void destroy() { }

    public void setView(SchedulePageView view) { this.view = view; }

    public void loadSchedule(final String page) {
        ParseQueryAdapter.QueryFactory<ScheduleObject> factory =
                new ParseQueryAdapter.QueryFactory<ScheduleObject>() {
                    public ParseQuery<ScheduleObject> create() {
                        ParseQuery<ScheduleObject> query = ScheduleObject.getQuery();
                        query.whereMatches(ScheduleObject.DAY, page);
                        query.orderByAscending(ScheduleObject.TIME);
                        query.fromLocalDatastore();
                        return query;
                    }
                };

        if (view != null) {
            view.showListSchedule(new ScheduleAdapter(view.getContext(), factory));
        }
    }

    public void deleteObject(ScheduleObject object) {
        view.showProgress();
        object.unpinInBackground(ScheduleObject.ALL_SCHEDULE, new DeleteCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    view.hideProgress();
                    view.reloadList();
                } else {
                    view.showError(e.getMessage());
                }
            }
        });
    }
}
