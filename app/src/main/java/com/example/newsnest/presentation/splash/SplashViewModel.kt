package com.example.newsnest.presentation.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsnest.core.shared.utils.Pref
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {
    private val _SplashData = MutableSharedFlow<SplashEvents>()
    val SplashData = _SplashData.asSharedFlow()

    fun onItemClick() {
        viewModelScope.launch {
            val isDone = Pref.isOnBoardingComplete(context)
            if (isDone) {
                _SplashData.emit(SplashEvents.NavigateNewsDashBoard)
            } else {
                _SplashData.emit(SplashEvents.NavigateToOnboard)
            }
        }
    }
}

sealed class SplashEvents {
    object NavigateToOnboard : SplashEvents()
    object NavigateNewsDashBoard : SplashEvents()
}