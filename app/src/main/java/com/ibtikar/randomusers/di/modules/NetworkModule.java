package com.ibtikar.randomusers.di.modules;


import android.app.Application;

import com.google.gson.Gson;
import com.ibtikar.randomusers.BuildConfig;
import com.ibtikar.randomusers.di.scopes.ApplicationScope;
import com.ibtikar.randomusers.model.utils.CacheUtils;
import com.ibtikar.randomusers.model.utils.Constants;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    @Provides
    @ApplicationScope
    Picasso providePicasso(OkHttpClient client, Application app) {
        File cache = CacheUtils.createDefaultCacheDir(app);
        OkHttpClient picassoOkHttpClient = client
                .newBuilder()
                .cache(new Cache(cache, CacheUtils.calculateDiskCacheSize(cache)))
                .build();

        Picasso.Builder builder = new Picasso
                .Builder(app)
                .downloader(new OkHttp3Downloader(picassoOkHttpClient))
                .listener((picasso, uri, exception) ->
                        Timber.e(exception, "Error while loading image %s", uri));

        if (BuildConfig.DEBUG) {
            builder.indicatorsEnabled(BuildConfig.DEBUG)
                    .loggingEnabled(BuildConfig.DEBUG);
        }

        return builder.build();
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder.build();
    }

    @Provides
    @ApplicationScope
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }
}
