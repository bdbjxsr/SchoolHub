package id.hub.school.schoolhub;

import javax.inject.Singleton;

import dagger.Component;
import id.hub.school.schoolhub.model.network.NetworkModule;

@Singleton
@Component(modules = {SchoolHubModule.class, NetworkModule.class})
public interface SchoolHubComponent extends SchoolHubGraph {}
