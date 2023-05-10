package com.emirk.appterndeezer.util.BindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(pictureXl: String?) {
    if (!pictureXl.isNullOrEmpty()) {
        Glide.with(this)
            .load(pictureXl)
            .into(this)
    }
}
