package id.hub.school.schoolhub;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

public class SchoolHubApp extends Application {
    private SchoolHubGraph component;

    @Inject ApplicationLifecycle applicationLifecycle;

    @Override
    public void onCreate() {
        super.onCreate();

        component = SchoolHubComponent.Initializer.init(this);
        component.inject(this);

        applicationLifecycle.onApplicationCreated();
    }

    public SchoolHubGraph component() { return component; }

    public static SchoolHubApp get(Context context) {
        return (SchoolHubApp) context.getApplicationContext();
    }
}
