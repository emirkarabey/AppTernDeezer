package com.emirk.appterndeezer.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.appterndeezer.databinding.ItemCategoryBinding
import com.emirk.appterndeezer.domain.ui_model.Genre

class GenresViewHolder(
    private val binding: ItemCategoryBinding,
    private val genresClickListener: GenresClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: Genre) = binding.apply {

        Glide.with(binding.ivCategory)
            .load(genre.picture_xl)
            .into(binding.ivCategory)

        tvCategoryName.text = genre.name

        itemView.setOnClickListener {
            genresClickListener.onItemClick(genre = genre)
        }
    }
}