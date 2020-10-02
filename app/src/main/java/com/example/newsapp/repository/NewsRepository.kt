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

    fun fetchEverything(query: String?, page: Int?): MutableLiveData<MutableList<Articles>?> {
        val data: MutableLiveData<MutableList<Articles>?> = MutableLiveData()
        AppNews().provideNews().fetchEverything(query, Constants.apiKey, defaultSize, page).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //500.. и выше
                data.value = null
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data.value = response.body()?.articles
            }
        })
        return data
    }
    private val country = "us"
    fun fetchTopHeadlines(): MutableLiveData<MutableList<Articles>?> {
        val data: MutableLiveData<MutableList<Articles>?> = MutableLiveData()
        AppNews().provideNews().fetchTopHeadlines(country, Constants.apiKey).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //500.. и выше
                data.value = null
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data.value = response.body()?.articles
            }
        })
        return data
    }

}