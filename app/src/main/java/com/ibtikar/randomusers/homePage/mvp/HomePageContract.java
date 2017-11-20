package com.ibtikar.randomusers.homePage.mvp;


import com.ibtikar.randomusers.baseInterfaces.BaseIModel;
import com.ibtikar.randomusers.baseInterfaces.BaseIPresenter;
import com.ibtikar.randomusers.baseInterfaces.BaseIView;
import com.ibtikar.randomusers.homePage.retrofit.GetNUsersListener;
import com.ibtikar.randomusers.model.pojos.Result;

import java.util.ArrayList;

public interface HomePageContract {

    interface View extends BaseIView {

        void showFirstNUsers(ArrayList<Result> results);
        void showAnotherNUsers(ArrayList<Result> results);
    }

    interface Model extends BaseIModel {

        void getNUsers(int number,boolean firstTime,GetNUsersListener listener);

    }

    interface Presenter extends BaseIPresenter {
        void showFirstNUsers();

        void loadMore();
    }
}
