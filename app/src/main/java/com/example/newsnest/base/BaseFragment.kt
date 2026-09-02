package com.example.newsnest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<vb : ViewBinding, vm : ViewModel>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> vb
) : Fragment() {
    private var _binding: vb? = null
    val binding get() = _binding!!
    protected abstract val viewModel: vm
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        bindObserver()
    }

    open fun setupView() {}
    open fun bindObserver() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}