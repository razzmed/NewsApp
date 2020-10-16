package com.example.newsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.Articles
@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: MutableList<Articles>)

    @Query("SELECT * FROM articles")
    fun getAll(): MutableList<Articles>
}