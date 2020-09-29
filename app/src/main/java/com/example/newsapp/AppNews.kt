package com.example.newsapp

import android.app.Application
import com.example.newsapp.network.NewsApi
import com.example.newsapp.network.RetrofitClient

class AppNews : Application() {

    val retrofitClient = RetrofitClient()
    fun provideNews() = retrofitClient.provideRetrofit.create(NewsApi::class.java)

    override fun onCreate() {
        super.onCreate()
        provideNews()
    }
}