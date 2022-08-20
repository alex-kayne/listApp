package com.example.listapp.di

import android.content.Context
import com.example.listapp.di.module.BooksModule
import com.example.listapp.di.module.ContextModule
import com.example.listapp.di.module.DatabaseModule
import com.example.listapp.di.module.NetworkModule
import com.example.listapp.model.BooksRepository
import com.example.listapp.presentation.BooksPresenter
import com.example.listapp.presentation.BooksPresenterImpl

class AppComponent(val context: Context) {
    private val booksModule: BooksModule = BooksModule()
    private val contextModule: ContextModule = ContextModule(context)
    private val databaseModule: DatabaseModule = DatabaseModule()
    private val networkModule: NetworkModule = NetworkModule()

    fun providePresenter(): BooksPresenter {
        return booksModule.provideBooksPresenterImpl(
            booksModule.provideBooksRepository(
                booksModule.provideBooksDao(
                    databaseModule.provideDatabase(
                        contextModule.provideContext()
                    )
                ),
                booksModule.provideRemoteBooksSource(networkModule.provideRetrofitServices())
            )
        )
    }

}