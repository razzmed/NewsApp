package com.example.newsapp.di

import com.example.newsapp.network.RetrofitClient
import com.example.newsapp.repository.NewsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var newsModule = module {
    single { RetrofitClient() }

    factory { NewsRepository() }

}