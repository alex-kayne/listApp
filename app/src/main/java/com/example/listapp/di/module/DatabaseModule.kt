package com.example.listapp.di.module

import android.content.Context
import com.example.listapp.model.DBHelper

class DatabaseModule {

    fun provideDatabase(context: Context): DBHelper {
        return DBHelper(context)
    }
}