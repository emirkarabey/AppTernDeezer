package com.emirk.appterndeezer.presentation.artist_detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.appterndeezer.databinding.ItemAlbumBinding
import com.emirk.appterndeezer.domain.ui_model.ArtistAlbum

class ArtistAlbumViewHolder(
    private val binding: ItemAlbumBinding,
    private val artistAlbumItemClickListener: ArtistAlbumItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(artist: ArtistAlbum) = binding.apply {

        Glide.with(binding.ivAlbum)
            .load(artist.cover_xl)
            .into(binding.ivAlbum)

        tvAlbumName.text = artist.title
        tvReleaseDate.text = artist.release_date

        itemView.setOnClickListener {
            artistAlbumItemClickListener.onItemClick(artist.id)
        }
    }
}