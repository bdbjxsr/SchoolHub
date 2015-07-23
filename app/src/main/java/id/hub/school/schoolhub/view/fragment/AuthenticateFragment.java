package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.AuthenticatePresenter;
import id.hub.school.schoolhub.view.AuthenticateView;

public class AuthenticateFragment extends BaseFragment implements AuthenticateView {

    @Inject AuthenticatePresenter authenticatePresenter;

    @InjectView(R.id.view_pager) ViewPager pager;

    @InjectView(R.id.indicator) CirclePageIndicator indicator;

    private Controller controller;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SchoolHubApp.get(activity).component().inject(this);

        if (!(activity instanceof Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
        authenticatePresenter.setAuthenticateView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authentication, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AuthenticatePageAdapter adapter = new AuthenticatePageAdapter(getChildFragmentManager());

        pager.setAdapter(adapter);

        indicator.setViewPager(pager);
        indicator.setRadius(6);

    }

    @OnClick(R.id.login_button)
    void onButtonLoginClick() {
        authenticatePresenter.onButtonLoginClick();
    }

    @OnClick(R.id.signup_button)
    void onButtonSignupClick() {
        authenticatePresenter.onButtonSignupClick();
    }

    @Override
    public void navigateToLoginActivity() {
        controller.navigatetoLoginActivity();
    }

    @Override
    public void navigateToSignupActivity() {
        controller.navigateToSignupActivity();
    }

    public interface Controller {
        void navigatetoLoginActivity();

        void navigateToSignupActivity();
    }

}
