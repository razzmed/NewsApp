package com.example.newsapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.newsapp.AppNews
import com.example.newsapp.Constants
import com.example.newsapp.model.Articles
import com.example.newsapp.model.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    private val defaultSize = 10

    private val ru = "ru"

    fun fetchEverything(query: String?, page: Int): MutableLiveData<MutableList<Articles>>? {
        var data: MutableLiveData<MutableList<Articles>>? = MutableLiveData()
        AppNews().provideNews().fetchEverything(query, Constants.apiKey, page, defaultSize)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    //404 - не найдено, 401 - нет доступа, 403 - токен истек
                    data?.value = response.body()?.articles
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //500.. и выше
                data = null
            }
        })
        return data
    }
    fun fetchTopHeadlines(page: Int): MutableLiveData<MutableList<Articles>>? {
        var dataTop: MutableLiveData<MutableList<Articles>>? = MutableLiveData()
        AppNews().provideNews().fetchTopHeadlines(ru, Constants.apiKey)
            .enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                dataTop?.value = response.body()?.articles
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //500.. и выше
                dataTop = null
            }

        })
        return dataTop
    }

}