package com.ibtikar.randomusers.application;

import android.app.Application;

import com.ibtikar.randomusers.BuildConfig;
import com.ibtikar.randomusers.di.Injector;
import com.orhanobut.logger.Logger;

import timber.log.Timber;


public class RandomUsers extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree() {
                @Override
                public void log(int priority, String tag, String message, Throwable t) {
                    Logger.log(priority, tag, message, t);
                }
            });
        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        if (Injector.INSTANCE.getAppComponent() == null) {
            Injector.INSTANCE.initializeAppComponent(this);
        }
    }
}
