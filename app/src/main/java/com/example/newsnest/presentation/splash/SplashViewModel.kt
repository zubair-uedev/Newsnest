package com.example.newsnest.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private val _progress = MutableStateFlow(0)
    val progress = _progress.asStateFlow()

    private val _event = MutableSharedFlow<SplashEvent>()
    val event: SharedFlow<SplashEvent> = _event.asSharedFlow()

    init {
        startProgress()
    }

    private fun startProgress() {
        viewModelScope.launch {
            for (i in 1..100) {
                delay(30.milliseconds)
                _progress.value = i
            }
            _event.emit(SplashEvent.NavigateToOnboarding)
        }
    }

    sealed class SplashEvent {
        object NavigateToOnboarding : SplashEvent()
    }
}