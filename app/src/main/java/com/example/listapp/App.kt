package com.example.listapp

import android.app.Application
import android.content.Context
import com.example.listapp.di.AppComponent
import com.example.listapp.di.DaggerAppComponent
import com.example.listapp.di.module.ContextModule

fun Context.component(): AppComponent {
    return App.app(this).getComponent()
}

class App : Application() {
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }

    fun getComponent(): AppComponent = appComponent

    companion object {
        fun app(context: Context): App {
            return context.applicationContext as App
        }
    }
}