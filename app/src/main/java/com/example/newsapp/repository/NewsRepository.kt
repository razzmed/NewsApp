package com.example.newsapp.repository

import com.example.newsapp.AppNews
import com.example.newsapp.Constants
import com.example.newsapp.model.Articles
import com.example.newsapp.model.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsRepository {

    private val everythingDefaultSize = 10
    fun fetchEverything(query: String?): MutableList<Articles>? {
        var data: MutableList<Articles>? = mutableListOf()
        AppNews().provideNews().fetchEverything(query, Constants.apiKey, everythingDefaultSize).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //500.. и выше
                data = null
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                //404 - не найдено, 401 - нет доступа, 403 - токен истек
                data = response.body()?.articles
            }
        })
        return data
    }


}