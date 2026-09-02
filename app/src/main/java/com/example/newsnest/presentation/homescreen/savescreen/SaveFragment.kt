package com.example.newsnest.presentation.homescreen.savescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsnest.databinding.FragmentSaveBinding
import com.example.newsnest.presentation.homescreen.savescreen.saveadapter.SaveNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveFragment : Fragment() {
    private var _binding: FragmentSaveBinding? = null
    val binding get() = _binding!!

    private val viewModel: SaveViewModel by viewModels()
    private lateinit var saveNewsAdapter: SaveNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observer()
    }

    private fun setupRecyclerView() {
        saveNewsAdapter = SaveNewsAdapter(
            onNewsClick = {

            },
            onDeleteClick = { it ->
                it.url?.let { it ->
                    viewModel.deleteArticleByUrl(it)
                }
            }
        )
        binding.recyclerViewSaveNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = saveNewsAdapter
        }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.saveNews.collect { it ->
                saveNewsAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}