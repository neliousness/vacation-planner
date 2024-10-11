package com.pulsar.vacationplanner.domain.model

data class LocationItinerary(
    val itinerary: List<ItineraryData>,
    val location: String
)