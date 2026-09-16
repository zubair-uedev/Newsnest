package com.example.newsnest.presentation.homescreen.newsscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsnest.databinding.ItemNewsBinding
import com.example.newsnest.domain.model.Article

class NewsAdapter(
    private val onNewsClick: (Article) -> Unit,
    private val onSaveClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val newsList = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(
            newsList[position]
        )
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                tvTitle.text = article.title
                tvDescription.text = article.description ?: ""
                Glide.with(root.context)
                    .load(article.urlToImage)
                    .into(newsImg)
                root.setOnClickListener {
                    onNewsClick(article)
                }
                newsSave.setOnClickListener {
                    onSaveClick(article)
                }
            }
        }
    }

    fun submitList(list: List<Article>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

}