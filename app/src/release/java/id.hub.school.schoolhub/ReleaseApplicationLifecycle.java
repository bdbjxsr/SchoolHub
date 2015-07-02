package id.hub.school.schoolhub;

import android.app.Application;

import javax.inject.Inject;

final class ReleaseApplicationLifecycle implements ApplicationLifecycle {

    private final Application application;

    @Inject
    public ReleaseApplicationLifecycle(Application application) {
        this.application = application;
    }

    @Override
    public void onApplicationCreated() {

    }
}
