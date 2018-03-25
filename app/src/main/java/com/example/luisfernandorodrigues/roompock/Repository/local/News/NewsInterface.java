package com.example.luisfernandorodrigues.roompock.Repository.local.News;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.luisfernandorodrigues.roompock.Dao.Entity.News;

import java.util.List;


@Dao public interface NewsInterface{

    @Query("SELECT * FROM news")
    LiveData<List<News>> getAll();

    @Query("SELECT * FROM news WHERE id IN (:userIds)")
    List<News> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM news WHERE title LIKE :title AND "
            + "short_description LIKE :shortDescription AND description like :description AND date like :date LIMIT 1")
    News findBy(String title, String shortDescription ,String description , String date);

    @Insert
    void insertAll(News... users);

    @Delete
    void delete(News user);

    @Query("DELETE from news")
    void deleteAll();
}
