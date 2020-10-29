package com.example.newsapp.ui.fragments.top

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.base.BaseFragment
import com.example.newsapp.extension.PaginationListener
import com.example.newsapp.model.Articles
import com.example.newsapp.network.Resource
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_top.*
import org.koin.android.ext.android.inject

class TopFragment : BaseFragment<TopViewModel>(R.layout.fragment_top) {

    override val viewModel by inject <TopViewModel>()
    private lateinit var adapterTop: NewsAdapter
    private val linearManager = LinearLayoutManager(activity?.parent)
    private var listTop : MutableList<Articles> = mutableListOf()

    override fun initial() {
        recyclerView()
        detailStart()
        setupScroll()
        getData()
        viewModel.getNews()
    }

    private fun getData() {
        viewModel.getDataBase.observe(viewLifecycleOwner, Observer {
            adapterTop.update(it as MutableList<Articles>)
        })
    }

    override fun observe() {
        subscribeTop()
    }

    private fun recyclerView() {
        adapterTop = NewsAdapter(listTop)
        topRWFr.apply {
            layoutManager = LinearLayoutManager(activity?.parent)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = adapterTop
        }
    }

    private fun subscribeTop() {
        viewModel.getNews().observe(viewLifecycleOwner, Observer {
            val articles = it.data?.articles
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (articles != null) {
                        adapterTop.update(articles)
                    }
                }
            }
        })
    }

    private fun setupScroll() {
        topRWFr?.addOnScrollListener(object : PaginationListener(linearManager) {
            override fun isLastPage(): Boolean {
                return viewModel.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMoreItems() {
                viewModel.isLoading = true
                subscribeTop()
            }
        })
    }

    private fun updateAdapter(list: MutableList<Articles>) {
        list?.let {
            adapterTop.update(it)
        }
    }

    private fun detailStart() {
        adapterTop.setOnClick(object : NewsAdapter.OnItemClickListener {
            override fun onClickListener(article: Articles) {
                DetailNewsActivity.instanceActivity(activity, article)
            }
        })
    }

}