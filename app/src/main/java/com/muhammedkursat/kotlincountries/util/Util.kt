package com.muhammedkursat.kotlincountries.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muhammedkursat.kotlincountries.R

fun ImageView.downloadImagesFromUrl(url: String?,progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .error(R.mipmap.ic_launcher_round)
        .placeholder(progressDrawable)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
fun placeHolderProgressBar(context: Context) :CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}