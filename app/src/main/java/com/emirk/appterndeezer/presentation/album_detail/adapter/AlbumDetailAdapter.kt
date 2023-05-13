package com.emirk.appterndeezer.presentation.album_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.emirk.appterndeezer.databinding.ItemAlbumDetailBinding
import com.emirk.appterndeezer.domain.ui_model.Track

class AlbumDetailAdapter(
    private val albumDetailItemClickListener: AlbumDetailItemClickListener
) : ListAdapter<Track, AlbumDetailViewHolder>(diffUtil) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val binding = ItemAlbumDetailBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumDetailViewHolder(binding, albumDetailItemClickListener)
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}