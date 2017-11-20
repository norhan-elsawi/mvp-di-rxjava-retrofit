package com.ibtikar.randomusers.di;

import android.content.Context;

import com.google.gson.Gson;
import com.ibtikar.randomusers.di.modules.ApplicationModule;
import com.ibtikar.randomusers.di.modules.NetworkModule;
import com.ibtikar.randomusers.di.scopes.ApplicationScope;
import com.ibtikar.randomusers.model.localDataProvider.DataManager;
import com.squareup.picasso.Picasso;

import dagger.Component;
import retrofit2.Retrofit;

@ApplicationScope
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    Context getContext();

    Gson getGson();

    Picasso getPicasso();

    DataManager getDataManager();

    Retrofit getRetrofit();
}
