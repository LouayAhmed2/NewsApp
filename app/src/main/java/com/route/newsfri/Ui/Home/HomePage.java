package com.route.newsfri.Ui.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.route.apis.ApiManager;
import com.route.apis.model.ArticlesItem;
import com.route.apis.model.NewsResponse;
import com.route.apis.model.SourcesItem;
import com.route.apis.model.SourcesResponse;
import com.route.newsfri.Constants;
import com.route.newsfri.NewsAdapter;
import com.route.newsfri.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {

    protected TabLayout tablayout;
    protected RecyclerView recyclerView;
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_home_page);
        initView();
        initRecyclerView();
        getNewsSources();
    }

    NewsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    private void initRecyclerView() {
        adapter=new NewsAdapter(null);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void getNewsSources(){
        ApiManager.getApis()
                .getNewsSources(Constants.API_KEY)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(Call<SourcesResponse> call,
                                           Response<SourcesResponse> response) {
                        if("ok".equals(response.body().getStatus())){
                            List<SourcesItem> sources =
                                    response.body()
                                    .getSources();
                            initTabLayout(sources);

                        }else {
                            Toast.makeText(HomePage.this, response.body().message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SourcesResponse> call,
                                          Throwable t) {
                        Toast.makeText(HomePage.this, "please try again later"+
                                t.getLocalizedMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void initTabLayout(final List<SourcesItem> sources) {

        for(SourcesItem source : sources){
         TabLayout.Tab tab =  tablayout.newTab();
         tab.setText(source.getName());
         tab.setTag(source);
         tablayout.addTab(tab);
        }
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                SourcesItem sourcesItem = ((SourcesItem) tab.getTag());
                String soourceId = sourcesItem.getId();
                getNewsBySourceId(soourceId);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                SourcesItem sourcesItem = ((SourcesItem) tab.getTag());
                String soourceId = sourcesItem.getId();
                getNewsBySourceId(soourceId);
            }
        });
        tablayout.getTabAt(0).select();
    }

    private void getNewsBySourceId(String sourceId) {
        ApiManager.getApis()
                .getNewsBySourceId(Constants.API_KEY,sourceId)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call,
                                           Response<NewsResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        if("ok".equals(response.body().getStatus())){
                            List<ArticlesItem> newsList = response.body().getArticles();
                            adapter.changeData(newsList);
                        }else {
                            Toast.makeText(HomePage.this, response.body().message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call,
                                          Throwable t) {
                        Toast.makeText(HomePage.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
}
