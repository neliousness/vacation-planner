package com.pulsar.vacationplanner.presentation.itineraryDetails

sealed class ItineraryDetailsEvent {
    data object GoBack : ItineraryDetailsEvent()
}