package com.example.newsapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.AppNews
import com.example.newsapp.model.Articles
import com.example.newsapp.repository.NewsRepository


class NewsViewModel : ViewModel() {

    var page = 0
    var articles = MutableLiveData<MutableList<Articles>>()
    var db = AppNews().getInstance().getDatabase()

    fun fetchEverything(query: String) {
        page += 1
        articles = NewsRepository().fetchEverything(query, page)!!
    }

    fun insertNews(articles: MutableList<Articles>) {
        db.articlesDao().insertAll(articles)
    }

    fun getNews(): MutableList<Articles> {
        return db.articlesDao().getAll()
    }

    fun fetchTopHeadlines(page: Int) {
        articles = NewsRepository().fetchTopHeadlines(page)!!
    }

}