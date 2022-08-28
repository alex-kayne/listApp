package com.example.listapp.di.module;

import android.content.Context;

import com.example.listapp.model.DBHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public DBHelper provideDatabase(Context context) {
        return new DBHelper(context);
    }
}