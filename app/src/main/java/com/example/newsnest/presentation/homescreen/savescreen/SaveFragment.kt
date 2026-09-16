package com.example.newsnest.presentation.homescreen.savescreen
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsnest.core.shared.base.BaseFragment
import com.example.newsnest.databinding.FragmentSaveBinding
import com.example.newsnest.core.extensions.launchAndRepeatWithViewLifecycle
import com.example.newsnest.presentation.homescreen.savescreen.saveadapter.SaveNewsAdapter
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class SaveFragment : BaseFragment<FragmentSaveBinding, SaveViewModel>(
    FragmentSaveBinding::inflate
) {
    override val viewModel: SaveViewModel by viewModels()
    private lateinit var saveNewsAdapter: SaveNewsAdapter
    override fun setupViews() {
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
    override fun observeData() {
        launchAndRepeatWithViewLifecycle {
            viewModel.saveNews.collect { it ->
                saveNewsAdapter.submitList(it)
            }
        }
    }
}