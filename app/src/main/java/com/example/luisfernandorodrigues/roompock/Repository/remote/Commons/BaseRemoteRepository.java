package com.example.luisfernandorodrigues.roompock.Repository.remote.Commons;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRemoteRepository<T> {

    private static String BASE_URL = "https://1d4b1a8b-6b2d-4386-81b8-0bae13ae004d.mock.pstmn.io/";
    public static String APIKEY = "aa722fc8e5494d67920b1cee53cfdcac";
    private Retrofit retrofit;
    private Class<T> typeService;


    public BaseRemoteRepository(Class<T> typeService) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.typeService = typeService;

    }

    public T getService() {
        return  retrofit.create(typeService);
    }
}
