package com.pulsar.vacationplanner.presentation.home

import com.pulsar.vacationplanner.domain.model.itinerary.LocationItinerary

sealed class HomeEvent {
    data class GoToItineraryDetails(val data: LocationItinerary) : HomeEvent()
    data class SearchItinerary(val destination: String, val days: String) : HomeEvent()
    data class Error(val message: String) : HomeEvent()
    data class Loading(val isLoading: Boolean) : HomeEvent()
}