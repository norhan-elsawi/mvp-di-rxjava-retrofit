package com.ibtikar.randomusers.homePage.mvp;

import com.ibtikar.randomusers.di.scopes.ActivityScope;
import com.ibtikar.randomusers.homePage.retrofit.GetNUsersListener;
import com.ibtikar.randomusers.model.pojos.Result;

import java.util.ArrayList;

import javax.inject.Inject;

import timber.log.Timber;


@ActivityScope
public class HomePagePresenter implements HomePageContract.Presenter, GetNUsersListener {

    HomePageContract.View view;
    HomePageContract.Model model;


    @Inject
    public HomePagePresenter(HomePageContract.View view, HomePageContract.Model model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void showFirstNUsers() {
        model.getNUsers(10, true, this);
    }

    @Override
    public void loadMore() {
        model.getNUsers(5, false, this);
    }

    @Override
    public void dataLoaded(ArrayList<Result> results, boolean firstTime) {
        if (firstTime) {
            view.showFirstNUsers(results);
        } else {
            view.showAnotherNUsers(results);
        }
    }

    @Override
    public void failLoading(Throwable throwable) {
        Timber.e(throwable);
    }
}
