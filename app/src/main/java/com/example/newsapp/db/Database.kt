package com.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.model.Articles

@Database(entities =[Articles::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase(){
    abstract fun articlesDao(): Dao
}