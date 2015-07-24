package id.hub.school.schoolhub.presenter;

import android.text.TextUtils;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import id.hub.school.schoolhub.interactor.CreateScheduleInteractorImp;
import id.hub.school.schoolhub.model.data.ScheduleObject;
import id.hub.school.schoolhub.utils.ConvertUtil;
import id.hub.school.schoolhub.view.CreateScheduleView;

@Singleton
public class CreateSchedulePresenter implements BasePresenter, CreateScheduleListener {

    private CreateScheduleView view;
    private ScheduleObject object;

    @Inject CreateScheduleInteractorImp interactorImp;

    @Inject public CreateSchedulePresenter() {}

    public void setView(CreateScheduleView view) { this.view = view; }

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    public void onSubmitClick() {
        view.hideTitleError();

        if (TextUtils.isEmpty(view.getTitle())) {
            view.showTitleError("Title cannot be empty");
        } else {
            view.showProgress();
            interactorImp.validateCreateSchedule(view.getTitle(), view.getDay(),
                    view.getTime(), this);
        }
    }

    @Override
    public void onTitleError(String message) {
        view.hideProgress();
        view.showTitleError(message);
    }

    @Override
    public void onCreateScheduleSuccess() {
        view.hideProgress();
        view.finishCreateSchedule(ConvertUtil.convertToDayPositionOnPage(view.getDay()));
    }

    @Override
    public void onCreateScheduleFailed(String message) {
        view.hideProgress();
        view.showError(message);
    }

    @Override
    public void successGetObject(ScheduleObject object) {
        view.setTitleEditTExt(object.getTITLE());
        view.setTimeView(object.getTIME());
        view.setCurrentDay(object.getDAY());

        this.object = object;
    }

    @Override
    public void failetGetObject(String message) { view.showError(message); }

    @Override
    public void updateSuccess() {
        view.hideProgress();
        view.finishCreateSchedule(ConvertUtil.convertToDayPositionOnPage(view.getDay()));
    }

    @Override
    public void updateFailed(String message) {
        view.hideProgress();
        view.showError(message);
    }

    public void loadSchedule(String objectId) {
        interactorImp.findSchedule(objectId, this);
    }

    public void onUpdateClick() {
        view.hideTitleError();
        if (TextUtils.isEmpty(view.getTitle())) {
            view.showTitleError("Title cannot be empty");
        } else {
            if (object != null) {
                view.showProgress();
                interactorImp.updateSchedule(object, view.getTitle(), view.getDay(),
                        view.getTime(), this);
            }
        }
    }
}
