package com.ibtikar.randomusers.homePage.retrofit;

import com.ibtikar.randomusers.model.pojos.UserList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by norhan.elsawi on 11/19/2017.
 */

public interface HomePageRetrofitApi {

    @GET("api/")
    Observable<UserList> getUser(@Query("results") int results);
}
