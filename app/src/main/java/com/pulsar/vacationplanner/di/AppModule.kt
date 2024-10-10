package com.pulsar.vacationplanner.di

import com.pulsar.vacationplanner.presentation.onboarding.OnboardingViewModel
import org.koin.androidx.compose.get
import org.koin.dsl.module

val appModule = module {
    // Define dependencies here
    factory { OnboardingViewModel() }
}