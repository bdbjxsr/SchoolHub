package id.hub.school.schoolhub.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;

import butterknife.ButterKnife;
import id.hub.school.schoolhub.R;
import id.hub.school.schoolhub.view.DiscussionFormView;

public class DiscussionFormFragment extends BaseFragment implements DiscussionFormView {
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
        View view = inflater.inflate(R.layout.fragment_discussion_form, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void showJudulError(String message) {

    }

    @Override
    public void showQuestionError(String message) {

    }

    @Override
    public String getJudul() {
        return null;
    }

    @Override
    public String getQuestion() {
        return null;
    }

    @Override
    public String getKategori() {
        return null;
    }

    @Override
    public File getImage() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return null;
    }

    public interface Controller{

    }
}
