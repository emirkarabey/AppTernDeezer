package com.emirk.appterndeezer.presentation.album_detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.appterndeezer.databinding.ItemAlbumDetailBinding
import com.emirk.appterndeezer.domain.ui_model.Track

class AlbumDetailViewHolder(
    private val binding: ItemAlbumDetailBinding,
    private val albumDetailItemClickListener: AlbumDetailItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(track: Track) = binding.apply {

        Glide.with(binding.ivAlbum)
            .load(track.album.cover_xl)
            .into(binding.ivAlbum)

        tvSongName.text = track.title
        val duration = track.duration.toFloat() / 60
        tvDuration.text = String.format("%.2f", duration)

        itemView.setOnClickListener {
            albumDetailItemClickListener.onItemClick(track.id)
        }
    }
}