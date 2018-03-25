package com.example.luisfernandorodrigues.roompock.Repository.remote.Commons;

import retrofit2.Call;

public interface RequestResponse<T>  {

    void onResponse(T response);
    void onResponseError(Call<T> call, Throwable t);
}
