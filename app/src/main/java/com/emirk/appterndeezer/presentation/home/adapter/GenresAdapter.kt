package com.emirk.appterndeezer.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.emirk.appterndeezer.databinding.ItemCategoryBinding
import com.emirk.appterndeezer.domain.ui_model.Genre

class GenresAdapter(
    private val genresClickListener: GenresClickListener
) : ListAdapter<Genre, GenresViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(
                oldItem: Genre,
                newItem: Genre
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Genre,
                newItem: Genre
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val binding = ItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return GenresViewHolder(binding, genresClickListener)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}