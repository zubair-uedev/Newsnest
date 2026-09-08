package com.example.newsnest.presentation.splash
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.newsnest.R
import com.example.newsnest.core.shared.base.BaseFragment
import com.example.newsnest.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    FragmentSplashBinding::inflate
) {
    override val viewModel: SplashViewModel by viewModels()
    override fun observeData() {
        eventObserver()
        ObjectAnimator.ofInt(binding.progress, "progress", 0, 100).apply {
            duration = 3000
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    viewModel.onItemClick()   // ViewModel ko batao "ab check karo"
                }
            })
            start()
        }
    }
    fun eventObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.SplashData.collectLatest {
                    if (isAdded) {
                        when (it) {
                            SplashEvents.NavigateNewsDashBoard -> {
                                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                            }

                            SplashEvents.NavigateToOnboard -> {
                                findNavController().navigate(R.id.action_splashFragment_to_onboardingFragment)
                            }
                        }
                    }
                }
            }
        }
    }
}
