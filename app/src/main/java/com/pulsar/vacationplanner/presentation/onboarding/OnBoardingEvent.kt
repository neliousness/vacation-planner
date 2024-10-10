package com.pulsar.vacationplanner.presentation.onboarding

sealed class OnBoardingEvent {

    object OnBoardingComplete: OnBoardingEvent()

}