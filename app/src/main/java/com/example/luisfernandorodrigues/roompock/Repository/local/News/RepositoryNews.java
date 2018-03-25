
package com.example.luisfernandorodrigues.roompock.Repository.local.News;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.example.luisfernandorodrigues.roompock.Dao.Database.AppDatabase;
import com.example.luisfernandorodrigues.roompock.Dao.Entity.News;

import java.util.List;

public class RepositoryNews {

    private AppDatabase database;
    private static RepositoryNews sInstance;

    private RepositoryNews(AppDatabase database) {
        this.database = database;
    }


    public static RepositoryNews getInstance(final Context appContext) {
        if (sInstance == null) {
            synchronized (RepositoryNews.class) {
                if (sInstance == null) {
                    sInstance = new RepositoryNews(AppDatabase.getDataBase(appContext));
                }
            }
        }
        return sInstance;
    }


    public void insert(final News news){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.newsDao().insertAll(news);
                return null;
            }
        }.execute();

    }



    public void deleteAll(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.newsDao().deleteAll();
                return null;
            }
        }.execute();

    }
    public LiveData<List<News>> getAllNews(){
        return database.newsDao().getAll();
    }
}
