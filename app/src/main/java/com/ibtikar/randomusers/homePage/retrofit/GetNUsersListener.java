package com.ibtikar.randomusers.homePage.retrofit;

import com.ibtikar.randomusers.model.pojos.Result;

import java.util.ArrayList;


public interface GetNUsersListener {
    void dataLoaded(ArrayList<Result> results, boolean firstTime);

    void failLoading(Throwable throwable);
}
