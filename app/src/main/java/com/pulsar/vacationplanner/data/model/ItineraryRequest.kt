package com.pulsar.vacationplanner.data.model

data class ItineraryRequest(
    val destination: String,
    val duration: Int
)