package com.example.newsapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.Articles
import com.example.newsapp.repository.NewsRepository


class NewsViewModel : ViewModel() {


    var isLastPage = false
    var page = 0

    fun fetchEverything(query: String): MutableLiveData<MutableList<Articles>?> {
        page += 1
        return NewsRepository().fetchEverything(query, page)
    }

    fun fetchTopHeadlines(): MutableLiveData<MutableList<Articles>?>  {
        return NewsRepository().fetchTopHeadlines()
    }

}