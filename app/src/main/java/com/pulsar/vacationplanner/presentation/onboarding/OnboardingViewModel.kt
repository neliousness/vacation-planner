package com.pulsar.vacationplanner.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {
    private val _uiEvent = MutableSharedFlow<OnBoardingEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun onEvent(event: OnBoardingEvent) {
        viewModelScope.launch {
            when (event) {
                is OnBoardingEvent.OnBoardingComplete -> {
                    _uiEvent.emit(OnBoardingEvent.OnBoardingComplete)
                }
            }
        }
    }
}