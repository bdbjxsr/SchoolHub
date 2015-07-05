package id.hub.school.schoolhub.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import id.hub.school.schoolhub.R;

public class LoadingView extends FrameLayout {
    public LoadingView(Context context) { this(context, null); }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_loading, null);
        addView(view);
    }
}
