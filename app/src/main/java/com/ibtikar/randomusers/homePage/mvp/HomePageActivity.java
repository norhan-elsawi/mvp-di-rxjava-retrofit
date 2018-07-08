package com.ibtikar.randomusers.homePage.mvp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ibtikar.randomusers.R;
import com.ibtikar.randomusers.di.ApplicationComponent;
import com.ibtikar.randomusers.di.Injector;
import com.ibtikar.randomusers.homePage.di.DaggerHomePageComponent;
import com.ibtikar.randomusers.homePage.di.HomePageModule;
import com.ibtikar.randomusers.homePage.mvp.adapters.RecyclerViewAdapter;
import com.ibtikar.randomusers.model.pojos.Result;
import com.ibtikar.randomusers.model.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomePageActivity extends AppCompatActivity implements HomePageContract.View {

    @Inject
    HomePageContract.Presenter presenter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;


    boolean flagLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        resolveDaggerDependency(Injector.INSTANCE.getAppComponent());
        initRecyclerView();
        presenter.showFirstNUsers();

    }


    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    public void resolveDaggerDependency(ApplicationComponent appComponent) {
        DaggerHomePageComponent
                .builder()
                .applicationComponent(appComponent)
                .homePageModule(new HomePageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void failLoading() {
        if (recyclerViewAdapter != null)
            recyclerViewAdapter.hideProgress();
    }

    public void showFirstNUsers(ArrayList<Result> results) {
        recyclerViewAdapter = new RecyclerViewAdapter(HomePageActivity.this, results);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getNUsers();
            }
        });
    }

    private void getNUsers() {
        if (!flagLoading) {
            flagLoading = true;
            recyclerViewAdapter.showProgress();
            presenter.loadMore();
        }
    }

    @Override
    public void showAnotherNUsers(ArrayList<Result> results) {
        flagLoading = false;
        recyclerViewAdapter.hideProgress();
        recyclerViewAdapter.addItems(results);
    }

}



