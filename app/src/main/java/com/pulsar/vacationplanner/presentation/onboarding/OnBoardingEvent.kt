package com.pulsar.vacationplanner.presentation.onboarding

sealed class OnBoardingEvent {
    data object OnBoardingComplete: OnBoardingEvent()
}