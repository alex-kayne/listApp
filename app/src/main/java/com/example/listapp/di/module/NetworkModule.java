package com.example.listapp.di.module;

import com.example.listapp.model.retrofit.RetrofitClient;
import com.example.listapp.model.retrofit.RetrofitServices;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    public static final String BASE_URL = "https://pastebin.com/raw/";

    @Provides
    public RetrofitServices provideRetrofitServices() {
        return RetrofitClient.INSTANCE
                .getClient(BASE_URL)
                .create(RetrofitServices.class);
    }
}