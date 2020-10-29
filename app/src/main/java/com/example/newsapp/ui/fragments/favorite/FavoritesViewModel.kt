package com.example.newsapp.ui.fragments.favorite

import androidx.lifecycle.MutableLiveData
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.model.Articles
import com.example.newsapp.repository.NewsRepository

class FavoritesViewModel(private val repository: NewsRepository) : BaseViewModel() {
    var getDataBase = MutableLiveData<List<Articles>>()

    suspend fun remove(articles: Articles) {
        repository.newsData.newsDao().delete(articles)
    }

    fun getFavorites() {
        getDataBase.value = repository.fetchFavorites().value
    }
}