package id.hub.school.schoolhub.model.network;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.hub.school.schoolhub.AnalyticsProperty;

@Module
public class NetworkModule {

    @Provides
    AnalyticsProperty provideAnalyticsProperty(){ return AnalyticsProperty.PRODUCTION; }

    @Provides
    @Singleton
    GoogleAnalytics provideGoogleAnalytics(Application application){
        GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(application);
        googleAnalytics.setLocalDispatchPeriod(1800);
        return googleAnalytics;
    }

    @Provides
    @Singleton
    Tracker prodiveTracker(GoogleAnalytics googleAnalytics, AnalyticsProperty property) {
        Tracker tracker = googleAnalytics.newTracker(property.getValue());
        tracker.enableExceptionReporting(true);
        tracker.enableAutoActivityTracking(false);
        return tracker;
    }
}
