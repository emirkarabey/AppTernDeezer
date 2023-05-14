package com.emirk.appterndeezer.presentation.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.emirk.appterndeezer.databinding.ItemFavoritesBinding
import com.emirk.appterndeezer.domain.ui_model.Track

class FavoritesAdapter(
    private val favoritesItemClickListener: FavoritesItemClickListener
) : ListAdapter<Track, FavoritesViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(
                oldItem: Track,
                newItem: Track
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Track,
                newItem: Track
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = ItemFavoritesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesViewHolder(binding, favoritesItemClickListener)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, position) }
    }
}