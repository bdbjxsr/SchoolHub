package id.hub.school.schoolhub;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SchoolHubModule.class})
public interface SchoolHubComponent extends SchoolHubGraph {

    final static class Initializer {
        static SchoolHubGraph init(SchoolHubApp app) {
            return DaggerSchoolHubComponent.builder()
                    .schoolHubModule(new SchoolHubModule(app))
                    .build();
        }

        private Initializer() {
        } // No instances.
    }
}
