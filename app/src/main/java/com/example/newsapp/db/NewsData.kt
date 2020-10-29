package com.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.model.Articles

@Database(entities = [Articles::class], version = 1)
abstract class NewsData : RoomDatabase() {
    abstract fun newsDao(): Dao
}