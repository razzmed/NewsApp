package com.example.newsapp.ui.fragments.favorite

import android.util.Log
import androidx.lifecycle.Observer
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.android.ext.android.inject


class FavoritesFragment : BaseFragment<FavoritesViewModel>(R.layout.fragment_favorites) {
    override val viewModel by inject<FavoritesViewModel>()
    lateinit var list: MutableList<Articles>
    lateinit var adapter: NewsAdapter

    override fun initial() {
        val bundle = this.arguments
        if (bundle != null) {
            Log.e("TAG", "initial: " + bundle.getSerializable("article_key"))
            list.add(bundle.getSerializable("article_key") as Articles)
        }
        viewModel.getFavorites()
        viewModel.getDataBase.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", "initial: $it")
            list = it as MutableList<Articles>
        })
        initrecycler()
    }

    private fun initrecycler() {
        favRWFr.adapter = NewsAdapter(list)
        adapter.update(list)
    }

    override fun observe() {

    }
}