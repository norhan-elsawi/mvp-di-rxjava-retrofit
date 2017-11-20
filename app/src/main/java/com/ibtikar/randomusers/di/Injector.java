package com.ibtikar.randomusers.di;

import com.ibtikar.randomusers.application.RandomUsers;
import com.ibtikar.randomusers.di.modules.ApplicationModule;
import com.ibtikar.randomusers.di.modules.NetworkModule;
import com.ibtikar.randomusers.model.utils.Constants;

/**
 * Created by norhan.elsawi on 11/16/2017.
 */

public enum Injector {
    INSTANCE;

    private ApplicationComponent applicationComponent;

    public ApplicationComponent initializeAppComponent(RandomUsers application) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(application))
                .networkModule(new NetworkModule(Constants.SERVER_URL))
                .build();
        return applicationComponent;
    }

    public ApplicationComponent getAppComponent() {
        return applicationComponent;
    }
}
