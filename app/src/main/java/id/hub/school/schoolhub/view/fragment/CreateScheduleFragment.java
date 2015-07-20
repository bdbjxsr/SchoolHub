package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.CreateSchedulePresenter;
import id.hub.school.schoolhub.utils.ConvertUtil;
import id.hub.school.schoolhub.view.CreateScheduleView;
import id.hub.school.schoolhub.view.activity.CreateScheduleActivity;
import id.hub.school.schoolhub.view.widget.TimePickerView;

public class CreateScheduleFragment extends BaseFragment implements CreateScheduleView {

    public static final String TAG_TIME_PICKER = "timePicker";
    public static final String TAG_LOADING = "loading";

    @InjectView(R.id.action_bar) Toolbar toolbar;
    @InjectView(R.id.time_picker_view) TimePickerView timePickerView;
    @InjectView(R.id.title_text_input_layout) TextInputLayout titleTextInputLayout;
    @InjectView(R.id.day_rad_group) RadioGroup dayRadGroup;

    @Inject CreateSchedulePresenter presenter;

    private Controller controller;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SchoolHubApp.get(getActivity()).component().inject(this);
        if (! (activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_form, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((CreateScheduleActivity) getActivity()).setupToolbar(toolbar);
    }

    @OnClick(R.id.time_picker_view)
    void onTimePickerViewClick() {
        TimePickerFragment timePickerDialog = new TimePickerFragment();
        timePickerDialog.setTime(timePickerView);
        timePickerDialog.show(getFragmentManager(), TAG_TIME_PICKER);
    }

    @OnClick(R.id.button_submit)
    void onSubmitButtonClick() { presenter.onSubmitClick(); }

    @Override
    public void showTitleError(String message) {
        titleTextInputLayout.setErrorEnabled(true);
        titleTextInputLayout.setError(message);
    }

    @Override
    public void hideTitleError() { titleTextInputLayout.setErrorEnabled(false); }

    @Override
    public String getTitle() { return titleTextInputLayout.getEditText().getText().toString(); }

    @Override
    public String getDay() {
        switch (dayRadGroup.getCheckedRadioButtonId()) {
            case R.id.mon_rad_button:
                return ConvertUtil.convertToDayName(0);
            case R.id.tue_rad_button:
                return ConvertUtil.convertToDayName(1);
            case R.id.wed_rad_button:
                return ConvertUtil.convertToDayName(2);
            case R.id.thu_rad_button:
                return ConvertUtil.convertToDayName(3);
            case R.id.fri_rad_button:
                return ConvertUtil.convertToDayName(4);
        }
        return null;
    }

    @Override
    public String getTime() { return timePickerView.getTime(); }

    @Override
    public void finishCreateSchedule() {
        controller.finishCreateSchedule();
    }

    @Override
    public void showProgress() {
        ProgressDialogFragment.show(getString(R.string.message_loading),
                getFragmentManager(), TAG_LOADING);
    }

    @Override
    public void hideProgress() {
        ProgressDialogFragment.dismiss(getFragmentManager(), TAG_LOADING);
    }

    @Override
    public void showRetry() {}

    @Override
    public void hideRetry() {}

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    public interface Controller {
        void finishCreateSchedule();
    }
}
