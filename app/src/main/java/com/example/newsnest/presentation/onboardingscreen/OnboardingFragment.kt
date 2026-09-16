package com.example.newsnest.presentation.onboardingscreen

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsnest.R
import com.example.newsnest.core.shared.base.BaseFragment
import com.example.newsnest.databinding.FragmentOnboardingBinding
import com.example.newsnest.core.extensions.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding, OnboardingViewModel>(
    FragmentOnboardingBinding::inflate
) {
    override val viewModel: OnboardingViewModel by viewModels()

    override fun setupViews() {
        binding.apply {
            btnGetStarted.setOnClickListener {
                viewModel.onGetStartedClicked()
            }
        }
    }
    override fun observeData() {
        launchAndRepeatWithViewLifecycle {
            viewModel.event.collect { event ->
                when (event) {
                    OnboardingViewModel.OnboardingEvent.NavigateToHome -> {
                        findNavController().navigate(R.id.action_onboardingFragment_to_homeFragment)
                    }
                }
            }
        }
    }
}