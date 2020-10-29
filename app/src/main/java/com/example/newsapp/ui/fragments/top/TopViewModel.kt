package com.example.newsapp.ui.fragments.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.model.Articles
import com.example.newsapp.model.ResponseBody
import com.example.newsapp.network.Resource
import com.example.newsapp.repository.NewsRepository

class TopViewModel(private val repository: NewsRepository) : BaseViewModel() {

    var page = 0
    var isLastPage = false
    var isLoading = false
    var getDataBase = MutableLiveData<List<Articles>>()
    var isPagination = MutableLiveData<Boolean>()

    fun getNews(): LiveData<Resource<ResponseBody>> {
        page += 1
        return repository.fetchTopHeadlines()
    }

    fun fetchFav() {
        getDataBase.value = repository.fetchFavorites().value
    }
}