package com.emirk.appterndeezer.presentation.favorites.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.appterndeezer.databinding.ItemFavoritesBinding
import com.emirk.appterndeezer.domain.ui_model.Track

class FavoritesViewHolder(
    private val binding: ItemFavoritesBinding,
    private val favoritesItemClickListener: FavoritesItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(track: Track, position: Int) = binding.apply {

        Glide.with(binding.ivAlbum)
            .load(track.trackImage)
            .into(binding.ivAlbum)

        tvSongName.text = track.title
        val duration = track.duration.toFloat() / 60
        tvDuration.text = String.format("%.2f", duration)

        itemView.setOnClickListener {
            favoritesItemClickListener.onItemClick(track.preview)
        }

        btnFav.setOnClickListener {
            favoritesItemClickListener.onFavItemClick(track, position)
        }
    }
}