package id.hub.school.schoolhub.view;

import android.content.Context;

public interface LoadDataView {
    void showProgress();

    void hideProgress();

    void showRetry();

    void hideRetry();

    void showError(String message);

    Context getContext();
}
