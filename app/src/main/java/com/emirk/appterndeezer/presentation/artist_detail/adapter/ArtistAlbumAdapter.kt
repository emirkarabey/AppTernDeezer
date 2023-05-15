package com.emirk.appterndeezer.presentation.artist_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.emirk.appterndeezer.databinding.ItemAlbumBinding
import com.emirk.appterndeezer.domain.ui_model.ArtistAlbum

class ArtistAlbumAdapter(
    private val artistAlbumItemClickListener: ArtistAlbumItemClickListener
) : ListAdapter<ArtistAlbum, ArtistAlbumViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ArtistAlbum>() {
            override fun areItemsTheSame(
                oldItem: ArtistAlbum,
                newItem: ArtistAlbum
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ArtistAlbum,
                newItem: ArtistAlbum
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumViewHolder {
        val binding = ItemAlbumBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistAlbumViewHolder(binding, artistAlbumItemClickListener)
    }

    override fun onBindViewHolder(holder: ArtistAlbumViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}