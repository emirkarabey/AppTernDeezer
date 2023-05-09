package com.emirk.appterndeezer.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emirk.appterndeezer.databinding.ItemCategoryBinding
import com.emirk.appterndeezer.domain.ui_model.Category

class CategoryViewHolder(
    private val binding: ItemCategoryBinding,
    private val categoryClickListener: CategoryClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) = binding.apply {

        Glide.with(binding.ivCategory)
            .load(category.picture_xl)
            .into(binding.ivCategory)

        tvCategoryName.text = category.name

        itemView.setOnClickListener {
            categoryClickListener.onItemClick(category.id)
        }
    }
}