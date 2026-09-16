package com.example.newsnest.presentation.homescreen.newsscreen
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsnest.R
import com.example.newsnest.core.shared.base.BaseFragment
import com.example.newsnest.core.shared.utils.NewsCategories
import com.example.newsnest.databinding.FragmentHomeNews2Binding
import com.example.newsnest.core.extensions.launchAndRepeatWithViewLifecycle
import com.example.newsnest.presentation.homescreen.newsscreen.adapter.CategoryAdapter
import com.example.newsnest.presentation.homescreen.newsscreen.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
@AndroidEntryPoint
class HomeNewsFragment : BaseFragment<FragmentHomeNews2Binding, HomeNewsViewModel>(
    FragmentHomeNews2Binding::inflate
) {
    override val viewModel: HomeNewsViewModel by viewModels()
    private var newsAdapter = NewsAdapter(
        onNewsClick = { article ->
            viewModel.onNewsCLickArticle(article = article)
        },
        onSaveClick = { article ->
            // Room Database me save karenge
        }
    )
    private var categoryAdapter = CategoryAdapter(NewsCategories.list) { selectedCategory ->
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getHeadLines(category = selectedCategory.apiCategory)
    }

    override fun setupViews() {
        val toolbar = binding.toolbarInclude.toolBar
        toolbar.title = getString(R.string.newsnestFirst)
        toolbar.navigationIcon = null
        toolbar.inflateMenu(R.menu.toolbar_menu)
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> {
                    findNavController().navigate(R.id.action_homeNewsFragment_to_searchFragment)
                    true
                }

                else -> false
            }
        }
        setupCategoryRecyclerView()
        stepRecyclerView()
        viewModel.getHeadLines(category = null)
    }

    override fun observeData() {
        launchAndRepeatWithViewLifecycle {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.newResponseData.collect { response ->
                response?.let {
                    newsAdapter.submitList(it.articles)
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.newsMessage.collectLatest {
                when (it) {
                    is NewsDashBoard.NavigateToDetailScreen -> {
                        val action = HomeNewsFragmentDirections.actionHomeNewsFragmentToDetailFragment(it.article)
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }

    private fun setupCategoryRecyclerView() {
        binding.recyclerViewCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }

    private fun stepRecyclerView() {
        binding.recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }
}