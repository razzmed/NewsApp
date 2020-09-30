package com.example.newsapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.model.Articles
import com.example.newsapp.repository.NewsRepository


class NewsViewModel : ViewModel() {

    var articles = MutableLiveData<MutableList<Articles>>()

    fun fetchEverything(query: String) {
        articles = NewsRepository().fetchEverything(query)!!
    }

}