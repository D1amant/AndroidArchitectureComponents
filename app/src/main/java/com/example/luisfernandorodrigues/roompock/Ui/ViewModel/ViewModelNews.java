package com.example.luisfernandorodrigues.roompock.Ui.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.luisfernandorodrigues.roompock.Dao.DatabaseHelper;
import com.example.luisfernandorodrigues.roompock.Dao.Entity.News;
import com.example.luisfernandorodrigues.roompock.Repository.local.News.RepositoryNews;
import com.example.luisfernandorodrigues.roompock.Repository.remote.Commons.RequestResponse;
import com.example.luisfernandorodrigues.roompock.Repository.remote.News.NewsRemoteRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ViewModelNews extends AndroidViewModel implements RequestResponse<Response<List<News>>>{

    private RepositoryNews repositoryNews;
    private NewsRemoteRepository newsRemoteRepository;
    private LiveData<List<News>> localLiveData;
    private MutableLiveData<String> errorLiveData;



    public ViewModelNews(@NonNull Application application) {
        super(application);

        repositoryNews = RepositoryNews.getInstance(DatabaseHelper.getDataBase(getApplication().getApplicationContext()));
        localLiveData = repositoryNews.getAllNews();
        newsRemoteRepository = new NewsRemoteRepository(application.getApplicationContext(), this);
    }

    public void removeAll(){
        repositoryNews.deleteAll();
    }

    public void getAll(){
        newsRemoteRepository.getNews();
    }


    public LiveData<List<News>> getLocalLiveData() {
        return localLiveData;
    }


    @Override
    public void onResponse(Response<List<News>> response) {
        try {
            errorLiveData.postValue(response.errorBody().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponseError(Call<Response<List<News>>> call, Throwable t) {
        errorLiveData.postValue(t.getMessage());
    }

    public LiveData<String> getRemoteLiveData(){
        if (errorLiveData == null) {
            errorLiveData = new MutableLiveData<>();
        }
        return errorLiveData;
    }
}
