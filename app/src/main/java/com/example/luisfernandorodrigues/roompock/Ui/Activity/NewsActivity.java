package com.example.luisfernandorodrigues.roompock.Ui.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.luisfernandorodrigues.roompock.Dao.Entity.News;
import com.example.luisfernandorodrigues.roompock.R;
import com.example.luisfernandorodrigues.roompock.Ui.Adapter.NewsAdapter;
import com.example.luisfernandorodrigues.roompock.Ui.ViewModel.ViewModelNews;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private ViewModelNews viewModelNews;
    private List<News> news = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        viewModelNews = ViewModelProviders.of(this).get(ViewModelNews.class);
        observers();

        newsAdapter = new NewsAdapter(getApplicationContext(), news );

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(newsAdapter);
        viewModelNews.removeAll();
        viewModelNews.getAll();
    }

    public void observers(){
        viewModelNews.getLocalLiveData().observe(this , newsList -> {
            if(news.size() > 0) news.removeAll(news);
            if(newsList.size() > 0){
                news.addAll(newsList);
                findViewById(R.id.progress).setVisibility(View.GONE);
            }else {
                findViewById(R.id.progress).setVisibility(View.VISIBLE);
            }

            newsAdapter.notifyDataSetChanged();
        });
    }
}
