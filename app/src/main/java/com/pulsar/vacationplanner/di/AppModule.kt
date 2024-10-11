package com.pulsar.vacationplanner.di

import com.pulsar.vacationplanner.presentation.common.viewmodels.SharedLocationItineraryViewModel
import com.pulsar.vacationplanner.presentation.details.DetailsViewModel
import com.pulsar.vacationplanner.presentation.home.HomeViewModel
import com.pulsar.vacationplanner.presentation.onboarding.OnboardingViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //region viewModels
    factory { OnboardingViewModel() }
    factory { HomeViewModel() }
    factory { DetailsViewModel() }
    viewModel { SharedLocationItineraryViewModel() }
    //endregion viewModels
}