package com.example.newsnest.presentation.detailscreen

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newsnest.R
import com.example.newsnest.core.shared.base.BaseFragment
import com.example.newsnest.databinding.FragmentDetailBinding
import com.example.newsnest.domain.model.SaveArticle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate
) {
    override val viewModel: DetailViewModel by viewModels()

    override fun setupViews() {
        val article = viewModel.article

        // setup toolbar
        val toolbar = binding.toolBarDetail.toolBar
        toolbar.title = "Detail Screen"
        toolbar.setNavigationIcon(R.drawable.arrow_back_)
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        // show details
        article?.let {
            binding.apply {
                detailTitle.text = article.title
                detailDescription.text = article.description
                detailContent.text = article.content
            }

            Glide.with(this)
                .load(article.urlToImage)
                .into(binding.detailNewsImage)

            viewModel.isArticleSaved(article.url) { isSaved ->
                if (isSaved) {
                    binding.btnSaveNews.setColorFilter(android.graphics.Color.RED)
                } else {
                    binding.btnSaveNews.clearColorFilter()
                }
            }

            // handle save click
            binding.btnSaveNews.setOnClickListener {
                viewModel.isArticleSaved(article.url) { isSaved ->
                    if (isSaved) {
                        viewModel.deleteArticleByUrl(article.url)
                        binding.btnSaveNews.clearColorFilter()
                    } else {
                        val saveArticle = SaveArticle(
                            url = article.url,
                            urlToImage = article.urlToImage,
                            title = article.title,
                            description = article.description,
                            content = article.content,
                            publishedAt = article.publishedAt
                        )
                        viewModel.saveNews(saveArticle)
                        binding.btnSaveNews.setColorFilter(android.graphics.Color.RED)
                    }
                }
            }
        }
    }
}