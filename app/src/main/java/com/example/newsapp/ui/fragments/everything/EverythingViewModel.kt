package com.example.newsapp.ui.fragments.everything

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.model.Articles
import com.example.newsapp.model.ResponseBody
import com.example.newsapp.network.Resource
import com.example.newsapp.repository.NewsRepository

class EverythingViewModel(private val repository: NewsRepository) : BaseViewModel() {

    var isLastPage = false
    var isLoading = false
    var page = 0
    var getDataBase = MutableLiveData<List<Articles>>()
    var articles = MutableLiveData<ResponseBody>()

    fun fetchEverything(query: String): LiveData<Resource<ResponseBody>> {
        page += 1
        return repository.fetchEverything("bitcoin", page)
    }

    fun insertFavorite(articles: Articles) {
        repository.insertNewNews(articles)
    }
}