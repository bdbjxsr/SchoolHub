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
import android.widget.RadioButton;
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
import id.hub.school.schoolhub.utils.TimeUtil;
import id.hub.school.schoolhub.view.CreateScheduleView;
import id.hub.school.schoolhub.view.activity.CreateScheduleActivity;
import id.hub.school.schoolhub.view.widget.TimePickerView;

public class CreateScheduleFragment extends BaseFragment implements CreateScheduleView {

    public static final String TAG_TIME_PICKER = "timePicker";
    public static final String TAG_LOADING = "loading";
    public static final String ARG_POSITION = "arg_position";
    public static final String ARG_OBJECT_ID = "arg_object_id";

    @InjectView(R.id.action_bar) Toolbar toolbar;
    @InjectView(R.id.time_picker_view) TimePickerView timePickerView;
    @InjectView(R.id.title_text_input_layout) TextInputLayout titleTextInputLayout;
    @InjectView(R.id.day_rad_group) RadioGroup dayRadGroup;

    @Inject CreateSchedulePresenter presenter;

    private Controller controller;
    private boolean edited = false;

    public static CreateScheduleFragment newInstance(Bundle bundle) {
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, bundle.getInt(CreateScheduleActivity.EXTRA_POSITION));

        if (bundle.containsKey(CreateScheduleActivity.EXTRA_OBJECT_ID)) {
            args.putString(ARG_OBJECT_ID, bundle.getString(CreateScheduleActivity.EXTRA_OBJECT_ID));
        }

        CreateScheduleFragment fragment = new CreateScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        setDay();

        if (getArguments().containsKey(ARG_OBJECT_ID)) {
            edited = true;
            presenter.loadSchedule(getArguments().getString(ARG_OBJECT_ID));
        }
    }

    @OnClick(R.id.time_picker_view)
    void onTimePickerViewClick() {
        TimePickerFragment timePickerDialog = new TimePickerFragment();
        timePickerDialog.setTime(timePickerView);
        timePickerDialog.show(getFragmentManager(), TAG_TIME_PICKER);
    }

    @OnClick(R.id.button_submit)
    void onSubmitButtonClick() {
        if (!edited) {
            presenter.onSubmitClick();
        } else {
            presenter.onUpdateClick();
        }
    }

    @Override
    public void setDay() {
        RadioButton radioButton = (RadioButton)
                dayRadGroup.getChildAt(getArguments().getInt(ARG_POSITION));
        radioButton.setChecked(true);
    }

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
    public void finishCreateSchedule(int day) {
        controller.finishCreateSchedule(day);
    }

    @Override
    public void setTitleEditTExt(String title) {
        titleTextInputLayout.getEditText().setText(title);
    }

    @Override
    public void setTimeView(String time) {
        timePickerView.setHour(TimeUtil.getHourFromString(time));
        timePickerView.setMinute(TimeUtil.getMinuteFromString(time));
    }

    @Override
    public void setCurrentDay(String day) {
        int dayOnPosition = ConvertUtil.convertToDayPositionOnPage(day);
        RadioButton radChild = (RadioButton) dayRadGroup.getChildAt(dayOnPosition);
        radChild.setChecked(true);
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
        void finishCreateSchedule(int position);
    }
}
