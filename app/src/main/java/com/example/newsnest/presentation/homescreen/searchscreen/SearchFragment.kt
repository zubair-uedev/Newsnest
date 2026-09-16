package com.example.newsnest.presentation.homescreen.searchscreen

import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsnest.R
import com.example.newsnest.core.shared.base.BaseFragment
import com.example.newsnest.databinding.FragmentSearchBinding
import com.example.newsnest.core.extensions.launchAndRepeatWithViewLifecycle
import com.example.newsnest.presentation.homescreen.newsscreen.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(FragmentSearchBinding::inflate) {
    override val viewModel: SearchViewModel by viewModels()
    private var newsAdapter = NewsAdapter(
        onNewsClick = { article -> findNavController().navigate(R.id.action_searchFragment_to_detailFragment) },
        onSaveClick = { article -> }
    )

    override fun setupViews() {
        setupRecyclerView()
        searchNews()
        viewModel.loadDefaultNews()
    }

    override fun observeData() {
        observerNews()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewSearch.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun observerNews() {
        launchAndRepeatWithViewLifecycle {
            viewModel.searchState.collect { response ->
                response?.let {
                    newsAdapter.submitList(it.articles)
                }
            }
        }
    }

    private fun searchNews() {
        binding.etSearch.doAfterTextChanged {
            val query = it.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchNews(query = query)
            }
        }
    }

}