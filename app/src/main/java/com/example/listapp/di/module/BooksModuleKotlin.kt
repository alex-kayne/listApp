//package com.example.listapp.di.module
//
//import com.example.listapp.model.BooksRepository
//import com.example.listapp.model.DBHelper
//import com.example.listapp.model.retrofit.RetrofitServices
//import com.example.listapp.model.source.BooksDao
//import com.example.listapp.model.source.RemoteBooksSource
//import com.example.listapp.presentation.BooksPresenter
//import com.example.listapp.presentation.BooksPresenterImpl
//import dagger.Module
//import dagger.Provides
//
//@Module
//class BooksModule {
//    @Provides
//    fun provideBooksDao(dbh: DBHelper): BooksDao {
//        return BooksDao(dbh)
//    }
//
//    @Provides
//    fun provideRemoteBooksSource(service: RetrofitServices): RemoteBooksSource {
//        return RemoteBooksSource(service)
//    }
//
//    @Provides
//    fun provideBooksPresenterImpl(booksRepository: BooksRepository): BooksPresenter {
//        return BooksPresenterImpl(booksRepository)
//    }
//
//    @Provides
//    fun provideBooksRepository(
//        booksDao: BooksDao,
//        remoteBooksSource: RemoteBooksSource
//    ): BooksRepository {
//        return BooksRepository(booksDao, remoteBooksSource)
//    }
//
//    @Provides
//    fun providePresenter(booksRepository: BooksRepository): BooksPresenter {
//        return BooksPresenterImpl(booksRepository)
//    }
//}