package com.example.listapp.di.module

import android.content.Context

class ContextModule(private val context: Context) {

    fun provideContext(): Context {
        return context
    }
}