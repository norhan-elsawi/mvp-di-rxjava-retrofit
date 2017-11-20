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

import java.util.ArrayList;

import javax.inject.Inject;

public class HomePageActivity extends AppCompatActivity implements HomePageContract.View {

    @Inject
    HomePageContract.Presenter presenter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;

    ArrayList<Result> myResults;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        resolveDaggerDependency(Injector.INSTANCE.getAppComponent());
        initRecyclerView();
        handler = new Handler();
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

    public void showFirstNUsers(ArrayList<Result> results) {
        myResults = results;
        recyclerViewAdapter = new RecyclerViewAdapter(HomePageActivity.this, myResults, recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnLoadMoreListener(() -> {
            presenter.loadMore();
            myResults.add(null);
            recyclerViewAdapter.notifyItemInserted(myResults.size() - 1);

        });
    }

    @Override
    public void showAnotherNUsers(ArrayList<Result> results) {
        handler.postDelayed(() -> {
            myResults.remove(myResults.size() - 1);
            recyclerViewAdapter.notifyItemRemoved(myResults.size() - 1);
            myResults.addAll(results);
            recyclerViewAdapter.notifyItemInserted(myResults.size());
            recyclerViewAdapter.setLoaded();
        }, 2000);
    }

}



