package com.emirk.appterndeezer.presentation.artists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.emirk.appterndeezer.databinding.ItemArtistBinding
import com.emirk.appterndeezer.domain.ui_model.Artist

class ArtistAdapter(
    private val artistClickListener: ArtistClickListener
) : ListAdapter<Artist, ArtistViewHolder>(diffUtil) {

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Artist>() {
            override fun areItemsTheSame(
                oldItem: Artist,
                newItem: Artist
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Artist,
                newItem: Artist
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ItemArtistBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding, artistClickListener)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}