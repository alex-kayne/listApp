package com.example.listapp.di;

import com.example.listapp.MainActivity;
import com.example.listapp.di.module.BooksModule;
import com.example.listapp.di.module.ContextModule;
import com.example.listapp.di.module.DatabaseModule;
import com.example.listapp.di.module.NetworkModule;
import com.example.listapp.presentation.BooksPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        BooksModule.class,
        ContextModule.class,
        DatabaseModule.class,
        NetworkModule.class
})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
    BooksPresenter providePresenter();
}
