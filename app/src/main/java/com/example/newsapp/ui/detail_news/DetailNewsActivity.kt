package com.example.newsapp.ui.detail_news

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R
import com.example.newsapp.extension.loadImage
import com.example.newsapp.model.Articles
import kotlinx.android.synthetic.main.activity_detail_news.*

class DetailNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)
        setupViews()
    }

    private fun setupViews() {
        imageNews.loadImage(this, item?.urlToImage)
        tvAuthor.text = item?.author
        tvTittle.text = item?.title
        tvDesc.text = item?.description
    }

    companion object {
        private var item: Articles? = null
        fun instanceActivity(activity: Activity?, item: Articles) {
            this.item = item
            val intent = Intent(activity, DetailNewsActivity::class.java)
            activity?.startActivity(intent)
        }
    }
}