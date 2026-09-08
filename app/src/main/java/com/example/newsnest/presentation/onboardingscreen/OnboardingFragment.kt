package com.example.newsnest.presentation.onboardingscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsnest.databinding.FragmentOnboardingBinding
import com.example.newsnest.presentation.onboardingscreen.adapter.OnBoardingAdapter

class OnboardingFragment : Fragment() {
    private var _binding: FragmentOnboardingBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = listOf<Fragment>(
            firstOnBoardingFragment(),
        )

        val adapter = OnBoardingAdapter(
            fragmentList,
            childFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        val viewPager = binding.viewPager
        binding.dotsIndicator.attachTo(viewPager2 = viewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}