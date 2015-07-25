package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.SchoolHubApp;
import id.hub.school.schoolhub.model.data.ScheduleObject;
import id.hub.school.schoolhub.presenter.SchedulePresenter;
import id.hub.school.schoolhub.utils.ConvertUtil;
import id.hub.school.schoolhub.view.SchedulePageView;
import id.hub.school.schoolhub.view.adapter.ScheduleAdapter;

public class SchedulePageFragment extends BaseFragment implements SchedulePageView,
        ScheduleAdapter.Listener {

    public static final String ARG_PAGE = "arg_page";
    public static final String LOADING_DIALOG = "loadingDialog";

    @InjectView(R.id.list_item) ListView listView;
    @InjectView(R.id.empty) TextView empty;

    @Inject SchedulePresenter presenter;
    @Inject Tracker tracker;

    private Controller controller;

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
        if (! (activity instanceof  Controller)) {
            throw new ClassCastException("Activity must implement " + Controller.class);
        }
        controller = (Controller) activity;
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
        presenter.loadSchedule(ConvertUtil.convertToDayName(getArguments().getInt(ARG_PAGE)));
    }

    @Override
    public void showListSchedule(ScheduleAdapter adapter) {
        adapter.setListener(this);
        if (listView != null) listView.setAdapter(adapter);
    }

    @Override
    public void reloadList() { controller.reloadAfterDelete(getArguments().getInt(ARG_PAGE)); }

    @Override
    public void showProgress() {
        ProgressDialogFragment.show(getString(R.string.message_loading),
                getChildFragmentManager(), LOADING_DIALOG);
    }

    @Override
    public void hideProgress() {
        ProgressDialogFragment.dismiss(getChildFragmentManager(), LOADING_DIALOG);
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
    public Context getContext() { return getActivity(); }

    @Override
    public void onItemClick(View v) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Schedule")
                .setAction("click")
                .setLabel("Edit Schedule")
                .build());

        ScheduleObject object = (ScheduleObject) v.getTag();
        controller.navigateToEditSchedule(object, getArguments().getInt(ARG_PAGE));
    }

    @Override
    public void onLongItemClick(View v) {
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory("Schedule")
                .setAction("long click")
                .setLabel("Delete schedule")
                .build());

        final ScheduleObject object = (ScheduleObject) v.getTag();
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("Delete " + object.getTITLE());
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteObject(object);
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create().show();
    }

    public interface Controller {
        void navigateToEditSchedule(ScheduleObject object, int position);

        void reloadAfterDelete(int page);
    }
}
