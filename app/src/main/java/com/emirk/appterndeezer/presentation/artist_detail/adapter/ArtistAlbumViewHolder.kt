package com.emirk.appterndeezer.presentation.artist_detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.appterndeezer.databinding.ItemAlbumBinding
import com.emirk.appterndeezer.domain.ui_model.ArtistAlbum

class ArtistAlbumViewHolder(
    private val binding: ItemAlbumBinding,
    private val artistAlbumItemClickListener: ArtistAlbumItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(artistAlbum: ArtistAlbum) = binding.apply {

        Glide.with(binding.ivAlbum)
            .load(artistAlbum.cover_xl)
            .into(binding.ivAlbum)

        tvAlbumName.text = artistAlbum.title
        tvReleaseDate.text = artistAlbum.release_date

        itemView.setOnClickListener {
            artistAlbumItemClickListener.onItemClick(artistAlbum = artistAlbum)
        }
    }
}