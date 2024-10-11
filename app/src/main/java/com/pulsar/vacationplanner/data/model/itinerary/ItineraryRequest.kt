package com.pulsar.vacationplanner.data.model.itinerary

data class ItineraryRequest(
    val destination: String,
    val duration: Int
)