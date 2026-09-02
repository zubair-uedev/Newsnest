package com.example.newsnest.presentation.homescreen.savescreen.saveadapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsnest.databinding.ItemNewsBinding
import com.example.newsnest.domain.model.SaveArticle

class SaveNewsAdapter(
    private val onNewsClick: (SaveArticle) -> Unit,
    private val onDeleteClick: (SaveArticle) -> Unit
) : RecyclerView.Adapter<SaveNewsAdapter.SaveNewsViewHolder>() {
    private val savedNewsList = mutableListOf<SaveArticle>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveNewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaveNewsViewHolder(binding)
    }
    inner class SaveNewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: SaveArticle) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            Glide.with(binding.root.context)
                .load(article.urlToImage)
                .into(binding.newsImg)
            binding.root.setOnClickListener {
                onNewsClick(article)
            }
            binding.newsSave.setOnClickListener {
                onDeleteClick(article)
            }
        }
    }

    override fun onBindViewHolder(holder: SaveNewsViewHolder, position: Int) {
        holder.bind(savedNewsList[position])
    }

    override fun getItemCount(): Int {
        return savedNewsList.size
    }

    fun submitList(list: List<SaveArticle>) {
        savedNewsList.clear()
        savedNewsList.addAll(list)
        notifyDataSetChanged()
    }
}