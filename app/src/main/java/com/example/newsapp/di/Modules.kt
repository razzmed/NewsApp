package com.example.newsapp.di

import com.example.newsapp.db.Database
import com.example.newsapp.network.RetrofitClient
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.fragments.everything.EverythingViewModel
import com.example.newsapp.ui.fragments.favorite.FavoritesViewModel
import com.example.newsapp.ui.fragments.top.TopViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

var newsModule: Module = module {
    single { RetrofitClient().provideNews() }
    single { Database().getData(androidContext()) }
    factory { NewsRepository(get(), get()) }

    viewModel { EverythingViewModel(get()) }
    viewModel { TopViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }

}