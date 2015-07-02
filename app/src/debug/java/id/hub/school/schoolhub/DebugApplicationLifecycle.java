package id.hub.school.schoolhub;

import android.app.Application;

import javax.inject.Inject;

final class DebugApplicationLifecycle implements ApplicationLifecycle {

    private final Application application;

    @Inject
    public DebugApplicationLifecycle(Application application) {
        this.application = application;
    }

    @Override
    public void onApplicationCreated() {

    }
}
