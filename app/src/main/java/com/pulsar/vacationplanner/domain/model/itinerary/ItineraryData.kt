package com.pulsar.vacationplanner.domain.model.itinerary

data class ItineraryData(
    val activities: List<Activity>,
    val date: String,
    val title: String
)