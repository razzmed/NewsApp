package com.example.newsapp.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.extension.showToast
import com.example.newsapp.model.Articles
import com.example.newsapp.ui.detail_news.DetailNewsActivity
import com.example.newsapp.ui.fragments.everything.EverythingFragment
import com.example.newsapp.ui.fragments.favorite.FavoritesFragment
import com.example.newsapp.ui.fragments.top.TopFragment
import com.example.newsapp.ui.news.adapter.NewsAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val everything = EverythingFragment()
        val top = TopFragment()
        val favorites = FavoritesFragment()

        makeCurrentFragment(everything)

        btmNavView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menuEverything -> makeCurrentFragment(everything)
                R.id.menuTop -> makeCurrentFragment(top)
                R.id.menuFavorites -> makeCurrentFragment(favorites)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) : FragmentTransaction = supportFragmentManager.beginTransaction()
        .apply{
            replace(R.id.navHost, fragment)
            commit()

    }


}