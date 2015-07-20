package id.hub.school.schoolhub.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;

public class TimePickerView extends FrameLayout {

    @InjectView(R.id.hour) TextView hourTextView;
    @InjectView(R.id.minute) TextView minuteTextView;

    public TimePickerView(Context context) {
        super(context);
    }

    public TimePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_time_picker, this);
        ButterKnife.inject(this);
        initDefaultTime();
    }

    private void initDefaultTime() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        setHour(hour);
        setMinute(minute);
    }

    public void setHour(int hour) {
        String stringHour = String.valueOf(hour);
        if (hour < 10) {
            stringHour = "0" + hour;
        }
        hourTextView.setText(stringHour);
    }

    public void setMinute(int minute) {
        String stringMinute = String.valueOf(minute);
        if (minute < 10) {
            stringMinute = "0" + minute;
        }
        minuteTextView.setText(stringMinute);
    }

    public String getTime() {
        return hourTextView.getText() + ":" + minuteTextView.getText();
    }
}
