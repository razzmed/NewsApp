package com.example.newsapp.ui.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.extension.loadImage
import com.example.newsapp.model.Articles
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.*

class NewsAdapter(private var list: MutableList<Articles>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var onNewsClickListener: OnItemClickListener
    public lateinit var onLikeclick: Likeclick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position], onLikeclick)
        holder.itemView.setOnClickListener {
            onNewsClickListener.onClickListener(list[position])
        }
    }

    fun update(list: MutableList<Articles>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnClick(onItemClickListener: OnItemClickListener) {
        this.onNewsClickListener = onItemClickListener
    }

    fun setOnFavClick(onLikeclick: Likeclick) {
        this.onLikeclick = onLikeclick
    }

    override fun getItemCount(): Int = list.size

    class NewsViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(
            article: Articles,
            onLikeclick: Likeclick
        ) {
            favIcon.setOnClickListener {
                if (article.isFavorite) {
                    favIcon.setImageResource(R.drawable.ic_favorite)
                    article.isFavorite = false

                } else {
                    favIcon.setImageResource(R.drawable.ic_favorite_red)
                    article.isFavorite = true
                }
                onLikeclick.onLikeClick(article)
            }
            title_text.text = article.title
            description_text.text = article.description
            if (article.urlToImage != null) {
                image.loadImage(context = image.context, url = article.urlToImage)
            } else {
                image.setImageResource(R.drawable.ic_image)
            }
        }
    }

    interface OnItemClickListener {
        fun onClickListener(article: Articles)
    }

    interface Likeclick {
        fun onLikeClick(article: Articles)
    }
}
