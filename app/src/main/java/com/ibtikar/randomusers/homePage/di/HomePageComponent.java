package com.ibtikar.randomusers.homePage.di;

import com.ibtikar.randomusers.di.ApplicationComponent;
import com.ibtikar.randomusers.di.scopes.ActivityScope;
import com.ibtikar.randomusers.homePage.mvp.HomePageActivity;

import dagger.Component;

@ActivityScope
@Component(modules = HomePageModule.class, dependencies = ApplicationComponent.class)
public interface HomePageComponent {
    void inject(HomePageActivity activity);
}
