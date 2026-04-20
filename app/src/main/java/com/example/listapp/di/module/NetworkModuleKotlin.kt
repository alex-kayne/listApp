//package com.example.listapp.di.module
//
//import com.example.listapp.model.retrofit.RetrofitClient
//import com.example.listapp.model.retrofit.RetrofitServices
//import dagger.Module
//import dagger.Provides
//
//@Module
//class NetworkModule {
//
//    @Provides
//    fun provideRetrofitServices(): RetrofitServices {
//        return RetrofitClient
//            .getClient(BASE_URL)
//            .create(RetrofitServices::class.java)
//    }
//
//    private companion object {
//        const val BASE_URL = "https://pastebin.com/raw/"
//    }
//}