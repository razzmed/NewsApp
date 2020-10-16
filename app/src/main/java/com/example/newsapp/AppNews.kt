package com.example.newsapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.newsapp.db.Database
import com.example.newsapp.network.NewsApi
import com.example.newsapp.network.RetrofitClient

class AppNews : Application() {

    companion object {
        lateinit var database: Database
        lateinit var instance: AppNews
    }

    val retrofitClient = RetrofitClient()
    fun provideNews() = retrofitClient.provideRetrofit.create(NewsApi::class.java)

    override fun onCreate() {
        super.onCreate()
        instance = this
        provideNews()
        database = Room.databaseBuilder(this, Database::class.java, "NewsDatabase")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun getInstance(): AppNews {
        return instance
    }

    fun getDatabase(): Database {
        return database
    }


}