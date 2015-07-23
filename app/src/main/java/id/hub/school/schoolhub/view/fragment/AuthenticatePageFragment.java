package id.hub.school.schoolhub.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;

public class AuthenticatePageFragment extends BaseFragment {

    public static final String ARGS_POSITION = "args_position";

    private final String[] wording = {
            "Arrange your daily schedule to manage your time. " +
                    "Set up a time for studying or doing your homework.",
            "Ask something you dont understand, " +
                    "or help other student learn something they dont understand."
    };

    private final int[] icons = {
            R.drawable.onboard_one,
            R.drawable.onboard_two
    };

    @InjectView(R.id.icon) ImageView icon;
    @InjectView(R.id.label) TextView label;

    public static AuthenticatePageFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(ARGS_POSITION, position);

        AuthenticatePageFragment fragment = new AuthenticatePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth_pager, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int position = getArguments().getInt(ARGS_POSITION);

        icon.setImageResource(icons[position]);
        label.setText(wording[position]);

    }
}
