package com.ibtikar.randomusers.homePage.mvp;

import com.ibtikar.randomusers.di.scopes.ActivityScope;
import com.ibtikar.randomusers.homePage.retrofit.GetNUsersListener;
import com.ibtikar.randomusers.homePage.retrofit.HomePageRetrofitApi;
import com.ibtikar.randomusers.model.pojos.Result;
import com.ibtikar.randomusers.model.pojos.UserList;

import java.util.ArrayList;
import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

@ActivityScope
public class HomePageModel implements HomePageContract.Model {

    Retrofit retrofit;
    HomePageRetrofitApi homePageRetrofitApi;


    @Inject
    public HomePageModel(Retrofit retrofit) {
        this.retrofit = retrofit;
        homePageRetrofitApi = retrofit.create(HomePageRetrofitApi.class);
    }

    @Override
    public void getNUsers(int number,boolean firstTime,GetNUsersListener listener)
    {

                 homePageRetrofitApi.getUser(number)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe( UserList-> listener.dataLoaded(UserList.getResults(),firstTime));
    }


}
