package com.example.newsnest.presentation.onboardingscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {

    private val _event = MutableSharedFlow<OnboardingEvent>()
    val event: SharedFlow<OnboardingEvent> = _event.asSharedFlow()

    fun onGetStartedClicked() {
        viewModelScope.launch {
            _event.emit(OnboardingEvent.NavigateToHome)
        }
    }

    sealed class OnboardingEvent {
        object NavigateToHome : OnboardingEvent()
    }
}