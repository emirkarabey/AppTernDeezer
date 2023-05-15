package com.emirk.appterndeezer.presentation.album_detail.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.appterndeezer.R
import com.emirk.appterndeezer.databinding.ItemAlbumDetailBinding
import com.emirk.appterndeezer.domain.ui_model.Track

class AlbumDetailViewHolder(
    private val binding: ItemAlbumDetailBinding,
    private val albumDetailItemClickListener: AlbumDetailItemClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(track: Track, position: Int) = binding.apply {

        Glide.with(binding.ivAlbum)
            .load(track.trackImage)
            .into(binding.ivAlbum)

        tvSongName.text = track.title
        val duration = track.duration.toFloat() / 60
        tvDuration.text = String.format("%.2f", duration)

        itemView.setOnClickListener {
            albumDetailItemClickListener.onItemClick(track)
        }
        Log.v("isFav", track.isFav.toString())
        if (track.isFav == true) {
            btnFav.setBackgroundResource(R.drawable.fav)
        } else {
            btnFav.setBackgroundResource(R.drawable.fav_border)
        }

        btnFav.setOnClickListener {
            if (it.background.current.equals(R.drawable.fav_border)) {
                it.setBackgroundResource(R.drawable.fav)
            } else {
                it.setBackgroundResource(R.drawable.fav_border)
            }
            albumDetailItemClickListener.onFavItemClick(track, position)
        }
    }
}