package com.pulsar.vacationplanner.di

import com.pulsar.vacationplanner.data.remote.LocationItineraryApi
import com.pulsar.vacationplanner.domain.repository.ItineraryRepository
import com.pulsar.vacationplanner.data.repository.ItineraryRepositoryImpl
import com.pulsar.vacationplanner.domain.usecases.FetchDestinationsUseCase
import com.pulsar.vacationplanner.domain.usecases.LoadOnboardDataUseCase
import com.pulsar.vacationplanner.domain.usecases.SaveOnboardDataUseCase
import com.pulsar.vacationplanner.domain.usecases.SearchItineraryUseCase
import com.pulsar.vacationplanner.presentation.common.viewmodels.SharedLocationItineraryViewModel
import com.pulsar.vacationplanner.presentation.locationDetails.LocationDetailsViewModel
import com.pulsar.vacationplanner.presentation.home.HomeViewModel
import com.pulsar.vacationplanner.presentation.itineraryDetails.ItineraryDetailsViewModel
import com.pulsar.vacationplanner.presentation.onboarding.OnboardingViewModel
import com.pulsar.vacationplanner.util.Constants.BASE_URL
import com.pulsar.vacationplanner.util.PreferencesHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val presentationModule = module {
    //region viewModels
    factory { OnboardingViewModel() }
    factory { HomeViewModel(get(),get()) }
    factory { LocationDetailsViewModel() }
    factory { ItineraryDetailsViewModel() }
    viewModel { SharedLocationItineraryViewModel(get(), get()) }
    //endregion viewModels
}

val networkModule = module {

    //region network
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(LocationItineraryApi::class.java) }
    single<ItineraryRepository> { ItineraryRepositoryImpl(get()) }
    //endregion network
}

val helperModule = module {
    single { PreferencesHelper(get()) }
}

val useCasesModule = module {
    factory { FetchDestinationsUseCase(get()) }
    factory { SearchItineraryUseCase(get()) }
    factory { SaveOnboardDataUseCase(get()) }
    factory { LoadOnboardDataUseCase(get()) }
}