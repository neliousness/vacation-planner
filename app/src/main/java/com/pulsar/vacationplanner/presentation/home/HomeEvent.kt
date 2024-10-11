package com.pulsar.vacationplanner.presentation.home

import com.pulsar.vacationplanner.domain.model.LocationItinerary

sealed class HomeEvent {
    data class GoToItineraryDetails(val data: LocationItinerary) : HomeEvent()
    data object SearchItinerary : HomeEvent()
    data class Error(val message: String) : HomeEvent()
    data class ItineraryFetchResult(val result: LocationItinerary) : HomeEvent()
}