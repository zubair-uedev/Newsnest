package com.example.newsnest.presentation.onboardingscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsnest.databinding.FragmentOnboardingBinding
import com.example.newsnest.databinding.FragmentSecondOnBoardingBinding

class econdOnBoardingFragment : Fragment() {
    private var _binding: FragmentSecondOnBoardingBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textNext.setOnClickListener {
            val parentBinding =
                FragmentOnboardingBinding.bind(requireParentFragment().requireView())
            parentBinding.viewPager.currentItem = parentBinding.viewPager.currentItem + 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}