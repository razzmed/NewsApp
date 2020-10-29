package com.example.newsapp.db

import androidx.room.*
import androidx.room.Dao
import com.example.newsapp.model.Articles

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: MutableList<Articles>)

    @Insert()
    suspend fun insert(articles: Articles)

    @Query("SELECT * FROM articles")
    suspend fun fetchFavoriteArticles(): List<Articles>

    @Query("SELECT * FROM articles")
    fun getAll(): MutableList<Articles>

    @Delete
    suspend fun delete(articles: Articles)
}