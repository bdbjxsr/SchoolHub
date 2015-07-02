package id.hub.school.schoolhub;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class SchoolHubModule {
    private final SchoolHubApp app;

    public SchoolHubModule(SchoolHubApp app) { this.app = app; }

    @Provides @Singleton
    Application provideApplication() { return app; }

    @Provides
    ApplicationLifecycle provideApplicationLifecycle() {
        return new DebugApplicationLifecycle(app);
    }
}
