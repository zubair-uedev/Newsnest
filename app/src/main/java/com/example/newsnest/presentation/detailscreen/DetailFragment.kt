package com.example.newsnest.presentation.detailscreen
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsnest.databinding.FragmentDetailBinding
import com.example.newsnest.domain.model.SaveArticle
import com.example.newsnest.presentation.homescreen.savescreen.SaveViewModel
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!
    private val viewModel: SaveViewModel by viewModels()
    private val navArgs: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = navArgs.article
        binding.detailTitle.text = article.title
        binding.detailDescription.text = article.description
        binding.detailContent.text = article.content
        Glide.with(this)
            .load(article.urlToImage)
            .into(binding.detailNewsImage)
        binding.btnSaveNews.setOnClickListener {
            val saveArticle = SaveArticle(
                url = article.url,
                urlToImage = article.urlToImage,
                title = article.title,
                description = article.description,
                content = article.content,
                publishedAt = article.publishedAt
            )

            viewModel.saveNews(saveArticle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}