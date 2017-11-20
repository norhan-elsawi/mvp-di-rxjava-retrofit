package com.ibtikar.randomusers.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibtikar.randomusers.di.scopes.ApplicationScope;
import com.ibtikar.randomusers.model.localDataProvider.DataManager;
import com.ibtikar.randomusers.model.localDataProvider.SharedPrefsHelper;
import com.ibtikar.randomusers.model.utils.BooleanAsIntAdapter;
import com.ibtikar.randomusers.model.utils.Constants;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationScope
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @ApplicationScope
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(Constants.DATE_FORMAT);
        BooleanAsIntAdapter booleanAsIntAdapter = new BooleanAsIntAdapter();
        gsonBuilder.registerTypeAdapter(Boolean.class, booleanAsIntAdapter);
        gsonBuilder.registerTypeAdapter(boolean.class, booleanAsIntAdapter);
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    SharedPrefsHelper provideSharedPrefsHelper(SharedPreferences sharedPreferences) {
        return new SharedPrefsHelper(sharedPreferences);
    }

    @Provides
    @ApplicationScope
    DataManager provideDataManager(Context context, SharedPrefsHelper sharedPrefsHelper) {
        return new DataManager(context, sharedPrefsHelper);
    }


}