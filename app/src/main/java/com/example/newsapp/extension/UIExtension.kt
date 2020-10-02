package com.example.newsapp.extension

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showToast(stringFromResource: Int) {
    val message = getString(stringFromResource)
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ImageView.loadImage(context: Context, url: String?, placeholder: Int = 0) {
    Glide.with(context)
        .asBitmap()
        .placeholder(placeholder)
        .load(url)
        .into(this)
}