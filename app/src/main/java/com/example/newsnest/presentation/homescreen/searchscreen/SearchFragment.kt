package com.example.newsnest.presentation.homescreen.searchscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsnest.R
import com.example.newsnest.databinding.FragmentSearchBinding
import com.example.newsnest.presentation.homescreen.newsscreen.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observerNews()
        searchNews()

        searchViewModel.loadDefaultNews()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(
            onNewsClick = { article ->
                findNavController().navigate(R.id.action_searchFragment_to_detailFragment)
            },
            onSaveClick = { article ->

            }

        )
        binding.recyclerViewSearch.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun observerNews() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.searchState.collect { response ->
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
                searchViewModel.searchNews(query = query)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}