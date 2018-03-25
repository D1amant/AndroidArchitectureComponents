package com.example.luisfernandorodrigues.roompock.Repository.remote.News;

import com.example.luisfernandorodrigues.roompock.Dao.Entity.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceNews {

    @GET("news")
    Call<List<News>> getNews(@Query("apiKey") String apiKey);
}
