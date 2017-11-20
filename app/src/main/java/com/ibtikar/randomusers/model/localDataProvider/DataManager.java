package com.ibtikar.randomusers.model.localDataProvider;


import android.content.Context;


public class DataManager {

    private Context mContext;
    private SharedPrefsHelper mSharedPrefsHelper;


    public DataManager(Context context, SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

}