package com.example.luisfernandorodrigues.roompock.Repository.remote.News;

import android.content.Context;

import com.example.luisfernandorodrigues.roompock.Dao.Entity.News;
import com.example.luisfernandorodrigues.roompock.Repository.local.News.RepositoryNews;
import com.example.luisfernandorodrigues.roompock.Repository.remote.Commons.BaseRemoteRepository;
import com.example.luisfernandorodrigues.roompock.Repository.remote.Commons.RequestResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRemoteRepository extends BaseRemoteRepository<ServiceNews> {

    private RequestResponse requestResponse;
    private RepositoryNews repositoryNews;


    public NewsRemoteRepository(Context appContext , RequestResponse requestResponse) {
        super(ServiceNews.class);
        this.requestResponse = requestResponse;
        this.repositoryNews = RepositoryNews.getInstance(appContext);
    }

    public void getNews(){
        getService().getNews(BaseRemoteRepository.APIKEY).enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                requestResponse.onResponse(response);
                if(response.body() != null){
                    for (News news: response.body()) {
                        repositoryNews.insert(news);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                requestResponse.onResponseError(call, t);
            }
        });
    }



}
