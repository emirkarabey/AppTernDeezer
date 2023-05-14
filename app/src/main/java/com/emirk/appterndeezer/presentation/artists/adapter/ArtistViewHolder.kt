package com.emirk.appterndeezer.presentation.artists.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.appterndeezer.databinding.ItemArtistBinding
import com.emirk.appterndeezer.domain.ui_model.Artist

class ArtistViewHolder(
    private val binding: ItemArtistBinding,
    private val artistClickListener: ArtistClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(artist: Artist) = binding.apply {

        Glide.with(binding.ivArtist)
            .load(artist.picture_xl)
            .into(binding.ivArtist)

        tvArtistName.text = artist.name

        itemView.setOnClickListener {
            artistClickListener.onItemClick(artist = artist)
        }
    }
}