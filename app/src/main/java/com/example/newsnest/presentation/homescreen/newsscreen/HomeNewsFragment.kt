package com.example.newsnest.presentation.homescreen.newsscreen
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsnest.databinding.FragmentHomeNews2Binding
import com.example.newsnest.presentation.homescreen.newsscreen.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeNewsFragment : Fragment() {
    private var _binding: FragmentHomeNews2Binding? = null
    val binding get() = _binding!!
    private val viewModel: HomeNewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // R.layout.fragment_home_news2
        _binding = FragmentHomeNews2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stepRecyclerView()
        observeNews()
        viewModel.getHeadLines()
        Log.d("TAG", "onViewCreated: ${viewModel.getHeadLines().toString()}")
    }

    private fun stepRecyclerView() {
        newsAdapter = NewsAdapter(
            onNewsClick = { article ->
               val action = HomeNewsFragmentDirections.actionHomeNewsFragmentToDetailFragment(article)
                findNavController().navigate(action)
            },
            onSaveClick = { article ->
                // Next step:
                // Room Database me save karenge
            }
        )
        binding.recyclerViewNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun observeNews() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.newResponseData.collect { response ->
                response?.let { it ->
                    newsAdapter.submitList(it.articles)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}