package com.pulsar.vacationplanner.presentation.locationDetails

import com.pulsar.vacationplanner.domain.model.itinerary.ItineraryData

sealed class LocationDetailsEvent {

    data object GoBack : LocationDetailsEvent()

    data class SelectedItinerary(val itineraryData: ItineraryData) : LocationDetailsEvent()
}