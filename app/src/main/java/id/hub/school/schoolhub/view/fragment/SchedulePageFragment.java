package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.presenter.SchedulePresenter;
import id.hub.school.schoolhub.utils.ConvertUtil;
import id.hub.school.schoolhub.view.SchedulePageView;
import id.hub.school.schoolhub.view.adapter.ScheduleAdapter;

public class SchedulePageFragment extends BaseFragment implements SchedulePageView {
    public static final String ARG_PAGE = "arg_page";

    @InjectView(R.id.list_item) ListView listView;
    @InjectView(R.id.empty) TextView empty;

    @Inject SchedulePresenter presenter;

    public static SchedulePageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);

        SchedulePageFragment fragment = new SchedulePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SchoolHubApp.get(activity).component().inject(this);
        presenter.setView(this);
    }

    @Override
    public void onDetach() {
        presenter.setView(null);
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setEmptyView(empty);
        showProgress();
        presenter.loadSchedule(ConvertUtil.convertToDayName(getArguments().getInt(ARG_PAGE)));
    }

    @Override
    public void showListSchedule(ScheduleAdapter adapter) {
        hideProgress();
        if (listView != null) listView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {}

    @Override
    public void hideProgress() {}

    @Override
    public void showRetry() {}

    @Override
    public void hideRetry() {}

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() { return getActivity(); }

}
