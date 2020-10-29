package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.newsapp.Constants
import com.example.newsapp.db.NewsData
import com.example.newsapp.model.Articles
import com.example.newsapp.network.NewsApi
import com.example.newsapp.network.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsRepository(val api: NewsApi, val newsData: NewsData) {

    private val defaultSize = 10

    private val ru = "ru"

    fun fetchEverything(query: String?, page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val result = api.fetchEverything(query, Constants.apiKey, defaultSize, page)
            newsData.newsDao().insertAll(result.articles)
            emit(Resource.success(data = result))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error everything!"))
        }
    }

    fun fetchTopHeadlines() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = api.fetchTopHeadlines(
                        ru,
                        Constants.apiKey,
                        defaultSize,
                        1
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Top!"))
        }
    }

    fun insertNewNews(article: Articles) {
        CoroutineScope(Dispatchers.IO).launch {
            newsData.newsDao().insert(article)
        }
    }

    fun fetchFavorites(): LiveData<List<Articles>> = liveData(Dispatchers.IO) {
        emit(newsData.newsDao().fetchFavoriteArticles())
    }

//    fun fetchEverything(query: String?, page: Int): MutableLiveData<MutableList<Articles>>? {
//        var data: MutableLiveData<MutableList<Articles>>? = MutableLiveData()
//        AppNews().provideNews().fetchEverything(query, Constants.apiKey, page, defaultSize)
//            .enqueue(object : Callback<ResponseBody> {
//                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                    //404 - не найдено, 401 - нет доступа, 403 - токен истек
//                    data?.value = response.body()?.articles
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                //500.. и выше
//                data = null
//            }
//        })
//        return data
//    }
//    fun fetchTopHeadlines(page: Int): MutableLiveData<MutableList<Articles>>? {
//        var dataTop: MutableLiveData<MutableList<Articles>>? = MutableLiveData()
//        AppNews().provideNews().fetchTopHeadlines(ru, Constants.apiKey)
//            .enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                //404 - не найдено, 401 - нет доступа, 403 - токен истек
//                dataTop?.value = response.body()?.articles
//            }
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                //500.. и выше
//                dataTop = null
//            }
//
//        })
//        return dataTop
//    }

}