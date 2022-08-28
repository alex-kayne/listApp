package com.example.listapp.di.module;

import com.example.listapp.model.BooksRepository;
import com.example.listapp.model.DBHelper;
import com.example.listapp.model.retrofit.RetrofitServices;
import com.example.listapp.model.source.BooksDao;
import com.example.listapp.model.source.RemoteBooksSource;
import com.example.listapp.presentation.BooksPresenter;
import com.example.listapp.presentation.BooksPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BooksModule {
    @Provides
    @Singleton
    public BooksDao provideBooksDao(DBHelper dbh) {
        return new BooksDao(dbh);
    }

    @Provides
    @Singleton
    public RemoteBooksSource provideRemoteBooksSource(RetrofitServices service) {
        return new RemoteBooksSource(service);
    }

    @Provides
    @Singleton
    public BooksRepository provideBooksRepository(
            BooksDao booksDao,
            RemoteBooksSource remoteBooksSource
    ) {
        return new BooksRepository(booksDao, remoteBooksSource);
    }

    @Provides
    @Singleton
    public BooksPresenter providePresenter(BooksRepository booksRepository) {
        return new BooksPresenterImpl(booksRepository);
    }
}