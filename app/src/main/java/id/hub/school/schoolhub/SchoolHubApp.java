package id.hub.school.schoolhub;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import javax.inject.Inject;

import id.hub.school.schoolhub.model.data.OpenDiscussionObject;
import id.hub.school.schoolhub.model.data.RuangDiskusiObject;

public class SchoolHubApp extends Application {
    private SchoolHubGraph component;

    @Inject ApplicationLifecycle applicationLifecycle;

    @Override
    public void onCreate() {
        super.onCreate();

        component = SchoolHubComponent.Initializer.init(this);
        component.inject(this);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "ey3vNH1eOoQC4HI6AEIhh7sguh5oRiCsyF05juPv",
                "62m3R8nJyQNsMMv1VxtIXQJigHCgggLZ5vd86olM");

        ParseObject.registerSubclass(RuangDiskusiObject.class);
        ParseObject.registerSubclass(OpenDiscussionObject.class);

        applicationLifecycle.onApplicationCreated();
    }

    public SchoolHubGraph component() { return component; }

    public static SchoolHubApp get(Context context) {
        return (SchoolHubApp) context.getApplicationContext();
    }
}
