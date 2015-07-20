package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.activity.CreateScheduleActivity;
import id.hub.school.schoolhub.view.widget.TimePickerView;

public class CreateScheduleFragment extends BaseFragment {

    public static final String TAG_TIME_PICKER = "timePicker";

    @InjectView(R.id.action_bar) Toolbar toolbar;
    @InjectView(R.id.time_picker_view) TimePickerView timePickerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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

    public interface Controller {

    }
}
