package com.example.newsapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.newsapp.db.Database
import com.example.newsapp.di.newsModule
import com.example.newsapp.network.NewsApi
import com.example.newsapp.network.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppNews : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@AppNews)
        modules(newsModule)}
    }

}