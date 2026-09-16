package com.example.newsnest.presentation.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsnest.R
import com.example.newsnest.core.extensions.launchAndRepeatWithViewLifecycle
import com.example.newsnest.core.shared.base.BaseFragment
import com.example.newsnest.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(FragmentSplashBinding::inflate) {

    override val viewModel: SplashViewModel by viewModels()

    override fun observeData() {
        launchAndRepeatWithViewLifecycle {
            viewModel.progress.collect { progress ->
                binding.progress.progress = progress
            }
        }

        launchAndRepeatWithViewLifecycle {
            viewModel.event.collect { event ->
                when (event) {
                    SplashViewModel.SplashEvent.NavigateToOnboarding -> {
                        findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
                    }
                }
            }
        }
    }
}