package com.example.newsnest.presentation.homescreen.newsscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsnest.R
import com.example.newsnest.databinding.ItemCategoryBinding
import com.example.newsnest.domain.model.NewsCategory

class CategoryAdapter(
    private val categoryList: List<NewsCategory>,
    private val onCategoryClick: (NewsCategory) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.binding.tvCategoryName.text = category.name
        if (position == selectedPosition) {
            holder.binding.categoryCard.setCardBackgroundColor(
                holder.itemView.context.getColor(R.color.category_selected)
            )
            holder.binding.tvCategoryName.setTextColor(
                holder.itemView.context.getColor(R.color.text_selected)
            )
        } else {
            holder.binding.categoryCard.setCardBackgroundColor(
                holder.itemView.context.getColor(R.color.category_unselected)
            )
            holder.binding.tvCategoryName.setTextColor(
                holder.itemView.context.getColor(R.color.text_unselected)
            )
        }
        holder.binding.categoryCard.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            onCategoryClick(category)
        }
    }

    class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return categoryList.size
    }
}