package com.ibtikar.randomusers.homePage.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.ibtikar.randomusers.di.scopes.ActivityScope;
import com.ibtikar.randomusers.homePage.mvp.HomePageContract;
import com.ibtikar.randomusers.homePage.mvp.HomePageModel;
import com.ibtikar.randomusers.homePage.mvp.HomePagePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomePageModule {

    private final HomePageContract.View view;

    public HomePageModule(HomePageContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HomePageContract.View provideView() {
        return view;
    }

    @ActivityScope
    @Provides
    HomePageContract.Model provideModel(HomePageModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    HomePageContract.Presenter providePresenter(HomePagePresenter presenter) {
        return  presenter;
    }

    @ActivityScope
    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager((Context)view);
    }

}
