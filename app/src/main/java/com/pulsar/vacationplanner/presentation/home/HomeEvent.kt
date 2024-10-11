package com.pulsar.vacationplanner.presentation.home

import com.pulsar.vacationplanner.domain.model.LocationItinerary

sealed class HomeEvent {

    data class ItineraryDetails(val data: LocationItinerary) : HomeEvent()
    data object SearchItinerary : HomeEvent()

}