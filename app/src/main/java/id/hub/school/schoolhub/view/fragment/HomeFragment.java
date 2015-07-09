package id.hub.school.schoolhub.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;

public class HomeFragment extends BaseFragment {

    @InjectView(R.id.textview) TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_simple_text, container, false);
        ButterKnife.inject(this, view);

        textView.setText("Home");
        return view;
    }
}
