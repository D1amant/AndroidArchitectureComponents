package com.example.luisfernandorodrigues.roompock.Dao.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.luisfernandorodrigues.roompock.Dao.Migration.NewsMigration;
import com.example.luisfernandorodrigues.roompock.Dao.Entity.News;
import com.example.luisfernandorodrigues.roompock.Repository.local.News.NewsInterface;

@Database(entities = {News.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public static String DATA_BASE_NAME = "roompock";
    public abstract NewsInterface newsDao();

    public static AppDatabase getDataBase(Context appContext){
        return Room.databaseBuilder(appContext, AppDatabase.class, AppDatabase.DATA_BASE_NAME)
                .addMigrations(NewsMigration.MIGRATION_1_2)
                .build();
    }

}