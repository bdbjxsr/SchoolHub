package id.hub.school.schoolhub;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SchoolHubModule.class})
public interface SchoolHubComponent extends SchoolHubGraph {}
